package top.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * swagger文档说明注解
 *
 * @author lgs
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerNotes {
    /**
     * 标题提示
     */
    String tip() default "subCode说明：";

    /**
     * 枚举实例名称前缀
     */
    String[] enums() default "";

    /**
     * subCode类型，指明SubCodeEnum的具体对象
     *
     * @return
     */
    Class[] subCodeType();

    /**
     * 接口提示信息
     *
     * @return
     */
    String title() default "";

    String[] codeType() default "";
}
