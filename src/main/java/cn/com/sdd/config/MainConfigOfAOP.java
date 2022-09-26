package cn.com.sdd.config;

import cn.com.sdd.aop.LogAspects;
import cn.com.sdd.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ClassName MainConfigOfAOP
 * @Author suidd
 * @Description AOP:【动态代理】
 * 指在程序运行期间可以动态的将某段代码切入到指定方法指定位置进行运行的编程方式
 * 1)、导入AOP模块；Spring AOP:spring-aspects
 * 2)、编写一个业务场景（MathCalculator）：在业务逻辑运行的时候，将日志进行打印（方法进行之前，方法运行结束、方法出现异常）
 * 3）、定义一个日志切面类（LogAspects），切面类的方法要动态的感知MathCalculator类div方法运行哪里，然后运行
 * 通知方法：
 *  前置通知（@Before）：logStart:在目标方法（MathCalculator.div）运行之前运行
 *  后置通知(@After)：logEnd:在目标方法（MathCalculator.div）运行之后运行
 *  返回通知(@AfterReturning)：logReturn:在目标方法（MathCalculator.div）正常返回之后运行
 *  异常通知(@AfterThrowing)：logError:在目标方法（MathCalculator.div）异常返回之后运行
 *  环绕通知(@Around)：动态代理，手动推进目标方法（joinPoint.proceed()）
 * 4)、给切面类的目标方法标注何时何地运行（通知注解）
 * 5)、将切面类和逻辑类都要加入到IOC容器中
 * 6）、必须告诉Spring哪个类是切面类（给切面类上加@Aspect注解）
 * 7）、配置中必须加@EnableAspectJAutoProxy【开启基于注解的AOP模式】！！！！★★★★★★★★★
 *      在Spring中有很多的@EnableXXXXX的注解；
 *
 *
 * 总结三部曲：
 * 1）、将业务组件和切面类都加入到IOC容器中；告诉Spring哪一个是切面类（@Aspect）
 * 2）、在切面类上的每一个通知方法上，标注通知注解，告诉Spring何时何地运行（切入点表达式）
 * 3)、开启基于注解的AOP模式 ：@EnableAspectJAutoProxy
 *
 *
 * AOP原理：
 *      【看给容器中注册了什么组件？这个组件什么时候工作？这个组件的功能是什么？】
 *      【看给容器中注册了什么组件？这个组件什么时候工作？这个组件的功能是什么？】
 *      【看给容器中注册了什么组件？这个组件什么时候工作？这个组件的功能是什么？】
 *      @EnableAspectJAutoProxy
 *
 * 1）、EnableAspectJAutoProxy是什么？
 *  @Import(AspectJAutoProxyRegistrar.class)：给容器中导入AspectJAutoProxyRegistrar
 *  利用AspectJAutoProxyRegistrar自定义给容器中注册bean
 *          internalAutoProxyCreator=>AnnotationAwareAspectJAutoProxyCreator
 *  给容器中注册一个AnnotationAwareAspectJAutoProxyCreator
 *
 *  2）、AnnotationAwareAspectJAutoProxyCreator：
 *      查看类图，滤清继承关系！
 *      关注后置处理器：（在bean初始化完成前后做什么事情）、自动装配BeanFactoryAware
 *
 * 【AbstractAutoProxyCreator】
 * AbstractAutoProxyCreator.setBeanFactory();
 * AbstractAutoProxyCreator.有后置处理的逻辑；
 *
 * 【AbstractAdvisorAutoProxyCreator】
 * AbstractAdvisorAutoProxyCreator.setBeanFactory()==>initBeanFactory()
 *
 * 【AspectJAwareAdvisorAutoProxyCreator】
 *
 *
 * 【AnnotationAwareAspectJAutoProxyCreator】
 * AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 *
 * ===================容器创建AOP流程：
 * 一）、传入配置类，创建IOC容器
 * 二）、注册配置类，调用refresh()
 * 三）、registerBeanPostProcessors(beanFactory);注册bean的后置处理器，来方便拦截bean的创建
 *      1）、先获取ioc容器已经定义了的需要创建对象的所有BeanPostProcessor
 *      2）、给容器中加别的BeanPostProcessor
 *      3）、优先注册实现了PriorityOrdered接口的BeanPostProcessor
 *      4）、再给容器中注册实现了Ordered接口的BeanPostProcessor
 *      5）、最后注册没有实现优先级接口的BeanPostProcessor
 *      6）、注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中
 *          如何创建internalAutoProxyCreator的BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator】
 *          1）、创建bean的实例
 *          2）、populateBean(beanName, mbd, instanceWrapper);给bean属性赋值
 *          3）、initializeBean(beanName, exposedObject, mbd);初始化bean
 *              1）、invokeAwareMethods(beanName, bean);处理Aware接口的方法回调
 *              2）、applyBeanPostProcessorsBeforeInitialization：执行后置处理的postProcessBeforeInitialization()
 *              3）、invokeInitMethods();执行自定义初始化方法
 *              4)、applyBeanPostProcessorsAfterInitialization：执行后置处理的postProcessAfterInitialization()
 *          4)、BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功==>aspectJAdvisorsBuilder
 *      7）、把BeanPostProcessor注册到BeanFactory中
 *          beanFactory.addBeanPostProcessor(postProcessor);
 *
 * ===================以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程===================
 *          AnnotationAwareAspectJAutoProxyCreator==>InstantiationAwareBeanPostProcessor
 * 四）、finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作：创建剩下的单实例Bean
 *      1）、遍历获取容器中所有的Bean，依次创建对象:getBean(beanName);
 *          getBean()->doGetBean()->getSingleton()->
 *          【AnnotationAwareAspectJAutoProxyCreator会在所有bean创建之前有一个拦截，InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation()】
 *      2)、创建bean
 *          1）、先从缓存中获取当前Bean，如果能获取到，是之前被创建过的，直接使用，否则再创建。只要创建好就会被缓存
 *          2）、createBean;AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前先尝试返回bean的实例
 *              【【【BeanPostProcessor是在bean对象创建完成初始化前后调用的】】】
 *              【【【InstantiationAwareBeanPostProcessor是在创建bean实例之前，先尝试用后置处理器返回对象的】】】
 *              1）、resolveBeforeInstantiation(beanName, mbdToUse);解析BeforeInstantiation，希望后置处理器，在此能返回一个代理对象，如果能返回代理对象就使用，否则继续后面逻辑
 *                  1）、后置处理器先尝试返回对象
 *                      //拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor就执行postProcessBeforeInstantiation()
 *                      bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                      if (bean != null) {
 * 						    bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                      }
 *              2）、doCreateBean(beanName, mbdToUse, args);真正的创建一个bean实例，和3.6流程一样
 *
 *
 * AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】的作用：
 * 一）、在每一个bean创建之前调用postProcessBeforeInstantiation()，拦截组件的创建过程;
 * 关心MathCalculator类和LogAspects类创建的过程
 *  1）、判断当前bean是否在advisedBeans中，（保存了所有需要增强的bean）
 *  2）、判断当前bean是否是基础类型的Advice、Pointcut、Advisor、AopInfrastructureBean
 *          或者是否是切面(@Acpect)
 *  3)、是否需要跳过
 *      1）、获取候选的增强器（切面里面的通知方法）【List<Advisor> candidateAdvisors】
 *          每一个通知方法的增强器是InstantiationModelAwarePointcutAdvisor类型
 *          判断每一个增强器是否是AspectJPointcutAdvisor类型，返回true
 *      2）、返回false
 *
 * 二）调用postProcessAfterInitialization，判断是否需要增强
 *      return wrapIfNecessary(bean, beanName, cacheKey);//包装如果需要的情况下
 *      1）、获取当前bean的所有增强器（通知方法）
 *          ①、找到候选的所有增强器
 *          ②、获取能在当前bean使用的增强器（找到哪些通知方法是需要切入当前bean方法的）
 *          ③、给增强器排序
 *      2）、保存当前bean在advisedBeans中
 *      3）、如果当前bean需要增强，创建bean的代理对象
 *          ①、获取所有增强器（通知方法）
 *          ②、保存到proxyFactory
 *          ③、创建代理对象
 *              JdkDynamicAopProxy(config);jdk动态代理
 *              ObjenesisCglibAopProxy(config);cglib动态代理
 *      4）、给容器中返回当前组件使用cglib增强了的代理对象
 *      5）、以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法
 *
 * 三、目标方法执行：
 *      容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象，XXX）；
 *      1）、CglibAopProxy.intercept();拦截目标方法的执行
 *      2）、根据ProxyFactory对象获取将要执行的目标方法拦截器链；
 *          List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *          1)、List<Object> interceptorList保存所有拦截器5
 *              一个默认的ExposeInvocationInterceptor和4个增强器
 *          2)、遍历所有的增强器，将其转为Interceptor;
 *              registry.getInterceptors(advisor);
 *          3）、将增强器转为List<MethodInterceptor>
 *              如果是MethodInterceptor，直接加入到集合
 *              如果不是，使用AdvisorAdapter将增强器转为MethodInterceptor
 *              转换完成后返回MethodInterceptor数组
 *      3）、如果没有拦截器链，直接执行目标方法
 *          拦截器链（每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 *      4）、如果有拦截器链，把需要执行的目标对象的目标方法，
 *          拦截器链等信息传入创建一个CglibMethodInvocation对象
 *          并调用Object retVal = mi.proceed();
 *      5）、拦截器链的触发过程
 *          1）、如果没有拦截器执行目标方法或者拦截器的索引==拦截器数组大小-1一样（指定到了最后一个拦截器）
 *          2）、链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成后，继续执行
 *              拦截器链的机制，保证了通知方法与目标方法的执行顺序
 *              具体执行顺序流程，参见：/resources/aop_chain/aop_chain_invoke.png截图
 *
 *
 * ============================================总结============================================
 * 1)、通过@EnableAspectJAutoProxy注解，开启AOP功能
 * 2）、@EnableAspectJAutoProxy会给容器注册一个AnnotationAwareAspectJAutoProxyCreator组件
 * 3）、AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
 * 4）、容器的创建流程：
 *      1）、registerBeanPostProcessors(beanFactory);注册后置处理器；创建AnnotationAwareAspectJAutoProxyCreator对象
 *      2）、finishBeanFactoryInitialization(beanFactory);初始化剩下的单实例Bean
 *          ①、创建业务组件和切面组件
 *          ②、AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
 *          ③、组件创建完成之后，通过postProcessAfterInitialization判断组件是否需要增强
 *              是：将切面的方法包装成增强器（Advisor）;给业务逻辑组件创建代理对象(cglib)
 * 5）、目标方法执行
 *      1）、代理对象执行目标方法
 *      2）、CglibAopProxy.intercept();拦截过程
 *          ①、得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
 *          ②、利用拦截器的链式机制。依次进入每一个拦截器进行执行
 *              正常执行：前置通知==>目标方法==>后置通知==>返回通知
 *              出现异常：前置通知==>目标方法==>后置通知==>异常通知
 * @Date 21:10 2020/11/5
 * @Version 1.0
 **/
@Configuration
@EnableAspectJAutoProxy//开启AOP
public class MainConfigOfAOP {

    @Bean
    public MathCalculator mathCalculator() {
        System.out.println("创建对象MathCalculator...");
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects() {
        System.out.println("创建对象LogAspects...");
        return new LogAspects();
    }
}
