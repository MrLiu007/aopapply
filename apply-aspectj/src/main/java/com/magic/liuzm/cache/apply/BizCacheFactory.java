package com.magic.liuzm.cache.apply;

import com.google.common.collect.Maps;
import com.magic.liuzm.cache.enums.CacheTypeEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @author zemin.liu
 * @date 2020/6/11 00:04
 * @description 缓存工厂类
 */
@Component
public class BizCacheFactory implements ApplicationContextAware {

    private static Map<CacheTypeEnum,Map<BizEnum,BizCacheable>> cacheMap = Maps.newHashMap();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 找到所有BizCacheable的实现类
        Map<String, BizCacheable> map = applicationContext.getBeansOfType(BizCacheable.class);
        map.forEach((key, value) -> {
            Map<BizEnum,BizCacheable> bizMap = Maps.newHashMap();
            bizMap.put(value.getBizCode(),value);

            cacheMap.put(value.getTypeCode(),bizMap);
        });
    }

    /**
     * @author zemin.liu
     * @description 返回相关所有子类
     * @date 2020/6/11 00:15
     * @param cacheType
     * @param bizCode
     * @return T
     */
    public static <T extends BizCacheable> T getCacheMap(CacheTypeEnum cacheType,BizEnum bizCode) {
        return (T)cacheMap.get(cacheType).get(bizCode);
    }
}
