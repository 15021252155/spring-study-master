package cn.com.sdd.config;

import cn.com.sdd.bean.Human;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ClassName MainConfigOfProperty
 * @Author suidd
 * @Description 属性赋值config
 * @Date 20:02 2020/11/3
 * @Version 1.0
 **/
@Configuration
//使用@PropertySource，读取外部的配置文件的K/V,保存到运行的环境变量中
@PropertySource(value = {"classpath:/human.properties"})
public class MainConfigOfProperty {
    @Bean
    public Human human() {
        return new Human();
    }
}
