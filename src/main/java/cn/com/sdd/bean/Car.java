package cn.com.sdd.bean;

import org.springframework.stereotype.Component;

/**
 * @ClassName Car
 * @Author suidd
 * @Description 通过@Bean 指定init和销毁方法 进行初始化和销毁方法
 * @Date 21:55 2020/11/2
 * @Version 1.0
 **/
@Component
public class Car {
    public Car() {
        System.out.println("Car constructor...");
    }

    public void init() {
        System.out.println("Car init...");
    }

    public void destory() {
        System.out.println("Car destory...");
    }
}
