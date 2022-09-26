package cn.com.sdd;

import cn.com.sdd.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @ClassName IOC_ProfileTest
 * @Author suidd
 * @Description Profile测试类
 * @Date 23:11 2020/11/4
 * @Version 1.0
 **/
public class IOC_ProfileTest {

    //切换环境标识的方式：
    //1、使用命令行动态参数：在虚拟机参数位置加 -Dspring.profile.active=test
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
        String[] beanNamesForAnnotation = applicationContext.getBeanNamesForType(DataSource.class);
        for (String name : beanNamesForAnnotation) {
            System.out.println(name);
        }
    }

    //切换环境标识的方式：
    //2、代码方式
    @Test
    public void test02() {
        //创建一个applicationContext，通过无参构造
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //指定需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test");
        //注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        //启动刷新容器
        applicationContext.refresh();

        String[] beanNamesForAnnotation = applicationContext.getBeanNamesForType(DataSource.class);
        for (String name : beanNamesForAnnotation) {
            System.out.println(name);
        }
    }

}
