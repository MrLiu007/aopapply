package com.magic.liuzm.log.aop;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.magic.liuzm.exception.BizException;
import com.magic.liuzm.exception.ErrCode;
import com.magic.liuzm.log.annotation.Log;
import com.magic.liuzm.utils.AopUtil;
import com.magic.liuzm.utils.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 日志注解
 */
@Aspect
@Component
public class LogAop {

    @Pointcut("@annotation(com.magic.liuzm.log.annotation.Log)")
    public void operationLog() {}

    @Around("operationLog()")
    public Object operationLog(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        // 获取注解绑定方法
        Method method = AopUtil.getMethod(point);
        // 获得方法上相关注解
        Log log = method.getAnnotation(Log.class);
        // 是否开启限流，默认开启
        if(!log.available()){
            // 执行原方法
            return point.proceed();
        }
        Object result = null;
        Integer errorCode = null;
        String errorMsg = null;
        // 当前类
        String className = point.getTarget().getClass().getName();
        // 当前方法名
        String methodName = ((MethodSignature)point.getSignature()).getMethod().getName();
        // 当前方法参数
        Object[] args = point.getArgs();
        // 入参数打印
        LogUtil.printBeforeLog(log.bizType().getDesc(),className,methodName,parseArgs(args));
        try {
            // 执行业务方法
            result = point.proceed();
        }catch (Exception e) {
            // 捕获原方法异常
            if(e instanceof BizException) {
                // 业务异常
                errorCode = ((BizException)e).getErrCode();
                errorMsg = ((BizException)e).getErrMsg();
            }else {
                errorCode = ErrCode.SYSTEM_ERROR.getCode();
                errorMsg = ErrCode.SYSTEM_ERROR.getMessage();
            }
            // 重新扔出异常，方便统一异常捕获
            throw e;
        }finally {
            long end = System.currentTimeMillis();
            // 出参数打印
            LogUtil.printAfterLog(log.bizType().getDesc(),className,methodName,parseArgs(args),paresResult(result), errorCode,
                    errorMsg, (end - start));
        }
        return result;
    }


    private String parseArgs(Object[] args) {
        if(args == null || args.length == 0) {
            return "";
        }
        List<Object> logArgs = Lists.newArrayList(args);
        Iterator iterator = logArgs.listIterator();
        while (iterator.hasNext()){
            Object object = iterator.next();
            if (object instanceof HttpServletRequest
                    || object instanceof HttpServletResponse){
                // 过滤掉HttpServletRequest和HttpServletResponse
                iterator.remove();
            }
            logArgs.add(object);
        }
        return JSON.toJSONString(logArgs);
    }

    private String paresResult(Object result) {
        if(result == null){
            return "";
        }
        return JSON.toJSONString(result);
    }
}
