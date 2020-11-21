package com.magic.liuzm.cache;

/**
 * @author zemin.liu
 * @date 2020/6/10 23:59
 * @description 缓存能力接口
 */
public interface Cacheable {

    /**
     * @author zemin.liu
     * @description 设置缓存
     * @date 2020/6/11 00:00
     * @param key 缓存key
     * @param value 缓存
     * @param classType 缓存类型
     * @return java.lang.Boolean
     */
    Boolean cacheValue(String key, Object value, Class classType);

    /**
     * @author zemin.liu
     * @description 设置缓存
     * @date 2020/6/11 00:00
     * @param key 缓存key
     * @return java.lang.Boolean
     */
    Boolean remove(String key);

    /**
     * @author zemin.liu
     * @description 获得缓存
     * @date 2020/6/10 16:01
     * @param key 缓存key
     * @return java.lang.String
     */
    String getValue(String key);
}
