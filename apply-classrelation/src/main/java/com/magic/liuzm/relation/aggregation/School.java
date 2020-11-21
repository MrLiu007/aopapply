package com.magic.liuzm.relation.aggregation;

import lombok.extern.log4j.Log4j2;

/**
 * @author zemin.liu
 * @date 2020/11/18 23:27
 * @description 学校对象
 */
@Log4j2
public class School {

    /**
     * 聚合方式
     */

    private Manger manger;

    private Teacher teacher;

    private Student student;


    public void doMeeting(){
        log.info("开始晨会");
        manger.doThing();
        teacher.doThing();
        student.doThing();
    }
}
