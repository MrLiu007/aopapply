package com.magic.liuzm.datasource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zemin.liu
 * @date 2021/6/29 16:22
 * @description hikari数据源配置
 */
@Component
@ConditionalOnProperty(prefix = "spring.datasource.hikari",name = "type", havingValue = "com.zaxxer.hikari.HikariDataSource")
public class HikariConfig {

    /**
     * 最大连接数,默认值10
     */
    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private String maximumPoolSize;

    /**
     * 最小空闲连接,默认值10
     */
    @Value("${spring.datasource.hikari.minimum-idle}")
    private String minimumIdle;

    /**
     * 空闲连接超时时间，默认值600000（10分钟）
     */
    @Value("${spring.datasource.hikari.idle-timeout}")
    private String idleTimeout;

    /**
     * 连接最大存活时间,默认值30分钟.设置应该比mysql设置的超时时间短
     */
    @Value("${spring.datasource.hikari.max-lifetime}")
    private String maxLifetime;

    /**
     * 连接超时时间,默认值30秒
     */
    @Value("${spring.datasource.hikari.connection-timeout}")
    private String connectionTimeout;

    /**
     * 用于测试连接是否可用的查询语句
     */
    @Value("${spring.datasource.hikari.connection-test-query}")
    private String connectionTestQuery;

    /**
     * 数据源类型
     */
    @Value("${spring.datasource.hikari.type}")
    private String type;

    /**
     * 引入数据源-master
     */
    @Autowired
    private JdbcConfig master;

    /**
     * 引入数据源-slave
     */
    @Autowired
    private JdbcConfig slave;

    public String getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public String getMinimumIdle() {
        return minimumIdle;
    }

    public String getIdleTimeout() {
        return idleTimeout;
    }

    public String getMaxLifetime() {
        return maxLifetime;
    }

    public String getConnectionTimeout() {
        return connectionTimeout;
    }

    public String getConnectionTestQuery() {
        return connectionTestQuery;
    }

    public String getType() {
        return type;
    }

    public JdbcConfig getMaster() {
        return master;
    }

    public JdbcConfig getSlave() {
        return slave;
    }

    /**
     * 创建数据源-master
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.master")
    public JdbcConfig master() {
        return new JdbcConfig();
    }

    /**
     * 创建数据源-slave
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari.slave")
    public JdbcConfig slave() {
        return new JdbcConfig();
    }
}
