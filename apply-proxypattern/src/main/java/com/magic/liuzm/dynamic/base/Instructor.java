package com.magic.liuzm.dynamic.base;

import lombok.extern.log4j.Log4j2;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:03
 * @description 真实角色对象
 */
@Log4j2
public class Instructor implements UserService{

    @Override
    public void delete(Integer id) {
        log.info("删除用户id="+id);
    }
}
