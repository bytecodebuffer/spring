package top.annotation;

import java.lang.annotation.*;

/**
 *
 * 自定义注解
 *
 * @author bz
 * @date 2020/11/11
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyLog {

}
