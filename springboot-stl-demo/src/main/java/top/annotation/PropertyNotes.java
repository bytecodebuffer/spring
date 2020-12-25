package top.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类型枚举类的提示
 *
 * @author lgs
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyNotes {
    /**
     * 属性上的提示
     *
     * @return
     */
    String value() default "";
}
