package com.magic.liuzm.datasource.enums;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 数据源类型枚举
 *
 * 注意：这里再按mysql，oracle拆分。
 */
public enum DataSourceEnum {

    /**
     * HikariCP(SpringBoot2.0 默认)
     */
    HIKARICP("hikari","HikariCP(SpringBoot2.0 默认)"),

    /**
     * tomcat
     */
    TOMCAT_JDBC_POOL("hikari","tomcat"),

    /**
     * c3p0
     */
    C3P0("hikari","c3p0"),

    /**
     * Druid
     */
    DRUID("hikari","Druid"),

    /**
     * BoneCP
     */
    BONECP("hikari","BoneCP"),

    /**
     * Apache Jakarta DBCP
     */
    DBCP("hikari","Apache Jakarta DBCP");


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

    DataSourceEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static DataSourceEnum getCacheOperateEnum(String code) {
        for (DataSourceEnum dataSourceEnum: DataSourceEnum.values()) {
            if (code.equals(dataSourceEnum.getCode())) {
                return dataSourceEnum;
            }
        }
        return null;
    }

    public static boolean checkType(String code){
        if(DataSourceEnum.HIKARICP.code == code
                ||DataSourceEnum.TOMCAT_JDBC_POOL.code == code
                ||DataSourceEnum.C3P0.code == code
                ||DataSourceEnum.DRUID.code == code
                ||DataSourceEnum.BONECP.code == code
                ||DataSourceEnum.DBCP.code == code){
            return true;
        }
        return false;
    }
}
