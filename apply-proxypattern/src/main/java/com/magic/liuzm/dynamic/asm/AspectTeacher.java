package com.magic.liuzm.dynamic.asm;

import lombok.extern.log4j.Log4j2;
/**
 * @author zemin.liu
 * @date 2020/11/18 10:01
 * @description 增强角色对象
 */
@Log4j2
public class AspectTeacher {

    public void preLog(String methodName, Integer id) {
        log.info("执行方法" + methodName +",参数("+ id +")");
    }
}
