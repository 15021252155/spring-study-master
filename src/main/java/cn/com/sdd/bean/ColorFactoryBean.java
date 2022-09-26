package cn.com.sdd.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @ClassName ColorFactoryBean
 * @Author suidd
 * @Description 自定义colorFactoryBean
 * @Date 21:36 2020/11/2
 * @Version 1.0
 **/
public class ColorFactoryBean implements FactoryBean<Color> {
    /**
     * 返回一个Color对象，这个对象会添加到容器中
     *
     * @return
     * @throws Exception
     */
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean getObject ...");
        return new Color();
    }

    public Class<?> getObjectType() {
        return null;
    }

    //是否是单例模式？
    //true：单例模式
    //false：多实例模式
    public boolean isSingleton() {
        return true;
    }
}
