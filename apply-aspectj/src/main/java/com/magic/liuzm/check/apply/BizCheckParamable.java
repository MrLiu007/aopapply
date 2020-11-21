package com.magic.liuzm.check.apply;

import com.magic.liuzm.cache.apply.BizEnum;

/**
 * @author zemin.liu
 * @date 2020/6/10 23:59
 * @description 业务check检查接口
 */
public interface BizCheckParamable {

    /**
     * @author zemin.liu
     * @description check 入口
     * @date 2020/6/11 00:00
     * @param argItem 参数对象
     * @return java.lang.Boolean
     */
    Boolean checkDo(Object argItem);

   /**
    * @author zemin.liu
    * @description 标记实现类类型
    * @date 2020/11/7 21:40
    * @param
    * @return com.magic.liuzm.cache.apply.BizEnum
    */
    BizEnum getCode();
}
