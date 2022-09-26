package cn.com.sdd.config;

import cn.com.sdd.bean.Person;
import cn.com.sdd.condition.LinuxCondition;
import cn.com.sdd.condition.MacCondition;
import cn.com.sdd.condition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MainConfigConditional
 * @Author suidd
 * @Description 学习@Conditional注解
 * 根据当前操作系统类型注入不同的bean
 * windows系统：注入@Bean("bill")
 * linux系统：注入@Bean("linus")
 * @Date 22:02 2020/11/1
 * @Version 1.0
 **/
@Configuration
//类统一设置 放在类级别，只有当前条件满足了，该类下的所有bean才会注册成功
//@Conditional(LinuxCondition.class)
public class MainConfigConditional {
    /**
     * @return
     * @Conditional({Condition}) :按照一定的条件判断，满足条件才会IOC容器注册bean
     */
    //放在方法层注解
    @Conditional(WindowsCondition.class)
    @Bean("bill")
    public Person getPersonBill() {
        return new Person("Bill", 62);
    }

    @Conditional(LinuxCondition.class)
    @Bean("linus")
    public Person getPersonLinus() {
        return new Person("linus", 48);
    }

    @Conditional(MacCondition.class)
    @Bean("jobs")
    public Person getPersonJobs() {
        return new Person("jobs", 88);
    }
}
