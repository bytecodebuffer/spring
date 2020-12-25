package top.validator.impl;

import top.enums.PropertyTypeEnum;
import top.validator.protocol.EnumValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义枚举校验规则
 *
 * @author: lgs
 */
public class EnumValidImpl implements ConstraintValidator<EnumValid, Integer> {

    private EnumValid constraintAnnotation;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Integer o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null || o.intValue() == 0) {
            return constraintAnnotation.allowNull();
        }
        Class cls = this.constraintAnnotation.enumTypeClass();
        boolean doValid = this.constraintAnnotation.doValid();
        if (PropertyTypeEnum.class.isAssignableFrom(cls)) {
            PropertyTypeEnum[] objects = (PropertyTypeEnum[]) cls.getEnumConstants();
            if (!doValid) {
                return true;
            }
            for (PropertyTypeEnum itm : objects) {
                if (o.equals(itm.getCode())) {
                    return true;
                }
            }
        }
        return false;
    }
}
