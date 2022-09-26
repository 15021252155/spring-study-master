package cn.com.sdd;

import cn.com.sdd.bean.Human;
import cn.com.sdd.config.MainConfigOfProperty;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @ClassName IOC_Property
 * @Author suidd
 * @Description 属性赋值测试类
 * @Date 20:06 2020/11/3
 * @Version 1.0
 **/
public class IOC_PropertyTest {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigOfProperty.class);

    @Test
    public void test01() {
        printBeans(annotationConfigApplicationContext);
        System.out.println("===================");

        Human human = (Human) annotationConfigApplicationContext.getBean("human");
        System.out.println(human);

        annotationConfigApplicationContext.close();

    }

     /**
      * @Author suidd
      * @Description 测试读取环境变量中的变量值（从配置文件加载的）
      * @Date 20:34 2020/11/3
      * @Param
      * @return
      * @Version 1.0
      **/
    @Test
    public void test02(){
        ConfigurableEnvironment environment = annotationConfigApplicationContext.getEnvironment();
        String nickName = environment.getProperty("nick.name");
        System.out.println(nickName);
    }

    /**
     * @return
     * @Author suidd
     * @Description 打印容器中的bean名称
     * @Date 20:11 2020/11/3
     * @Param
     * @Version 1.0
     **/
    private void printBeans(AnnotationConfigApplicationContext app) {
        String[] beanDefinitionNames = app.getBeanDefinitionNames();
        for (String name : beanDefinitionNames
        ) {
            System.out.println(name);
        }
    }
}
