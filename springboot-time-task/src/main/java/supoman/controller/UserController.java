package supoman.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import supoman.dao.UserDao;

import java.util.List;

/**
 * @author bz
 * @date 2020/11/9
 */
@Slf4j
@RestController
public class UserController {


    @Autowired
    private UserDao userDao;



    @GetMapping("/user")
    public void getUserName(){
        List<String> name = userDao.getUserName1();
    }
}
