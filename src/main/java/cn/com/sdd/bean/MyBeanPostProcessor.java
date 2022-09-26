package cn.com.sdd.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyBeanPostProcessor
 * @Author suidd
 * @Description 任何bean，配置该类，实现BeanPostProcessor接口，实现初始化和销毁工作
 * @Date 22:37 2020/11/2
 * @Version 1.0
 **/
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName+"->postProcessBeforeInitialization...bean=" + bean + "...beanName=" + beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization...bean=" + bean + "...beanName=" + beanName);
        return bean;
    }
}
