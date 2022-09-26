package cn.com.sdd;

import cn.com.sdd.cd.CDA;
import cn.com.sdd.cd.CDB;
import cn.com.sdd.config.MainCDConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author suidd
 * @name IOCCDTest
 * @description TODO
 * @date 2020/12/15 16:12
 * Version 1.0
 **/
public class IOCCDTest {

    @Test
    public void testcd() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainCDConfig.class);

        CDA cda = applicationContext.getBean(CDA.class);
        System.out.println(cda);
        CDB cdb = applicationContext.getBean(CDB.class);
        System.out.println(cdb);
    }
}