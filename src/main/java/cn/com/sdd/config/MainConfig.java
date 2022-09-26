package cn.com.sdd.config;

import cn.com.sdd.bean.Person;
import org.springframework.context.annotation.*;

/**
 * @ClassName MainConfig
 * @Author suidd
 * @Description 配置类==以前的配置文件
 * @Date 22:18 2020/6/3
 * @Version 1.0
 **/
@Configuration //告诉spring这是一个配置类


// includeFilters demo 要想起作用，必须配置useDefaultFilters = false
@ComponentScan(value = "cn.com.sdd", includeFilters = {
/*        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),*/
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
}, useDefaultFilters = false)


/*
// excludeFilters demo
@ComponentScan(value = "cn.com.sdd", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})
})
*/

/*
// jdk8 提供ComponentScans组件
@ComponentScans(
        value = {
                @ComponentScan(value = "cn.com.sdd", excludeFilters = {
                        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})
                })
        }
)
*/

/*
// includeFilters demo 要想起作用，必须配置useDefaultFilters = false
@ComponentScan(value = "cn.com.sdd", includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
},useDefaultFilters = false)
*/

//@ComponentScan value:指定要扫描的包
//excludeFilters=Filter[]:指定扫描包的时候按照什么规则排除哪些组件
//includeFilters=Filter[]：指定扫描包的时候只需要包含哪些组件
//FilterType.ANNOTATION:按照注解
//FilterType.ASSIGNABLE_TYPE 按照给定类型
//FilterType.ASPECTJ 使用ASPECTJ表达式
//FilterType.REGEX 使用正则表达式
//FilterType.CUSTOM 使用自定义规则
public class MainConfig {

    //默认都是单实例
    //给容器中注册一个Bean，类型为返回值的类型，id默认用方法名作为id
    @Bean("person_new")
    public Person person_new() {
        return new Person("李四", 20);
    }
}
