package com.magic.liuzm.dynamic.cglib;

import com.magic.liuzm.dynamic.base.AspectService;
import lombok.extern.log4j.Log4j2;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:47
 * @description 方法拦截器
 */
@Log4j2
public class DynamicMethodInterceptor implements MethodInterceptor {

    /**
     * 真实角色对象
     */
    private Object target;

    /**
     * 增强角色对象
     */
    private AspectService aspect;

    public void setTarget(Object target) {
        this.target = target;
    }

    public void setAspect(AspectService aspect) {
        this.aspect = aspect;
    }

    /**
     * 次要方法书写 + 执行主要业务
     *
     * @param obj  真实角色对象
     * @param method 真实角色对象方法
     * @param args 真实角色对象方法参数
     * @param methodProxy 真实角色对象方法的最终代理
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept (Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 次要业务：执行业务方法之前添加日志
        aspect.preLog(method,args);
        // 主要业务：使用反射机制执行方法
        Object returnVal = method.invoke(target,args);
        return returnVal;
    }
}

