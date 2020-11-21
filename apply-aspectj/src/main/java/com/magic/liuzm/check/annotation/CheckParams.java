package com.magic.liuzm.check.annotation;

import java.lang.annotation.*;


/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 通用接口参数验证（组合）注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckParams {

    /**
     * 组合CheckParam，由上往下判断
     */
    CheckParam[] value();
}