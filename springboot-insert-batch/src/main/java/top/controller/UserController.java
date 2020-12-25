package top.controller;

import top.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import top.dao.UserDao;


/**
 * @author bz
 * @date 2020/10/20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;
    private static final Integer SIZE = 100_000;

    /**
     * 插入 10w 条数据
     * @return
     */
    @GetMapping("/insertBatch1")
    public List<User> insertBatch1(){
        Long startTime = System.currentTimeMillis();
        List<User> list = new ArrayList<>();
        for(Integer i=0;i<SIZE;i++){
            User user  = new User();
            userDao.insertUser(user);
            list.add(user);
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时："+(endTime-startTime)/1000);
        return list;
    }

}
