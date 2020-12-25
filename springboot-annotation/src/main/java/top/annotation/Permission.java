package top.annotation;

import java.lang.annotation.*;

/**
 * @author bz
 * @date 2020/11/11
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    String name() default "";

    String value() default "";
}
