package com.bai.service.impl;

import com.bai.dao.UserDao;
import com.bai.model.User;
import com.bai.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;


    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Transactional(isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
    @Override
    public List<User> addMoney(Integer userId, BigDecimal salary) {
        userDao.addUserMoneyById(userId,salary);
        addMoney20(userId);
        return userDao.getUserList();
    }

    public void addMoney20(Integer id){
        int i = 10/0;
        userDao.addUserMoneyById(id,BigDecimal.valueOf(20));
    }
}
