package com.bai.controller;

import com.bai.dao.UserDao;
import com.bai.model.User;
import com.bai.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RestController
public class UserController {


   private final UserService userService;

    @GetMapping("/list")
    public List<User> userList(){
        return userService.getUserList();
    }

    @PutMapping("/add")
    public List<User> addMoney(Integer id, BigDecimal salary){
        return userService.addMoney(id,salary);
    }
}
