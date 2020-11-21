package com.magic.liuzm.dynamic.base;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Method;

/**
 * @author zemin.liu
 * @date 2020/11/18 10:01
 * @description 增强角色对象
 */
@Log4j2
public class AspectTeacher implements AspectService {

    @Override
    public void preLog(Method method, Object[] args) {
        log.info("执行方法" + method +",参数("+ args[0] +")");
    }
}
