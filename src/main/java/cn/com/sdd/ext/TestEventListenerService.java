package cn.com.sdd.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @ClassName TestEventListenerService
 * @Author suidd
 * @Description 测试使用@EventListener
 * @Date 21:58 2020/11/10
 * @Version 1.0
 **/
@Service
public class TestEventListenerService {

    @EventListener(classes = {ApplicationEvent.class})
    public void listener(ApplicationEvent event) {
        System.out.println("TestEventListenerService...监听到事件" + event);
    }
}
