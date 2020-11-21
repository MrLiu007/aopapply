package com.magic.liuzm.cache.apply;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 具体业务枚举
 */
public enum BizEnum {

    /**
     * 用户
     */
    USER("1", "user");

    private String code;

    private String desc;

    BizEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * @author zemin.liu
     * @description 直接存储枚举字符
     * @date 2020/11/7 19:54
     * @param code
     * @return com.magic.liuzm.cache.apply.BizEnum
     */
    public static BizEnum create(String code) {
        if(code == null || "".equals(code)) {
            return USER;
        }

        for(BizEnum type : values()) {
            if(code.equals(type.name())) {
                return type;
            }
        }
        return USER;
    }
}
