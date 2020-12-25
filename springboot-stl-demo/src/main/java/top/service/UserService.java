package top.service;

import top.model.param.AddUserParam;
import top.model.param.UpdateUserParam;
import top.model.response.ResponseInfo;

/**
 * 测试服务接口
 *
 * @author lgs
 */
public interface UserService {

    /**
     * 插入数据测试
     *
     * @param param
     * @return
     */
    ResponseInfo addUser(AddUserParam param);

    /**
     * 查询用户列表
     *
     * @param index
     * @param size
     * @return
     */
    ResponseInfo findUserList(int index, int size);

    /**
     * 修改用户信息
     *
     * @param param
     * @return
     */
    ResponseInfo updateUser(UpdateUserParam param);

    /**
     * 删除用户
     *
     * @param uid
     * @return
     */
    ResponseInfo deleteUser(Long uid);
}
