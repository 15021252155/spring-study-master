package cn.com.sdd.condition;

import cn.com.sdd.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName MyImportBeanDefinitionRegistrar
 * @Author suidd
 * @Description 自定义ImportBeanDefinitionRegistrar
 * @Date 23:18 2020/11/1
 * @Version 1.0
 **/
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * @param annotationMetadata     当前标注@ImportBeanDefinitionRegistrar注解类的所有注解信息
     * @param beanDefinitionRegistry beanDefinition注册类，调用其它的registerBeanDefinition注册bean
     */
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        boolean red = beanDefinitionRegistry.containsBeanDefinition("cn.com.sdd.bean.Red");
        boolean blue = beanDefinitionRegistry.containsBeanDefinition("cn.com.sdd.bean.Blue");
        if (red && blue) {
            //指定bean定义信息
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
            //指定bean名
            beanDefinitionRegistry.registerBeanDefinition("rainBow", rootBeanDefinition);
        }

    }
}
