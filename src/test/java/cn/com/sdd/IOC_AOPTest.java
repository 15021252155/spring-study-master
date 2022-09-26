package cn.com.sdd;

import cn.com.sdd.aop.MathCalculator;
import cn.com.sdd.config.MainConfigOfAOP;
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
public class IOC_AOPTest {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        //不要自己new对象，因为自己new的，不是spring管理的，无法使用这些功能
       // MathCalculator mathCalculator = new MathCalculator();
        //mathCalculator.div(1, 1);

        MathCalculator bean = applicationContext.getBean(MathCalculator.class);
        bean.div(1, 1);
    }
}
