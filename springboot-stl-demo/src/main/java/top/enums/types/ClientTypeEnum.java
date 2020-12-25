package top.enums.types;

import top.annotation.PropertyNotes;
import top.enums.PropertyTypeEnum;

/**
 * 客户端类型枚举
 *
 * @author lgs
 */
public enum ClientTypeEnum implements PropertyTypeEnum {

    @PropertyNotes("全部")
    ALL(0),
    @PropertyNotes("iOS")
    IOS(1),
    @PropertyNotes("安卓")
    ANDROID(2),
    @PropertyNotes("WPC")
    WPC(3),
    @PropertyNotes("web")
    WEB(4),
    @PropertyNotes("server")
    SERVER(5),
    @PropertyNotes("EAPC")
    EAPC(6),
    @PropertyNotes("内容账户")
    CONTENT_ACCOUNT(7);

    private Integer code;

    ClientTypeEnum(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
