package top.dao;

import top.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author bz
 * @date 2020/10/20
 */
@Mapper
public interface UserDao {

    /**
     * 插入用户
     * @param user
     */
    void insertUser(@Param("user") User user);

}
