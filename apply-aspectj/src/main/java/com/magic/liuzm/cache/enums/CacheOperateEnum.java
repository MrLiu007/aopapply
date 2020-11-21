package com.magic.liuzm.cache.enums;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 缓存操作类型枚举
 */
public enum CacheOperateEnum {

    /**
     * set
     */
    SET("1", "set"),

    /**
     * get
     */
    GET("2", "get"),

    /**
     * remove
     */
    REMOVE("3", "remove");


    private String code;

    private String desc;

    CacheOperateEnum(String code, String desc) {
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
