package top.enums.subcodes;

import top.enums.SubCodeEnum;

/**
 * 系统相关subCode
 *
 * @author lgs
 */

public enum SystemSubCode implements SubCodeEnum {
    UNKNOWN_EXCEPTION("000000", "未知异常"),
    RUNTIME_EXCEPTION("000001", "运行时异常"),
    NULL_POINTER_EXCEPTION("000002", "空指针异常"),
    CLASS_CAST_EXCEPTION("000003", "类型转换异常"),
    IO_EXCEPTION("000004", "IO异常"),
    INDEX_OUT_BOUND_EXCEPTION("000005", "数组越界异常"),
    METHOD_NOT_SUPPORTED_EXCEPTION("000006", "请求方法不支持"),
    MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION("000007", "请求参数格式不支持"),
    PARAM_EXCEPTION("000008", "请求参数异常"),
    AUTHORIZATION_REQUEST_EXCEPTION("000009", "鉴权-非法请求"),
    APPLICATION_BUSY("000010", "服务器限制时间内无法响应"),
    QUARTZ_EXCEPTION("000011", "定时任务异常"),
    SERVICE_CALL_ERROR("000012", "服务调用失败"),
    GATEWAY_FORWARDING_ERROR("000013", "网关转发异常");

    String subCode;
    String desc;

    SystemSubCode(String subCode, String desc) {
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