package com.magic.liuzm.check.aop;

import com.magic.liuzm.check.annotation.BizCheckParam;
import com.magic.liuzm.check.annotation.BizCheckUltravires;
import com.magic.liuzm.check.apply.BizCheckUltraviresProcessor;
import com.magic.liuzm.exception.BizException;
import com.magic.liuzm.exception.ErrCode;
import com.magic.liuzm.utils.AopUtil;
import com.magic.liuzm.utils.SpringContextUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 业务权限校验切面类
 */
@Aspect
@Component
public class BizCheckUltraviresAop {

    @Pointcut("@annotation(com.magic.liuzm.check.annotation.BizCheckUltravires)")
    public void operationCheckUltravires() {}

    @Around("operationCheckUltravires()")
    public Object operationCheckUltravires(ProceedingJoinPoint point) throws Throwable {
        // 获取注解绑定方法
        Method method = AopUtil.getMethod(point);
        // 获得方法上相关注解
        BizCheckUltravires bizCheckUltravires = method.getAnnotation(BizCheckUltravires.class);
        // 是否开启校验，默认开启
        if(!bizCheckUltravires.available()){
            // 执行原方法
            return point.proceed();
        }
        // 方法的参数value数组
        Object[] obj = point.getArgs();
        // 具体业务check权限实现类
        BizCheckUltraviresProcessor processor = SpringContextUtils.getBean(bizCheckUltravires.processor());
        if(processor.checkDo(obj)) {
            // 有权限，则执行原方法
            return point.proceed();
        }else {
            throw new BizException(ErrCode.BIZ_ULTRA_VIRES_INVALID);
        }
    }
}
