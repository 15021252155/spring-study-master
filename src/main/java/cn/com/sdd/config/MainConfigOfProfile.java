package cn.com.sdd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * @ClassName MainConfigOfProfile
 * @Author suidd
 * @Description profile:
 * Spring为我们提供的可以根据当前环境，动态激活和切换一系列组件的功能
 * 比如：开发环境、测试环境、生产环境
 * 数据源:/A /B /C
 *
 * @profile:指定组件在哪个环境下才能被注册到容器中，不指定，任何情况下都能注册这个组件
 * 1）、加了@profile的bean，只有这个环境被激活的时候才能注册到这个容器中
 * 2)、加在类上，只有激活指定环境的时候，整个配置类里的所有配置才开始生效
 * 3）、没有指定环境标识的，任何情况下都能注册
 * @Date 22:51 2020/11/4
 * @Version 1.0
 **/
@Profile("test")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private String user;

    private StringValueResolver stringValueResolver;

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password }") String pwd) {
        String s = stringValueResolver.resolveStringValue("db.driverClass");
        return null;
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest() {
        return null;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd() {
        return null;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        stringValueResolver = resolver;
    }
}
