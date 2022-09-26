package cn.com.sdd;

import cn.com.sdd.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName IOC_TXTest
 * @Author suidd
 * @Description 测试BeanFactoryPostProcessor
 * @Date 21:20 2020/11/8
 * @Version 1.0
 **/
public class IOC_ExtTest {

    //BeanFactoryProcessor测试
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);

        applicationContext.close();
    }

    //ApplicationListener测试
    @Test
    public void test02() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);

        //发布一个事件
        applicationContext.publishEvent(new ApplicationEvent(new String("发布一个事件demo")) {
        });
        applicationContext.close();
    }
}
