package com.bai.dao;

import com.bai.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UserDao {

    List<User> getUserList();

    void addUserMoneyById(@Param("id")Integer id, @Param("money")BigDecimal money);
}
