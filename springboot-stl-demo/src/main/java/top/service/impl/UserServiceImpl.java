package top.service.impl;

import top.enums.subcodes.UserSubCode;
import top.i18n.I18nKeys;
import top.mapper.UserMapper;
import top.model.entity.UserEntity;
import top.model.param.AddUserParam;
import top.model.param.UpdateUserParam;
import top.model.response.ResponseInfo;
import top.service.UserService;
import top.util.JsonUtils;
import top.util.MessageUtils;
import top.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试服务实现类
 *
 * @author lgs
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 保存用户redis的key后缀
     */
    private static final String REDIS_KEY_USER_SUFFIX = "_USER";
    /**
     * redis中用户信息过期时间
     */
    private static final Long REDIS_USER_EXPIRE_TIME = 3600000L;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ResponseInfo addUser(AddUserParam param) {
        UserEntity user = JsonUtils.copyObject(param, UserEntity.class);
        try {
            userMapper.addUser(user);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return ResponseInfo.info(MessageUtils.getMsg(I18nKeys.ADD_USER_FAILURE), UserSubCode.ADD_USER_FAILURE);
        }
        //将用户信息存入redis中（仅仅是为了用到redis，不代表实际的业务逻辑）
        redisUtils.set(user.getUid() + REDIS_KEY_USER_SUFFIX, REDIS_USER_EXPIRE_TIME);
        return ResponseInfo.ok(user, UserSubCode.ADD_USER_SUCCESS);
    }

    @Override
    public ResponseInfo findUserList(int index, int size) {
        int total = userMapper.findUserListTotal();
        if (total < 1) {
            return ResponseInfo.ok(new ArrayList<>(), 0, UserSubCode.FIND_USER_LIST_SUCCESS);
        }
        List<UserEntity> users = userMapper.findUserList(size * (index - 1), size);
        return ResponseInfo.ok(users, total, UserSubCode.FIND_USER_LIST_SUCCESS);
    }

    @Override
    public ResponseInfo updateUser(UpdateUserParam param) {
        int count = userMapper.updateUser(param);
        if (count < 1) {
            return ResponseInfo.info(MessageUtils.getMsg(I18nKeys.UPDATE_USER_FAILURE), UserSubCode.UPDATE_USER_FAILURE);
        }
        redisUtils.set(param.getUid() + REDIS_KEY_USER_SUFFIX, REDIS_USER_EXPIRE_TIME);
        return ResponseInfo.info(MessageUtils.getMsg(I18nKeys.UPDATE_USER_SUCCESS), UserSubCode.UPDATE_USER_SUCCESS);
    }

    @Override
    public ResponseInfo deleteUser(Long uid) {
        int count = userMapper.deleteUser(uid);
        if (count < 1) {
            return ResponseInfo.info(MessageUtils.getMsg(I18nKeys.DELETE_USER_FAILURE), UserSubCode.DELETE_USER_FAILURE);
        }
        redisUtils.delete(uid + REDIS_KEY_USER_SUFFIX);
        return ResponseInfo.info(MessageUtils.getMsg(I18nKeys.DELETE_USER_SUCCESS), UserSubCode.DELETE_USER_SUCCESS);
    }

}
