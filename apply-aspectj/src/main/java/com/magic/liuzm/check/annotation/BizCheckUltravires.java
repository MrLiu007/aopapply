package com.magic.liuzm.check.annotation;

import com.magic.liuzm.check.apply.BizCheckUltraviresProcessor;
import java.lang.annotation.*;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 自定义业务权限检查注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BizCheckUltravires {

    /**
     * 是否开启校验，默认开启
     */
    boolean available() default true;

    /**
     * 具体业务处理器
     */
    Class<? extends BizCheckUltraviresProcessor> processor();
}