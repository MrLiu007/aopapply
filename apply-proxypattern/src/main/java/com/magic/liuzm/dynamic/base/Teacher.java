package com.magic.liuzm.dynamic.base;

import lombok.extern.log4j.Log4j2;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:47
 * @description 教师角色
 */
@Log4j2
public class Teacher {

    public void delete(Integer id) {
        log.info("删除用户id="+id);
    }
}
