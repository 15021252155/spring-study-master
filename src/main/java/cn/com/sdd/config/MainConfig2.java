package cn.com.sdd.config;

import cn.com.sdd.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @ClassName MyConfig2
 * @Author suidd
 * @Description TODO
 * @Date 16:28 2020/6/6
 * @Version 1.0
 **/
@Configuration
public class MainConfig2 {

    /**
     * @Scope：调整作用域，它包含以下类型
     * prototype： 多实例，ioc容器启动并不会调用方法创建对象，每次获取的时候才会调用方法创建对象
     * singleton： 单实例（默认值）,ioc容器启动会调用方法创建对象到ioc容器中，
     *             以后每次获取就是直接从容器中拿
     * request： 同一个请求创建一个实例
     * session：同一个session创建一个实例
     *
     * 懒加载：
     *      单实例bean，默认在容器启动的时候创建对象；
     *      懒加载：容器启动不创建对象，第一次使用（获取）Bean创建对象，并初始化
     *
     *
     *
     * @return
     */
    //默认都是单实例
    @Bean("person")
    //@Scope("prototype")
    //@Scope("singleton")
    @Lazy
    public Person person2() {
        System.out.println("给容器中添加person...");
        return new Person("小李子", 19);
    }
}
