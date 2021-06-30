package com.magic.liuzm.datasource.enums;

/**
 * @author zemin.liu
 * @date 2021/6/28 11:31
 * @description 常见数据源类型枚举
 */
public enum DataSourceEnum {

    /**
     * HikariCP(SpringBoot2.0 默认)
     */
    HIKARICP("hikari","HikariCP(SpringBoot2.0 默认)数据源"),

    /**
     * tomcat
     */
    TOMCAT_JDBC_POOL("tomcat","tomcat数据源"),

    /**
     * c3p0
     */
    C3P0("c3p0","c3p0数据源"),

    /**
     * Druid
     */
    DRUID("druid","druid数据源"),

    /**
     * BoneCP
     */
    BONECP("bonecp","bonecp数据源"),

    /**
     * Apache Jakarta DBCP
     */
    DBCP("dbcp","Apache Jakarta DBCP数据源");


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
        for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {
            if (code.equals(dataSourceEnum.getCode())) {
                return dataSourceEnum;
            }
        }
        return null;
    }

    public static boolean checkType(String code){
        if(DataSourceEnum.HIKARICP.code.equals(code)
                || DataSourceEnum.TOMCAT_JDBC_POOL.code.equals(code)
                || DataSourceEnum.C3P0.code.equals(code)
                || DataSourceEnum.DRUID.code.equals(code)
                || DataSourceEnum.BONECP.code.equals(code)
                || DataSourceEnum.DBCP.code.equals(code)){
            return true;
        }
        return false;
    }
}
