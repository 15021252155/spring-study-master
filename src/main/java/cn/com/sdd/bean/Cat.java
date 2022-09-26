package cn.com.sdd.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @ClassName Cat
 * @Author suidd
 * @Description 通过实现InitializingBean、DisposableBean 进行初始化和销毁方法
 * @Date 22:16 2020/11/2
 * @Version 1.0
 **/
@Component
public class Cat implements InitializingBean, DisposableBean {
    public Cat() {
        System.out.println("Cat constructor...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("Cat afterPropertiesSet...");
    }

    public void destroy() throws Exception {
        System.out.println("Cat destroy...");
    }

}
