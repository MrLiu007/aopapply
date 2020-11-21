package com.magic.liuzm.log.annotation;

import com.magic.liuzm.cache.apply.BizEnum;

import java.lang.annotation.*;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 日志注解（打印调用方法出入参数+执行时间）
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 业务类型,比如用户
     */
    BizEnum bizType() default BizEnum.USER;

    /**
     * 是否开启校验，默认开启
     */
    boolean available() default true;


}
