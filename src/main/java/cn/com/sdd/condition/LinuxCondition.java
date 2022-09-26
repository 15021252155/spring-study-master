package cn.com.sdd.condition;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @ClassName LinuxCondition
 * @Author suidd
 * @Description 判断是否是linux系统
 * @Date 22:16 2020/11/1
 * @Version 1.0
 **/
public class LinuxCondition implements Condition {
    /**
     * @param conditionContext           判断条件能使用的上下文（环境）
     * @param annotatedTypeMetadata：注释信息
     * @return
     */
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //1.能获取到ioc使用的BeanFactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        //2.能获取到类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        //3.能获取到当前环境信息
        Environment environment = conditionContext.getEnvironment();
        //4.能获取到bean定义的注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        String property = environment.getProperty("os.name");
        if (property.contains("linux"))
            return true;
        return false;
    }
}
