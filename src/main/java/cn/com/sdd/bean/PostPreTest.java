package cn.com.sdd.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName PostPreTest
 * @Author suidd
 * @Description TODO
 * @Date 22:38 2020/11/4
 * @Version 1.0
 **/
public class PostPreTest {
    public PostPreTest() {
        System.out.println("PostPreTest constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("PostPreTest PostConstruct...");
    }

    @PreDestroy
    public void destory() {
        System.out.println("PostPreTest PreDestroy...");
    }
}
