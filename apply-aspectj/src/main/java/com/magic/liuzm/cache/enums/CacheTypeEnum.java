package com.magic.liuzm.cache.enums;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 缓存类型枚举
 */
public enum CacheTypeEnum {

    /**
     * Local cache
     */
    LOCAL("1", "Local cache"),

    /**
     * redis
     */
    REDIS("2", "redis"),

    /**
     * db
     */
    DB("3", "db");


    private String code;

    private String desc;

    CacheTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
