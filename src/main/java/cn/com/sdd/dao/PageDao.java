package cn.com.sdd.dao;

/**
 * @ClassName PageDao
 * @Author suidd
 * @Description TODO
 * @Date 23:20 2020/11/3
 * @Version 1.0
 **/
//测试使用，不进行标签注入
public class PageDao {
    private String lable="1";

    public String getLable() {
        return lable;
    }

    @Override
    public String toString() {
        return "PageDao{" +
                "lable='" + lable + '\'' +
                '}';
    }
}
