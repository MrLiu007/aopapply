package com.magic.liuzm.datasource.enums;

/**
 * @author zemin.liu
 * @date 2021/6/28 11:31
 * @description 读写库枚举
 */
public enum WriteReadEnum {

    /**
     * master：写+读操作
     */
    MASTER("master","主库"),

    /**
     * master：写+读操作
     */
    SECOND_MASTER("master2","主2库"),

    /**
     * slave：读操作
     */
    SLAVE("slave","从库"),

    /**
     * slave：读操作
     */
    SECOND_SLAVE("slave2","从2库");

    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    WriteReadEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

}
