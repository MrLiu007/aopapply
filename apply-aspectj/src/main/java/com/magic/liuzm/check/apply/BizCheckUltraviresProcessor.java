package com.magic.liuzm.check.apply;

import com.magic.liuzm.exception.BizException;

/**
 * @author zemin.liu
 * @date 2020/6/10 23:59
 * @description 业务check权限接口
 */
public interface BizCheckUltraviresProcessor {

    /**
     * @author zemin.liu
     * @description 具体check入口
     * @date 2020/11/14 22:59
     * @param args 请求方法的参数
     * @return java.lang.Boolean
     */
    Boolean checkDo(Object[] args) throws BizException;
}
