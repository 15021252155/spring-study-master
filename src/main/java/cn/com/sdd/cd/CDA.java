package cn.com.sdd.cd;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author suidd
 * @name CDA
 * @description 测试循环依赖
 * @date 2020/12/15 16:10
 * Version 1.0
 **/
public class CDA {
    @Autowired
    CDB cdb;
}