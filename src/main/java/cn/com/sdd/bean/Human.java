package cn.com.sdd.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName Human
 * @Author suidd
 * @Description 测试@Value赋值使用
 * @Date 20:15 2020/11/3
 * @Version 1.0
 **/
public class Human {
    //使用@Value赋值
    //1、基本数值
    //2、SpEL:#{}
    //3、${},取出配置文件中的值（在运行环境里面变量的值）

    @Value("张三")
    private String name;
    @Value("#{20-2}")
    private Integer age;
    @Value("${nick.name}")
    private String nickName;

    public Human() {
    }

    public Human(String name, Integer age, String nickName) {
        this.name = name;
        this.age = age;
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
