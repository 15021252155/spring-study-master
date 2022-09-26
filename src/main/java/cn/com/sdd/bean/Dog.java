package cn.com.sdd.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName Dog
 * @Author suidd
 * @Description 通过在对应方法上添加@PostConstruct和@PreDestroy进行初始化和销毁方法
 * @Date 22:25 2020/11/2
 * @Version 1.0
 **/
@Component
public class Dog {
    public Dog() {
        System.out.println("Dog constructor...");
    }

    @PostConstruct
    public void init() {
        System.out.println("Dog PostConstruct...");
    }

    @PreDestroy
    public void destory() {
        System.out.println("Dog PreDestroy...");
    }
}
