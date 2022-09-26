package cn.com.sdd.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName Boss
 * @Author suidd
 * @Description TODO
 * @Date 22:02 2020/11/4
 * @Version 1.0
 **/
//默认加在ioc容器中的组件，容器启动会调用无参构造器创建对象，再进行初始化赋值等操作
@Component
public class Boss {
    //第1种方式，Autowired标注在类上
    //@Autowired
    private Car car;

    //第3种方式，标注在有参构造器上：构造器要用的组件，从容器中获取
    @Autowired
    public Boss(Car car) {
        this.car = car;
        System.out.println("boss...有参构造器");
    }

    public Car getCar() {
        return car;
    }

    //第2种方式，标注在方法上：spring容器创建当前对象，就会调用方法，完成赋值
    //方法使用的参数，自定义类型的值从IOC容器中获取
    //@Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
