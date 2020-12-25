package top.validator.protocol;

import top.validator.impl.EnumValidImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 自定义枚举校验注解
 *
 * @author: lgs
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumValidImpl.class)
public @interface EnumValid {
    /**
     * 提示信息
     *
     * @return
     */
    String message() default "参数错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 校验枚举类
     *
     * @return
     */
    Class<?> enumTypeClass();

    /**
     * 是否需要校验（如果单纯只是想作为swagger提示展示的时候就设置为false）
     *
     * @return
     */
    boolean doValid() default true;

    /**
     * 是否允许为空
     *
     * @return
     */
    boolean allowNull() default false;
}
