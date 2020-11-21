package com.magic.liuzm.ratelimiter.annotation;

import com.magic.liuzm.cache.apply.BizEnum;
import java.lang.annotation.*;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 令牌桶限流（单机）注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {

    /**
     * 业务类型,比如用户
     */
    BizEnum bizType() default BizEnum.USER;

    /**
     * api名称
     */
    String apiName();

    /**
     * 是否开启校验，默认开启
     */
    boolean available() default true;

    /**
     * QPS,每秒往令牌桶添加令牌数据
     */
    double rate() default 1.0;

    /**
     * 获取令牌最大等待时间，单位毫秒
     */
    long timeout() default 0;

    /**
     * 获令牌失败时提示语
     */
    String hitMsg() default "系统繁忙,请稍后再试!";

}
