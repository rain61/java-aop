package com.ms.aop.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huahua
 *
 * Target 声明这是一个自定义注解类，ElementType.METHOD 表明此注解可声明在方法上
 * Retention 声明注解保留期限，RetentionPolicy.RUNTIME 表明此注解可保留至运行时，可以通过反射获取注解信息
 *
 *ExceptionAopNote 是一个异常注释 用于收集异常
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionCatch {

    String param() default "";
}
