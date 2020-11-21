package com.magic.liuzm.question.one.means3;

import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:47
 * @description 普通用户角色（变种）
 */
@Log4j2
public class UserModify {

    public void delete(List<Integer> list) {
        list.forEach(id ->{
            log.info("删除用户，id="+id);
        });
    }
}
