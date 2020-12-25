package top.mapper;

import top.model.entity.UserEntity;
import top.model.param.UpdateUserParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户相关数据库操作
 *
 * @author lgs
 */
@Mapper
public interface UserMapper {

    /**
     * 新增用户信息
     *
     * @param user
     * @return
     */
    void addUser(UserEntity user);

    /**
     * 查询用户列表总记录数
     *
     * @return
     */
    int findUserListTotal();

    /**
     * 查询用户列表
     *
     * @param index
     * @param size
     * @return
     */
    List<UserEntity> findUserList(@Param("index") int index, @Param("size") int size);

    /**
     * 修改用户信息
     *
     * @param param
     * @return
     */
    int updateUser(UpdateUserParam param);

    /**
     * 删除用户
     *
     * @param uid
     * @return
     */
    int deleteUser(@Param("uid") Long uid);
}
