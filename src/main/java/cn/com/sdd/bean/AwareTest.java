package cn.com.sdd.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @ClassName AwareTest
 * @Author suidd
 * @Description TODO
 * @Date 23:23 2020/11/2
 * @Version 1.0
 **/
@Component
public class AwareTest implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("传入的IOC容器：" + applicationContext);
    }

    public void setBeanName(String name) {
        System.out.println("当前bean的名字：" + name);
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String s = resolver.resolveStringValue("你好${os.name},我今年#{25+5}岁了，");
        System.out.println(s);
    }
}
