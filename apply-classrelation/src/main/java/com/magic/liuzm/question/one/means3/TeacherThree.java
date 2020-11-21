package com.magic.liuzm.question.one.means3;

import com.magic.liuzm.question.one.User;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:47
 * @description 教师角色
 */
@Log4j2
public class TeacherThree {
    /**
     * 实现方式：通过关联（Association）方式对象User扩展
     *
     * 缺点：还是要改当前类
     */

    /**
     * 单向关联User
     */
    private User user;

    /**
     * 单向关联UserModify
     */
    private UserModify userModify;

    /**
     * 单向关联Home
     */
    private Home home;

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserModify(UserModify userModify) {
        this.userModify = userModify;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public void delete(Integer id) {
        log.info("联系班主任，了解相关学生，学生id="+id);
        user.delete(id);
    }

    // 重载能力
    public void delete(List<Integer> list) {
        list.forEach(id -> {
            delete(id);
        });
    }

    // 直接提供能力
    public void deleteList(List<Integer> list) {
        userModify.delete(list);
    }

    public void cook(String name) {
        log.info("打算吃火锅");
        home.cook(name);
        log.info("打电话让朋友来吃饭");
    }
}
