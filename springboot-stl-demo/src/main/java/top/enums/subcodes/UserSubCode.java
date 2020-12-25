package top.enums.subcodes;

import top.enums.SubCodeEnum;

/**
 * 用户相关subCode
 *
 * @author lgs
 */

public enum UserSubCode implements SubCodeEnum {

    ADD_USER_SUCCESS("1402100", "新增用户成功"),
    ADD_USER_FAILURE("1402101", "新增用户失败"),

    FIND_USER_LIST_SUCCESS("1402200", "查询用户列表成功"),
    FIND_USER_LIST_FAILURE("1402201", "查询用户列表成功"),

    UPDATE_USER_SUCCESS("1402300", "修改用户成功"),
    UPDATE_USER_FAILURE("1402301", "修改用户失败"),

    DELETE_USER_SUCCESS("1402400", "删除用户成功"),
    DELETE_USER_FAILURE("1402401", "删除用户失败");

    String subCode;
    String desc;

    UserSubCode(String subCode, String desc) {
        this.subCode = subCode;
        this.desc = desc;
    }

    @Override
    public String getSubCode() {
        return this.subCode;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}