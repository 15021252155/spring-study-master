package cn.com.sdd.test;

import cn.com.sdd.bean.Person;
import cn.com.sdd.bean.Student;
import cn.com.sdd.config.MainConfig;
import cn.com.sdd.config.MainConfig3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName MainTest
 * @Author suidd
 * @Description 主测试类
 * @Date 22:13 2020/6/3
 * @Version 1.0
 **/
public class MainTest {
    public static void main(String[] args) {
        // 以xml的形式配置bean
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println("以xml的形式配置bean信息：" + person);

        //以注解@Bean形式
        ApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(MainConfig3.class);
        Student student = applicationContext1.getBean(Student.class);
        System.out.println("以注解@Bean形式信息：" + student);

        String[] names = applicationContext1.getBeanNamesForType(Student.class);
        for (String name :
                names) {
            System.out.println("name=" + name);
        }
    }
}
