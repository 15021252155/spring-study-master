package cn.com.sdd;

import cn.com.sdd.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName IOC_LifeCycle
 * @Author suidd
 * @Description IOC生命周期测试类
 * @Date 21:59 2020/11/2
 * @Version 1.0
 **/
public class IOC_LifeCycleTest {
    @Test
    public void test01() {
        //创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("ioc容器创建完成...");

        applicationContext.getBean("car");

        //关闭容器
        applicationContext.close();

    }
}
