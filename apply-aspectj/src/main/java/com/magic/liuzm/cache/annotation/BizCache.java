package com.magic.liuzm.cache.annotation;

import com.magic.liuzm.cache.apply.BizEnum;
import com.magic.liuzm.cache.enums.CacheOperateEnum;
import com.magic.liuzm.cache.enums.CacheTypeEnum;
import java.lang.annotation.*;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 缓存注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BizCache {

    /**
     * 业务类型,比如用户
     */
    BizEnum bizType() default BizEnum.USER;

    /**
     * 缓存类型,默认redis
     */
    CacheTypeEnum cacheType() default CacheTypeEnum.REDIS;

    /**
     * 操作类型,默认set
     */
    CacheOperateEnum operateType() default CacheOperateEnum.SET;

    /**
     * 开启缓存，默认开启
     */
    boolean available() default true;

    /**
     * 缓存key
     */
    String cacheKey() default "";

    /**
     * 缓存返回映射对象，默认String
     */
    Class cacheMapperClass() default String.class;

}
