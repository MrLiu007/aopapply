package com.magic.liuzm.datasource.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author zemin.liu
 * @date 2021/6/28 12:35
 * @description aop 常用操作工具类
 */
public class AopUtil {

    /**
     * 获取注解绑定方法
     *
     * @param joinPoint ProceedingJoinPoint
     * @return 方法
     */
    public static Method getMethod(JoinPoint joinPoint) {
        // jdk 1.8 特性
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 若为接口方法
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint
                        .getTarget()
                        .getClass()
                        .getDeclaredMethod(joinPoint.getSignature().getName(),
                                method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return method;
    }

    /**
     * 获取参数名称
     * @param joinPoint
     * @return
     */
    public static String[] getParamName(JoinPoint joinPoint) {
        // jdk 1.8 特性
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] strings = methodSignature.getParameterNames();
        return strings;
    }

    /**
     * 根据参数名称，获取参数值
     *
     * @param paramName 参数key数组
     * @param arguments 参数value数组
     * @param argName 需验证对象属性（或属性）
     * @return 参数value
     */
    public static Object getParamValue(String[] paramName,Object[] arguments, String argName) {
        Object value = null;
        String name = argName;
        // 若有下一级，先取出第一层属性
        if(argName.contains(".")) {
            name = argName.split("\\.")[0];
        }
        // 循环参数key,进行匹配
        int index = 0;
        for (String parm : paramName) {
            // 注解配置的参数名称不存在，则返回null
            if(parm.equals(name)) {
                value = arguments[index];
                break;
            }
            index++;
        }
        // 若有下一级，再取出第二层属性
        if(argName.contains(".")) {
            // 从对象中取值
            argName = argName.split("\\.")[1];
            // 用instanceof 判断入参数为json格式
            if (value instanceof JSON) {
                // 参数value为json
                JSONObject jo = (JSONObject) JSONObject.toJSON(value);
                value = jo.get(argName);
            }else{
                // 参数value为实体
                value = ReflectUtil.getPropertyValue(value,argName);
            }
        }
        return value;
    }
}
