package cn.com.sdd.config;

import cn.com.sdd.cd.CDA;
import cn.com.sdd.cd.CDB;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author suidd
 * @name MainCDConfig
 * @description
 * 三级缓存解决循环依赖问题。
 * 如果仅仅是解决循环依赖问题，二级缓存也可以，但是如果注入的对象实现了AOP，那么注入到其他bean的时候，不是最终的代理对象，而是原始的。
 * 通过三级缓存的ObjectFactory才能实现类最终的代理对象。
 * @date 2020/12/15 16:11
 * Version 1.0
 **/
@Configuration
@Import({CDA.class, CDB.class})
public class MainCDConfig {
}