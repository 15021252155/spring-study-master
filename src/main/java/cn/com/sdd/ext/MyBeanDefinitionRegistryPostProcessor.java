package cn.com.sdd.ext;

import cn.com.sdd.bean.Blue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyBeanDefinitionRegistryPostProcessor
 * @Author suidd
 * @Description TODO
 * @Date 21:58 2020/11/9
 * @Version 1.0
 **/
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    //BeanDefinitionRegistry Bean定义信息的保存中心，以后BeanFactory就是按照里面保存的每一个bean定义信息创建bean实例；
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor...postProcessBeanDefinitionRegistry...bean的数量：" + registry.getBeanDefinitionCount());

        //新增注册一个bean
        //方式1：
        // RootBeanDefinition rootBeanDefinition=new RootBeanDefinition(Blue.class);
        //registry.registerBeanDefinition("hello", rootBeanDefinition);
        //方式2：
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Blue.class).getBeanDefinition();
        registry.registerBeanDefinition("hello", beanDefinition);
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor...postProcessBeanFactory......bean的数量：" + beanFactory.getBeanDefinitionCount());
    }
}
