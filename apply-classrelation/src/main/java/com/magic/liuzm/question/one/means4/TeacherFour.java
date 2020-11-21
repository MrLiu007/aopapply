package com.magic.liuzm.question.one.means4;

import com.magic.liuzm.question.one.User;
import com.magic.liuzm.question.one.means3.Home;
import com.magic.liuzm.question.one.means3.UserModify;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:47
 * @description 教师角色
 */
@Log4j2
public class TeacherFour {
    /**
     * 实现方式：通过依赖（Dependency）方式对象User扩展
     *
     * 缺点：还是要改当前类
     */

    public void delete(User user,Integer id) {
        log.info("联系班主任，了解相关学生，学生id="+id);
        user.delete(id);
    }

    // 重载能力
    public void delete(User user,List<Integer> list) {
        list.forEach(id -> {
            delete(user,id);
        });
    }

    // 直接提供能力
    public void deleteList(UserModify userModify,List<Integer> list) {
        userModify.delete(list);
    }

    public void cook(Home home,String name) {
        log.info("打算吃火锅");
        home.cook(name);
        log.info("打电话让朋友来吃饭");
    }
}
