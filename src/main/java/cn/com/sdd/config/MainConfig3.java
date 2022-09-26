package cn.com.sdd.config;

import cn.com.sdd.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MainConfig3
 * @Author suidd
 * @Description 用来展示以注解@Bean形式Demo
 * @Date 21:10 2020/11/1
 * @Version 1.0
 **/
@Configuration
public class MainConfig3 {
    @Bean("student")
    public Student getStudent() {
        return new Student("小虎", 18);
    }

}
