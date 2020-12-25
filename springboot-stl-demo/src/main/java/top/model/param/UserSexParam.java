package top.model.param;

import top.enums.types.SexEnum;
import top.validator.protocol.EnumValid;

/**
 * @author bz
 * @date 2020/9/7
 */
public class UserSexParam {
    @EnumValid(enumTypeClass = SexEnum.class)
    private Integer sex;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
