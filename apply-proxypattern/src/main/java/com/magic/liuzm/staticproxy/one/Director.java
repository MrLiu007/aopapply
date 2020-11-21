package com.magic.liuzm.staticproxy.one;

import lombok.extern.log4j.Log4j2;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:47
 * @description 主任角色
 */
@Log4j2
public class Director implements Right {

    /**
     * 单向关联User
     */
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void delete(Integer id) {
        log.info("联系班主任，了解相关学生，学生id="+id);
        user.delete(id);
    }
}
