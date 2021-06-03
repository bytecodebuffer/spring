package com.bai.service;

import com.bai.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    List<User> getUserList();

    List<User> addMoney(Integer userId, BigDecimal salary);
}
