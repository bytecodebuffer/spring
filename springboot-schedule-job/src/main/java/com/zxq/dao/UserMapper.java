package com.zxq.dao;


import com.zxq.model.po.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zxq
 */
public interface UserMapper extends Mapper<User> {

    /**
     * 查找用户
     * @param username
     * @param password
     * @return
     */
    User getUser(@Param("username")String username,String password);
}