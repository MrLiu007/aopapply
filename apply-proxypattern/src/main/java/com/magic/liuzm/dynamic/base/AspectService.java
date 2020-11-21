package com.magic.liuzm.dynamic.base;

import java.lang.reflect.Method;

/**
 * @author zemin.liu
 * @date 2020/11/18 10:01
 * @description 抽象增强角色对象（接口）
 */
public interface AspectService {

    void preLog(Method method, Object[] args);
}
