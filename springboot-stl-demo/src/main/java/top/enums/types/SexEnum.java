package top.enums.types;

import top.annotation.PropertyNotes;
import top.enums.PropertyTypeEnum;

/**
 * 性别枚举
 *
 * @author lgs
 */
public enum SexEnum implements PropertyTypeEnum {

    @PropertyNotes("男")
    MALE(1),
    @PropertyNotes("女")
    FEMALE(2),
    @PropertyNotes("其他")
    OTHER(3);

    private Integer code;

    SexEnum(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

}
