package com.bai.service.impl;

import com.bai.dao.UserDao;
import com.bai.model.User;
import com.bai.service.UserServiceA;
import com.bai.service.UserServiceB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceAImpl implements UserServiceA {

    private final UserDao userDao;
    private final UserServiceB userServiceB;


    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = NullPointerException.class)
    @Override
    public List<User> addMoney(Integer userId, BigDecimal salary) {
        userDao.addUserMoneyById(userId,salary);
        try {
            userServiceB.addMoney(userId, BigDecimal.valueOf(20));
        }catch (Exception e){
            e.printStackTrace();
        }
        return userDao.getUserList();
    }


}
