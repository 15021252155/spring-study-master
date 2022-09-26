package cn.com.sdd.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @ClassName MyBeanFactoryProcessor
 * @Author suidd
 * @Description 测试BeanFactoryProcessor
 * @Date 21:32 2020/11/9
 * @Version 1.0
 **/
@Component
public class MyBeanFactoryProcessor implements BeanFactoryPostProcessor {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryProcessor...postProcessBeanFactory...");
        int count = beanFactory.getBeanDefinitionCount();
        System.out.println("当前BeanFactory中，共有" + count + "个bean");

        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        System.out.println("当前BeanFactory中的bean：" + Arrays.asList(beanDefinitionNames));

    }
}
