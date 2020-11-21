package com.magic.liuzm.dynamic.jdk;

import com.magic.liuzm.dynamic.base.AspectService;
import lombok.extern.log4j.Log4j2;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:06
 * @description 执行器对象
 */
@Log4j2
public class DynamicInvocationHandler implements InvocationHandler {

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
     * @param proxy 调用invoke方法的代理实例
     * @param method 真实角色对象方法
     * @param args 真实角色对象方法参数
     * @return java.lang.Object
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 次要业务：执行业务方法前打印日志
        aspect.preLog(method,args);
        // 主要业务：使用反射机制执行方法
        Object result = method.invoke(target,args);
        return result;
    }
}
