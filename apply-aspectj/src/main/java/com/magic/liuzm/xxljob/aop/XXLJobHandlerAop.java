package com.magic.liuzm.xxljob.aop;

import com.magic.liuzm.utils.TraceUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zemin.liu
 * @date 2020/10/21 13:40
 * @description 为xxl-job执行器添加traceId
 */
@Aspect
@Component
public class XXLJobHandlerAop {

    @Pointcut("@annotation(com.xxl.job.core.handler.annotation.XxlJob)")
    public void handlerPointCut() {
        // 切入点，注意若xxlJob换新注解，这里需改动
    }

    @Around("handlerPointCut()")
    public Object aroundDo(ProceedingJoinPoint point) throws Throwable {
        // 执行方法之前：设置traceId到logBack的MDC中
        TraceUtil.resetTraceID();
        // 执行原方法
        Object result = point.proceed();
        // 执行方法之后：从logBack的MDC中删除traceId
        TraceUtil.removeTraceID();
        return result;
    }
}