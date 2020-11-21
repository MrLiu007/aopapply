package com.magic.liuzm.relation.compostion;

import lombok.extern.log4j.Log4j2;

/**
 * @author zemin.liu
 * @date 2020/11/18 23:34
 * @description 身体对象
 */
@Log4j2
public class Body {

    /**
     * 组合方式
     */

    private Hand hand;

    private Mouth mouth;

    public void eat() {
        log.info("开始吃饭");
        hand.doThing();
        mouth.doThing();
    }
}
