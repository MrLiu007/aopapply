package com.magic.liuzm.lock.annotation;

import com.magic.liuzm.cache.apply.BizEnum;
import java.lang.annotation.*;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 业务lock注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock {

    /**
     * 业务类型,比如用户
     */
    BizEnum bizType() default BizEnum.USER;

    /**
     * 开启缓存，默认开启
     */
    boolean available() default true;

    /**
     * 参数名称用.表示层级，最多支持2级
     * 如： user.userId
     */
    String argName();

    /**
     * 自定义key前缀
     */
    String prefix() default "";

    /**
     * lock有效时间（单位s）
     */
    int expire() default 60;
}
