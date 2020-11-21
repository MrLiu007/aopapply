package com.magic.liuzm.cache.apply.example;

import com.magic.liuzm.cache.apply.BizCacheable;
import com.magic.liuzm.cache.apply.BizEnum;
import com.magic.liuzm.cache.enums.CacheTypeEnum;
import org.springframework.stereotype.Service;

/**
 * @author zemin.liu
 * @date 2020/5/12 18:19
 * @description 业务DB缓存具体实现
 */
@Service
public class BizDBCacheService implements BizCacheable {

    @Override
    public Boolean cacheValue(String key, Object value, Class classType) {
        // 转换为json存入
        return true;
    }

    @Override
    public Boolean remove(String key) {
        // 操作db
        return true;
    }

    @Override
    public String getValue(String key) {
        // 操作db
        return null;
    }

    @Override
    public BizEnum getBizCode() {
        return BizEnum.USER;
    }

    @Override
    public CacheTypeEnum getTypeCode() {
        return CacheTypeEnum.DB;
    }
}
