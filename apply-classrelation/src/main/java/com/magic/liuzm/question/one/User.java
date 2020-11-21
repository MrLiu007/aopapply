package com.magic.liuzm.question.one;

import lombok.extern.log4j.Log4j2;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:47
 * @description 普通用户角色
 */
@Log4j2
public class User {

    // 删除用户操作怎么扩展？
    public void delete(Integer id) {
        log.info("删除用户，id="+id);
    }
}
