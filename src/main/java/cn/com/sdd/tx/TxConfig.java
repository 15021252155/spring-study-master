package cn.com.sdd.tx;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

/**
 * @ClassName TxConfig
 * @Author suidd
 * @Description 声明式事务配置类
 * <p>
 * 声明式事务：
 * 环境搭建：
 * 1、导入相关依赖
 * 数据源、数据库驱动、spring-jdbc模块
 * 2、配置数据源、JDBCTemplate(spring提供的简化数据库操作的工具)操作数据
 * 3、给方法上标注@Transactional，表示当前方法是一个事务
 * 4、配置@EnableTransactionManagement 开启基于注解的事务管理功能
 * 5、配置事务管理器控制事务
 *
 *
 * 事务原理：
 * 1）、@EnableTransactionManagement
 *      利用Import注解注册一个TransactionManagementConfigurationSelector组件
 *      导入两个组件
 *      AutoProxyRegistrar
 *      ProxyTransactionManagementConfiguration
 *
 * 2）、AutoProxyRegistrar做了什么？
 *      给容器中注册一个InfrastructureAdvisorAutoProxyCreator组件
 *      =>分析InfrastructureAdvisorAutoProxyCreator的功能
 *      利用后置处理器机制在对象创建以后，包装对象，返回一个代理对象（增强器），代理对象执行方法，利用拦截器链调用
 *
 * 3）、ProxyTransactionManagementConfiguration做了什么？
 *      1）、给容器中注册事务增强器；
 *          ①、设置事务增强器要用什么解析事务，AnnotationTransactionAttributeSource解析事务注解
 *          ②、事务拦截器：
 *              TransactionInterceptor：保存了事务属性信息、事务管理器;
 *              TransactionInterceptor是一个MethodInterceptor
 *              invoke => invokeWithinTransaction
 *              在目标方法执行的时候：
 *                  执行拦截器链；
 *                  事务拦截器：
 *                      1）、先获取事务相关属性
 *                      2）、再获取PlatformTransactionManager，如果事先没有添加指定任何transactionManager
 *                          最终会从容器中按照类型获取一个PlatformTransactionManager
 *                      3）、执行目标方法
 *                          如果异常，获取到事务管理器，利用事务管理器回滚操作；
 *                          如果正常，利用事务管理器，提交事务
 *
 *
 * @Date 21:31 2020/11/8
 * @Version 1.0
 **/
@Configuration
@ComponentScan("cn.com.sdd.tx")
@PropertySource(value = {"classpath:/application.properties"})
@EnableTransactionManagement
public class TxConfig {
    @Value("${spring.datasource.url}")
    private String databaseUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DruidDataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.init();
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws SQLException {
        return new JdbcTemplate(dataSource());
    }

    //注册事务管理器到容器中
    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }
}
