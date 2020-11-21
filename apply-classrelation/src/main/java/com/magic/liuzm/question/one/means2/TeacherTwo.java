package com.magic.liuzm.question.one.means2;

import lombok.extern.log4j.Log4j2;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:47
 * @description 教师角色
 */
@Log4j2
public class TeacherTwo implements UserService,HomeService {

    /**
     * 实现方式：通过实现（Realization）方式对User扩展
     *
     * 缺点：
     * 比如教师变成主任了，权限更多
     * 比如教师的User有新方法，delete参数变化了
     */

    @Override
    public void delete(Integer id) {
        log.info("联系家长，学生id="+id);
        log.info("删除花名册，学生id="+id);
        log.info("注销相关权限，学生id="+id);
    }

    @Override
    public void cook(String name) {
        log.info("在家做饭，食材="+name);
    }
}
