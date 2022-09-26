package cn.com.sdd.dao;

import org.springframework.stereotype.Repository;

/**
 * @ClassName BookDao
 * @Author suidd
 * @Description TODO
 * @Date 10:08 2020/6/6
 * @Version 1.0
 **/
@Repository
//默认使用的名称是类名首字母小写的形式
public class BookDao {
    private String lable="1";

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "lable='" + lable + '\'' +
                '}';
    }
}
