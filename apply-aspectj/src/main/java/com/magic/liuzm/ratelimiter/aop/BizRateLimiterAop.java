package com.magic.liuzm.ratelimiter.aop;

import com.google.common.collect.Maps;
import com.magic.liuzm.ratelimiter.annotation.RateLimiter;
import com.magic.liuzm.response.R;
import com.magic.liuzm.utils.AopUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 业务限流切面类
 */
@Aspect
@Component
public class BizRateLimiterAop {

    /**
     * key:bizKey code + apiName
     *
     * value:对应限流器
     */
    private final Map<String, com.google.common.util.concurrent.RateLimiter> limiterMap = Maps.newConcurrentMap();

    @Pointcut("@annotation(com.magic.liuzm.ratelimiter.annotation.RateLimiter)")
    public void operationRate() {}

    @Around("operationRate()")
    public Object operationRate(ProceedingJoinPoint point) throws Throwable {
        // 获取注解绑定方法
        Method method = AopUtil.getMethod(point);
        // 获得方法上相关注解
        RateLimiter rateLimiter = method.getAnnotation(RateLimiter.class);
        // 是否开启限流，默认开启
        if(!rateLimiter.available()){
            // 执行原方法
            return point.proceed();
        }
        com.google.common.util.concurrent.RateLimiter limiter;
        String key = String.join(rateLimiter.bizType().getCode(),rateLimiter.apiName());
        if(!limiterMap.containsKey(key)) {
            // 绑定对应限速器
            limiter = com.google.common.util.concurrent.RateLimiter.create(rateLimiter.rate());
            limiterMap.put(key,limiter);
        }else {
            // 返回对应的限速器
            limiter = limiterMap.get(key);
        }
        // 尝试获取令牌
        boolean result = limiter.tryAcquire(rateLimiter.timeout(), TimeUnit.MILLISECONDS);
        if (!result) {
            // 获取令牌失败,直接返回response，不进入原方法
            return R.fail(rateLimiter.hitMsg());
        }
        // 执行原方法
        return point.proceed();
    }
}
