package com.magic.liuzm.datasource.config;

import lombok.Data;

/**
 * @author zemin.liu
 * @date 2020/11/13 14:53
 * @description jdbc配置对象
 */
@Data
public class JdbcPropertiesDO {

    /**
     * 操作用户名
     */
    private String userName;

    /**
     * 操作密码
     */
    private String passWord;

    /**
     * 数据源类型
     */
    private String type;

    /**
     * 数据库地址
     */
    private String jdbcUrl;

    /**
     * 驱动类名称
     */
    private String driverClassName;
}
