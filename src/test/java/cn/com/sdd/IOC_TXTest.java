package cn.com.sdd;

import cn.com.sdd.tx.TxConfig;
import cn.com.sdd.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName IOC_TXTest
 * @Author suidd
 * @Description 模拟声明式事务测试
 * @Date 21:20 2020/11/8
 * @Version 1.0
 **/
public class IOC_TXTest {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.addUser();
    }
}
