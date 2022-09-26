package cn.com.sdd.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserService
 * @Author suidd
 * @Description 模拟用户服务
 * @Date 20:55 2020/11/8
 * @Version 1.0
 **/
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    public void addUser() {
        userDao.insert();
        System.out.println("插入用户成功...");
        int i = 10 / 0;//模拟发生异常，应该事务回滚
    }
}
