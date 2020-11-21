package com.magic.liuzm.cache.apply;

import com.magic.liuzm.cache.Cacheable;
import com.magic.liuzm.cache.enums.CacheTypeEnum;

/**
 * @author zemin.liu
 * @date 2020/6/10 23:59
 * @description 具体业务缓存能力接口
 */
public interface BizCacheable extends Cacheable {

    /**
     * @author zemin.liu
     * @description 标记业务类型
     * @date 2020/6/11 10:36
     * @param
     * @return com.magic.liuzm.cache.apply.BizEnum
     */
    BizEnum getBizCode();

    /**
     * @author zemin.liu
     * @description 标记缓存方式
     * @date 2020/6/11 10:36
     * @param
     * @return com.magic.liuzm.cache.apply.BizEnum
     */
    CacheTypeEnum getTypeCode();
}
