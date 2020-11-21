package com.magic.liuzm.question.two;

import com.magic.liuzm.question.one.User;
import lombok.extern.log4j.Log4j2;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:47
 * @description 主任角色
 */
@Log4j2
public class Director {
    /**
     * 实现方式：通过关联（Association）方式对象User扩展
     *
     * 缺点：主任升级副校长，增加能力，要改当前类；
     *
     * 解决办法：见apply-proxypattern模块的staticproxy目录
     */

    /**
     * 单向关联User
     */
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void delete(Integer id) {
        log.info("联系班主任，了解相关学生，学生id="+id);
        user.delete(id);
    }
}
