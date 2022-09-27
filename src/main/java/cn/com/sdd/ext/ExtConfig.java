package cn.com.sdd.ext;

import cn.com.sdd.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ExtConfig
 * @Author suidd
 * @Description
 * 扩展原理：
 * BeanPostProcessor:bean的后置处理器，bean创建对象初始化前后进行拦截工作
 *
 * 一）、BeanFactoryProcessor:BeanFactory的后置处理器
 *      在BeanFactory标准初始化之后调用，所有的bean定义已经加载到BeanFactory中，但是bean的实例还未创建
 *
 * 1)、IOC容器创建对象;refresh()
 * 2）、invokeBeanFactoryPostProcessors(beanFactory);执行BeanFactoryPostProcessor
 *      如何找到BeanPostProcessor并执行他们的方法？
 *      1）、直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法
 *      2）、在初始化其他组件前面执行
 *
 * 二）、BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *      postProcessBeanDefinitionRegistry():在所有bean定义信息将要被加载，bean实例还未创建的时候执行
 *
 *      优先于BeanFactoryPostProcessor执行，利用BeanDefinitionRegistryPostProcessor给容器中再额外添加一些组件
 *
 *      原理：
 *          1）、创建ioc容器;refresh()
 *          2）、==>invokeBeanFactoryPostProcessors(beanFactory);
 *          3)、从容器中获取到所有的BeanDefinitionRegistryPostProcessor组件，
 *                  ①、依次触发所有的postProcessBeanDefinitionRegistry();
 *                  ②、再来触发postProcessBeanFactory()
 *          4)、再从容器中找到BeanFactoryPostProcessor组件，再来触发postProcessBeanFactory()
 *
 *
 * 三）、ApplicationListener：监听容器中发布的事件；事件驱动模型开发
 *      public interface ApplicationListener<E extends ApplicationEvent> extends EventListener
 *          监听ApplicationEvent及其下面的子事件
 *
 *      步骤：
 *          1）、写一个监听器来监听某个事件（ApplicationEvent及其子类）
 *              {   也可以使用@EventListener注解
 *  *              原理：EventListenerMethodProcessor
 *                  EventListenerMethodProcessor implements SmartInitializingSingleton
 *                  SmartInitializingSingleton原理：
 *                  1）、创建ioc容器；refresh()
 *                  2）、finishBeanFactoryInitialization(beanFactory);初始化剩下的单实例bean
 *                      ①、先创建所有的单实例bean;getBean(beanName);
 *                      ②、获取所有创建好的单实例bean，判断是否是SmartInitializingSingleton类型的
 *                          如果是就调用afterSingletonsInstantiated();
 *                  3）、
 *              }
 *
 *          2）、把监听器加入到容器中
 *          3）、只要容器中有相关事件发布，我们就能监听这个事件：
 *              ContextRefreshedEvent：容器刷新完成（所有bean都完成创建）会发布这个事件
 *              ContextClosedEvent：关闭容器会发布这个事件
 *          4）、发布一个事件：
 *              applicationContext.publishEvent();
 *
 *      原理：
 *          ContextRefreshedEvent、IOC_ExtTest$1[source=发布一个事件demo]、ContextClosedEvent
 *      1）、ContextRefreshedEvent事件：
 *          1）、创建ioc容器；refresh()
 *          2）、finishRefresh()，会发布ContextRefreshedEvent
 *      2）、自己发布事件，publishEvent(new ContextRefreshedEvent(this));
 *      3）、ContextClosedEvent事件
 *
 *      【事件发布流程：】
 *          ①、获取事件的多播器（派发器：getApplicationEventMulticaster()
 *          ②、multicastEvent派发事件
 *          ③、获取到所有ApplicationListener
 *              for (final ApplicationListener<?> listener : getApplicationListeners(event, type))
 *                  Ⅰ、如果有Executor，异步派发
 *                      Executor executor = getTaskExecutor();
 *                  Ⅱ、否则，同步的方式直接执行listener方法：invokeListener(listener, event);
 *                      若拿到listener，回调listener.onApplicationEvent(event);
 *
 *      【事件多播器（派发器）】
 *          1）、创建ioc容器；refresh()
 *          2、initApplicationEventMulticaster();初始化ApplicationEventMulticaster对象
 *              1）、先去容器中找有没有id="applicationEventMulticaster"的组件
 *              2）、如果没有：this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
 *                  并且加入到容器中，我们就可以在其他组件派发事件时，自动注入applicationEventMulticaster
 *
 *      【容器中有哪些监听器】
 *          1）、创建ioc容器；refresh()
 *          2）、registerListeners();
 *              ①、从容器中拿到所有监听器：
 *              String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 *              ②、将listener注册到ApplicationEventMulticaster中：
 *              getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 *
 * @Date 21:28 2020/11/9
 * @Version 1.0
 **/
@Configuration
@ComponentScan("cn.com.sdd.ext")
public class ExtConfig {

    @Bean
    public Blue blue() {
        return new Blue();
    }
}
