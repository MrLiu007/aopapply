package com.magic.liuzm.question.one.means1;

import com.magic.liuzm.question.one.User;
import lombok.extern.log4j.Log4j2;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:47
 * @description 教师角色
 */
@Log4j2
public class TeacherOne extends User {

    /**
     * 实现方式：通过继承(泛化Generalization)方式对User扩展
     *
     * 缺点：
     * 比如教师变成主任了，权限更多
     * 比如教师不光有简单用户能力，在学校有教学能力，在家有做饭能力
     * 比如教师的User有新方法，delete参数变化了
     */

    @Override
    public void delete(Integer id) {
        log.info("联系家长，学生id="+id);
        log.info("删除花名册，学生id="+id);
        log.info("注销相关权限，学生id="+id);
    }
}
