package cn.com.sdd.config;

import cn.com.sdd.bean.Color;
import cn.com.sdd.bean.ColorFactoryBean;
import cn.com.sdd.bean.Red;
import cn.com.sdd.condition.MyImportBeanDefinitionRegistrar;
import cn.com.sdd.condition.MyImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName MainConfigImport
 * @Author suidd
 * @Description 给容器注册bean的方式
 * <p>
 * * 1、包扫描+组件标注注解（@Controller、@Service。。。）
 * <p>
 * * 2、@Bean bean标签注入【导入的第三方的组件】
 * <p>
 * * 3、@Import 快速给容器注册一个组件
 * ====3.1）@Impotrt 给容器注册一个组件，id默认是组件的全类名
 * ====3.2）@ImportSelector：返回需要导入的组件的全类名数组
 * ====3.3）@ImportBeanDefinitionRegistrar：手动注册bean到容器中
 * <p>
 * * 4、使用spring提供的FactoryBean(工厂Bean)
 * ====4.1）默认获取的是工厂bean调用getObject创建的对象
 * ====4.2）要获取工厂Bean本身，要在bean id前面加 &符号，比如：&colorFactoryBean
 * @Date 22:58 2020/11/1
 * @Version 1.0
 **/
@Configuration
@ComponentScan(value = {"cn.com.sdd.dao"})
@Import({Color.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfigImport {
    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
