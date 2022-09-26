package cn.com.sdd;

import cn.com.sdd.bean.Person;
import cn.com.sdd.config.MainConfig;
import cn.com.sdd.config.MainConfig2;
import cn.com.sdd.config.MainConfigConditional;
import cn.com.sdd.config.MainConfigImport;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * @ClassName IOCTest
 * @Author suidd
 * @Description IOCTest
 * @Date 10:09 2020/6/6
 * @Version 1.0
 **/
public class IOCTest {

    /**
     * @return
     * @Author suidd
     * @Description 测试类的扫描
     * @Date 21:31 2020/11/1
     * @Param
     * @Version 1.0
     **/
    @SuppressWarnings("resource")
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    /**
     * @return
     * @Author suidd
     * @Description 测试类的Scope（单实例、多实例）
     * @Date 21:31 2020/11/1
     * @Param
     * @Version 1.0
     **/
    @SuppressWarnings("resource")
    @Test
    public void test02() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }

        System.out.println("ioc容器创建完成...");
        Object bean = applicationContext.getBean("person");
        Object bean2 = applicationContext.getBean("person");
        System.out.println("scope测试，判断同一个实体是否相同====");
        System.out.println(bean == bean2);
    }

    /**
     * @return
     * @Author suidd
     * @Description 测试Conditional注解
     * 根据当前操作系统类型注入不同的bean
     * windows系统：注入@Bean("bill")
     * linux系统：注入@Bean("linus")
     * @Date 22:04 2020/11/1
     * @Param
     * @Version 1.0
     **/
    @Test
    public void test03() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigConditional.class);
        //获取当前系统信息
        Environment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println("当前操作系统的os.name：" + property);

        //获取所有类型是Person类型的bean的名称
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);

        System.out.println("类型是Person的bean如下：");
        for (String name : beanNamesForType) {
            System.out.println(name);
        }


        Map<String, Person> beans = applicationContext.getBeansOfType(Person.class);
        System.out.println(beans);
    }

    /**
     * @return
     * @Author suidd
     * @Description 测试以import的形式注入组件
     * @Date 23:01 2020/11/1
     * @Param
     * @Version 1.0
     **/
    @Test
    public void testConfigImport() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigImport.class);
        // 获取上线文中的bean
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }

        //工厂bean获取的是调用getObject创建的对象
        Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
        System.out.println("colorFactoryBean类型：" + colorFactoryBean.getClass());

        //工厂bean也可以获取他本身
        Object bean = applicationContext.getBean("&colorFactoryBean");
        System.out.println("&colorFactoryBean类型：" + bean.getClass());
    }
}
