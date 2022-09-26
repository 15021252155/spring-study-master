package cn.com.sdd.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyApplicationListener
 * @Author suidd
 * @Description ApplicationListener测试
 * @Date 23:00 2020/11/9
 * @Version 1.0
 **/
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    //当容器发布事件以后，方法触发
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("收到事件" + event);
    }
}
