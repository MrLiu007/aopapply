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
 * @description Druid数据源配置
 */
@Component
@ConditionalOnProperty(prefix = "spring.datasource.druid",name = "type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
public class DruidConfig {

    /**
     * 连接池初始化大小
     */
    @Value("${spring.datasource.druid.initial-size}")
    private String initialSize;

    /**
     * 最大连接数
     */
    @Value("${spring.datasource.druid.max-active}")
    private String maxActive;

    /**
     * 最小空闲连接
     */
    @Value("${spring.datasource.druid.min-idle}")
    private String minIdle;

    /**
     * 配置监控统计拦截的filters
     */
    @Value("${spring.datasource.druid.filters}")
    private String filters;

    /**
     * 是否检测池里连接的可用性
     */
    @Value("${spring.datasource.druid.test-on-borrow}")
    private Boolean testOnBorrow;

    /**
     * 数据源类型
     */
    @Value("${spring.datasource.druid.type}")
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

    public String getInitialSize() {
        return initialSize;
    }

    public String getMaxActive() {
        return maxActive;
    }

    public String getMinIdle() { return minIdle; }

    public String getFilters() {
        return filters;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
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
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public JdbcConfig master() {
        return new JdbcConfig();
    }

    /**
     * 创建数据源-slave
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public JdbcConfig slave() {
        return new JdbcConfig();
    }
}
