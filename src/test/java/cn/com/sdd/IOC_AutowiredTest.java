package cn.com.sdd;

import cn.com.sdd.bean.Boss;
import cn.com.sdd.bean.Car;
import cn.com.sdd.bean.Color;
import cn.com.sdd.config.MainConfigOfAutowired;
import cn.com.sdd.dao.BookDao;
import cn.com.sdd.dao.PageDao;
import cn.com.sdd.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName IOC_LifeCycle
 * @Author suidd
 * @Description IOC生命周期测试类
 * @Date 21:51 2020/11/3
 * @Version 1.0
 **/
public class IOC_AutowiredTest {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

        BookService bean = applicationContext.getBean(BookService.class);
        System.out.println(bean);

        // 根据类型查询，可能会查询到多个,执行一下代码，如果注入了多个BookDao会报错
        // MainConfigOfAutowired类注入bean的时候加上@Primary注解就不会报错了
        BookDao bean1 = applicationContext.getBean(BookDao.class);
        System.out.println("多个相同类型的bean，添加@Primary注解后，bean=" + bean1);

        // 通过getBeanNamesForType方法，按照类型查询，可以查询到所有注入类型为BookDao的bean
        String[] beanNamesForType = applicationContext.getBeanNamesForType(BookDao.class);
        System.out.println("通过getBeanNamesForType方法获取到所有BookDao的bean");
        for (String name : beanNamesForType) {
            System.out.println(name);
        }

        // 指定bean名称查询
        Object bookDao = applicationContext.getBean("bookDao");
        System.out.println("指定bean名称查询，bookDao=" + bookDao);

        // 指定bean名称查询
        Object bookDao2 = applicationContext.getBean("bookDao2");
        System.out.println("指定bean名称查询，bookDao2=" + bookDao2);
    }

    @Test
    public void test02() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        //测试，当IOC容器中，没有PageDao注册的的组件，会报错
        PageDao pageDao = applicationContext.getBean(PageDao.class);
        System.out.println(pageDao);
    }


    /**
     * @return
     * @Author suidd
     * @Description //Autowired，标注在方法上：spring容器创建当前对象，就会调用方法，完成赋值
     * @Date 22:13 2020/11/4
     * @Param
     * @Version 1.0
     **/
    @Test
    public void test03() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        //获取boos类中标注在方法上的car是否装配
        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);
        //获取当前容器car
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);
    }

    /**
     * @return
     * @Author suidd
     * @Description 标注在有参构造器上：构造器要用的组件，从容器中获取
     * @Date 22:17 2020/11/4
     * @Param
     * @Version 1.0
     **/
    @Test
    public void test04() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        //获取boos类中标注在方法上的car是否装配
        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);
        //获取当前容器car
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);
    }

    /**
     * @return
     * @Author suidd
     * @Description 通过bean标注的方法，创建对象的时候，方法参数的值，从容器中直接获取
     * @Date 22:27 2020/11/4
     * @Param
     * @Version 1.0
     **/
    @Test
    public void test05() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        Color color = applicationContext.getBean(Color.class);
        System.out.println(color);
        //获取当前容器car
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);
    }
}
