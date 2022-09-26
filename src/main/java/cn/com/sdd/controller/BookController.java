package cn.com.sdd.controller;

import cn.com.sdd.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @ClassName BookController
 * @Author suidd
 * @Description TODO
 * @Date 10:07 2020/6/6
 * @Version 1.0
 **/
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
}
