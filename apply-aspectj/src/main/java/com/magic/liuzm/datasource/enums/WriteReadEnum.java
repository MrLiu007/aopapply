package com.magic.liuzm.datasource.enums;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 读写类型枚举
 */
public enum WriteReadEnum {

    /**
     * master：写+读操作
     */
    MASTER("master","主库"),

    /**
     * slave：读操作
     */
    SLAVE1("slave1","从1库"),

    /**
     * slave：读操作
     */
    SLAVE2("slave2","从2库");

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
