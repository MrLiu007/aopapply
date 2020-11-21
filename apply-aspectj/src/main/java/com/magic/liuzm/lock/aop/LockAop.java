package com.magic.liuzm.lock.aop;

import com.alibaba.druid.util.StringUtils;
import com.magic.liuzm.exception.BizException;
import com.magic.liuzm.exception.ErrCode;
import com.magic.liuzm.lock.RedisLockDao;
import com.magic.liuzm.lock.annotation.Lock;
import com.magic.liuzm.utils.AopUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 业务锁切面类
 */
@Aspect
@Component
public class LockAop {

    /**
     * 锁前缀
     */
    private static final String LOCK_PREFIX = "apply:lock:";

    @Autowired
    private RedisLockDao redisLockDao;

    @Pointcut("@annotation(com.magic.liuzm.lock.annotation.Lock)")
    public void operationLock() {}

    @Around("operationLock()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 获取注解绑定方法
        Method method = AopUtil.getMethod(point);
        // 参数key数组
        String[] paramName = AopUtil.getParamName(point);
        // 参数value数组
        Object[] arguments = point.getArgs();
        // 获得方法上相关注解
        Lock lock = method.getAnnotation(Lock.class);
        // 设置key的对象属性（或属性）
        String argName = lock.argName();
        // 设置key的对象属性值
        String value = (String) AopUtil.getParamValue(paramName, arguments, argName);

        StringBuffer key = new StringBuffer(LOCK_PREFIX);
        if(!StringUtils.isEmpty(lock.prefix())){
            key.append(lock.prefix()).append(value);
        }else {
            key.append(lock.prefix());
        }
        // 业务执行前上锁
        boolean isLock = redisLockDao.lock(key.toString(),value,lock.expire());
        if(!isLock){
            // 这里是直接抛异常，可用全局异常捕获，返回对应error code/msg
            throw new BizException(ErrCode.BIZ_LOCKED.getCode(),ErrCode.BIZ_LOCKED.getMessage());
        }
        try {
            // 执行原业务逻辑
            return point.proceed();
        }finally {
            // 执行后释放锁
            redisLockDao.unlock(key.toString(),value);
        }
    }
}
