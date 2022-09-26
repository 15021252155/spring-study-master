package cn.com.sdd.service;

import cn.com.sdd.dao.BookDao;
import cn.com.sdd.dao.PageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @ClassName BookService
 * @Author suidd
 * @Description TODO
 * @Date 10:08 2020/6/6
 * @Version 1.0
 **/
@Service
public class BookService {

    //Qualifier指定id注入，虽然写的名字是bookDao2，但通过指定 @Qualifier("bookDao")，查找的是bookDao
    // @Qualifier("bookDao")
    //@Autowired
    //@Resource
    //@Resource(name="bookDao2")
    @Inject
    private BookDao bookDao;

    //IOC容器中
    @Autowired(required = false)
    private PageDao pageDao;


    private void print() {
        System.out.println(bookDao);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao +
                '}';
    }
}
