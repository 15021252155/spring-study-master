package cn.com.sdd.config;

import cn.com.sdd.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @ClassName MainConfigOfLifeCycle
 * @Author suidd
 * @Description bean的生命周期：
 * bean的创建------初始化------销毁的过程
 * 容器管理bena的生命周期：
 * 我们可以自定义初始化和销毁方法，容器在bean进行到当前生命周期的时候可以调用我们自定义的初始化和销毁方法
 * <p>
 *
 * 构造
 *      单实例：容器启动的时候创建对象
 *      多实例：每次获取的时候创建对象
 *
 * 初始化
 *      对象创建完成，并赋值好，调用初始化方法
 *
 * 销毁
 *      单实例：容器关闭的时候
 *      多实例：容器不会管理bean，容器不会调用销毁方法
 *
 * <p>
 * 1）、指定初始化和销毁方法
 *      通过@Bean(initMethod = "init", destroyMethod = "destory")initMethod destroyMethod
 *
 * 2)、通过让bean实现InitializingBean接口（定义初始化逻辑），DisposableBean(定义销毁逻辑)
 *
 * 3)、使用JSR250:
 *      @PostConstruct:在bean创建完成，并且属性赋值好之后，执行初始化方法
 *      @PreDestroy：在容器销毁bean之前，通知我们做清理工作
 *
 * 4)、使用BeanPostProcessor：bean的后置处理器
 *      在bean的初始化前后进行一些处理工作
 *      postProcessBeforeInitialization:在初始化之前工作
 *      postProcessAfterInitialization:在初始化之后工作
 *
 * ==========================================================================================
 * postProcessBeforeInitialization和postProcessAfterInitialization方法为什么可以在初始化方法之前和之后进行？
 * 通过debug调试，核心调用过程如下：
 * 遍历容器中所有的BeanPostProcessor，挨个执行postProcessBeforeInitialization方法，一旦返回null，跳出for循环，不会执行后面的BeanPostProcessor
 *
 * =======BeanPostProcessor原理=============
 * 核心代码执行顺序过程如下：
 * populateBean(beanName, mbd, instanceWrapper);//给bean进行属性赋值
 * initializeBean 方法包含如下：
 * {
 *  wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *  invokeInitMethods(beanName, wrappedBean, mbd);//初始化方法
 *  wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * }
 *
 *
 * spring底层对BeanPostProcessor的使用:
 *                      bean赋值
 *                      注入其他组件
 *                      @AutoWired
 *                      生命周期注解功能
 *                      @Async
 *                      xxxBeanPostProcessor
 *
 *
 * @Date 21:51 2020/11/2
 * @Version 1.0
 **/
@Configuration
@ComponentScan("cn.com.sdd.bean")
public class MainConfigOfLifeCycle {

    //@Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destory")
    public Car car() {
        return new Car();
    }
}
