package com.magic.liuzm.question.one.means3;

import lombok.extern.log4j.Log4j2;

/**
 * @author zemin.liu
 * @date 2020/11/18 23:00
 * @description 家务角色
 */
@Log4j2
public class Home {

    /**
     * 做饭
     */
    public void cook(String name){
        log.info("在家做饭，食材="+name);
    }
}
