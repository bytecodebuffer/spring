package com.bai.service.impl;

import com.bai.dao.UserDao;
import com.bai.service.UserServiceB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserServiceBImpl implements UserServiceB {

    private final UserDao userDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ArithmeticException.class)
    @Override
    public void addMoney(Integer userId, BigDecimal salary) {
        userDao.addUserMoneyById(4, BigDecimal.valueOf(20));
        int i = 10 / 0;
    }
}
