package cn.com.sdd.config;

import cn.com.sdd.bean.Car;
import cn.com.sdd.bean.Color;
import cn.com.sdd.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @ClassName MainConfigOfAutowired
 * @Author suidd
 * @Description 学习自动装配配置类
 * 自动装配：
 * spring利用依赖注入（DI），完成对IOC容器中各个组件的依赖关系赋值
 * 一、Autowired【spring定义的注解】:自动注入
 * 1）、默认按照类型去容器中找对应的组件：applicationContext.getBean(BookDao.class);
 * 2）、如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找，applicationContext.getBean("bookDao");
 * 3)、@Qualifier，使用@Qualifier("bookDao")指定需要装配的组件的id，而不是使用属性名
 * 4）、自动装配默认要将属性赋值好，没有就会报错
 * 可以使用@Autowired(required = false) 设置为非必须装配
 * 5）、@Primary ：标注这个的bean，当类型有多个的时候，优先装配标注@Primary，如果有@Qualifier，肯定按照@Qualifier，因为@Qualifier是指定装配
 * <p>
 * 二、spring还支持@Resource(JSR250)@Inject(JSR330)【java规范的注解】
 * 1)、@Resource:和@Autowired一样实现自动装配功能，默认是按照组件名称进行装配
 * 不支持@Primary，，不支持@Autowired(required = false)
 * 2)、@Inject：需要导入javax.inject包，和Autowired功能一样，没有required = false
 * <p>
 * AutowiredAnnotationBeanPostProcessor：解析完成装配功能
 * <p>
 * 三、Autowired可以标注的位置：构造器、参数、方法、属性，都是从容器中获取参数组件的值
 * 1)、标注在方法上（默认可以不写@Autowired，都可以自动装配）
 * 2）、标注在构造器上（当类，只有一个有参构造器的时候，@Autowired可以省略，参数位置的组件还是可以从容器中获取）
 * 3）、标注在参数位置
 *
 * 四、自定义组件，想要使用spring底层的一些组件，（ApplicationContext、BeanFactory、XXXX）
 *      自定义组件实现XXXAware：在创建的对象的时候，会调用接口规定的方法注入相关组件；Aware
 *      把Spring底层的一些组件注入到自定义的Bean中
 *      XXXAware：功能使用XXXProcessor
 *          ApplicationContextAware====>ApplicationContextAwareProcessor
 * @Date 21:18 2020/11/3
 * @Version 1.0
 **/
@Configuration
@ComponentScan({"cn.com.sdd.dao", "cn.com.sdd.service", "cn.com.sdd.controller", "cn.com.sdd.bean"})
public class MainConfigOfAutowired {

    //模拟容器中存在2个bookDao组件，测试按照类型调用时，调用的是哪个
    //另一个bookDao通过@Repository注入
    //可以通过配置@Primary，优先进行装配
    @Primary
    @Bean("bookDao2")
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setLable("2");
        return bookDao;
    }

    //通过bean标注的方法，创建对象的时候，方法参数的值，从容器中直接获取
    //@Autowired 可省略
    @Bean
    public Color color(@Autowired Car car) {
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
