package com.magic.liuzm.dynamic.javaassist;

import com.magic.liuzm.dynamic.base.AspectService;
import javassist.util.proxy.MethodHandler;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Method;

/**
 * @author zemin.liu
 * @date 2020/11/17 18:02
 * @description 方法处理器
 */
@Log4j2
public class DynamicMethodHandler implements MethodHandler {

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
     * @param proxy 真实角色对象的代理
     * @param method 真实角色对象方法
     * @param proxyMethod 真实角色对象方法的代理
     * @param args 真实角色对象方法参数
     * @return java.lang.Object
     */
    @Override
    public Object invoke(Object proxy, Method method, Method proxyMethod, Object[] args) throws Throwable {
        // 次要业务：执行业务方法之前添加日志
        aspect.preLog(method,args);
        // 主要业务：使用反射机制执行方法
        Object returnVal = method.invoke(target,args);
        return returnVal;
    }
}
