package com.magic.liuzm.datasource.enums;

import com.magic.liuzm.datasource.config.JdbcPropertiesDO;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 数据源配置参数枚举
 */
public enum JdbcConfigEnum {

    /**
     * 数据库地址
     */
    JDBC_URL("jdbc-url","数据库地址"),

    /**
     * 操作用户名
     */
    USERNAME("username","操作用户名"),

    /**
     * 操作密码
     */
    PASSWORD("password","操作密码"),

    /**
     * 驱动类名称
     */
    DRIVER_CLASS_NAME("driver-class-name","驱动类名称"),

    /**
     * 数据源类型
     */
    TYPE("type","数据源类型");


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

    JdbcConfigEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static JdbcConfigEnum getJdbcConfigEnum(String code) {
        for (JdbcConfigEnum jdbcConfigEnum : JdbcConfigEnum.values()) {
            if (code.equals(jdbcConfigEnum.getCode())) {
                return jdbcConfigEnum;
            }
        }
        return null;
    }

    /**
     * 绑定参数
     */
    public static JdbcPropertiesDO bindTypeData(String key,String vlue, JdbcPropertiesDO jdbcDO){
        if(key.contains(JdbcConfigEnum.JDBC_URL.getCode())){
            jdbcDO.setJdbcUrl(vlue);
        }else if(key.contains(JdbcConfigEnum.USERNAME.getCode())){
            jdbcDO.setUserName(vlue);
        }else if(key.contains(JdbcConfigEnum.PASSWORD.getCode())){
            jdbcDO.setPassWord(vlue);
        }else if(key.contains(JdbcConfigEnum.DRIVER_CLASS_NAME.getCode())){
            jdbcDO.setDriverClassName(vlue);
        }else if(key.contains(JdbcConfigEnum.TYPE.getCode())){
            jdbcDO.setType(vlue);
        }
        return jdbcDO;
    }
}
