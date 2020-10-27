package com.admin.aop;

import java.lang.annotation.*;

/**
 * @ClassName 类名：LogAopAnnotation
 * @Description 功能说明：日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogAopAnnotation {
    String name() default "";
}
