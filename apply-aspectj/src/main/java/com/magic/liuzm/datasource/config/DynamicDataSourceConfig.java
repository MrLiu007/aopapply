package com.magic.liuzm.datasource.config;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.google.common.collect.Maps;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author zemin.liu
 * @date 2021/6/28 11:31
 * @description 数据源信息配置方式-属性动态加载
 */
@Component
public class DynamicDataSourceConfig{

    @Autowired(required = false)
    private DruidConfig druidConfig;

    @Autowired(required = false)
    private HikariConfig hikariConfig;


    /**
     * 数据源路由器
     */
    @Bean
    @Primary
    @ConditionalOnProperty(prefix = "spring.datasource.druid",name = "type", havingValue = "com.alibaba.druid.pool.DruidDataSource")
    public DynamicDataSourceRouter druidDataSourceRouter() throws Exception{
        // 可选择的数据源列表
        Map<Object, Object> targetDataSources = Maps.newHashMap();

        JdbcConfig master = druidConfig.getMaster();
        DataSource masterDataSource = DataSourceBuilder.create()
                .url(master.getUrl())
                .username(master.getUsername())
                .password(master.getPassword())
                .type((Class<DataSource>) Class.forName(druidConfig.getType())).build();
        // 复制配置信息
        bindDruidDataSourceConfig((DruidAbstractDataSource) masterDataSource,true);

        JdbcConfig slave = druidConfig.getSlave();
        if(slave != null && slave.checking()){
            DataSource slaveDataSource = DataSourceBuilder.create()
                    .url(slave.getUrl())
                    .username(slave.getUsername())
                    .password(slave.getPassword())
                    .type((Class<DataSource>) Class.forName(druidConfig.getType())).build();
            // 复制配置信息
            bindDruidDataSourceConfig((DruidAbstractDataSource) slaveDataSource,false);

            targetDataSources.put(((DruidAbstractDataSource) slaveDataSource).getName(), slaveDataSource);
        }

        return new DynamicDataSourceRouter(masterDataSource, targetDataSources);
    }

    /**
     * 数据源路由器
     */
    @Bean
    @Primary
    @ConditionalOnProperty(prefix = "spring.datasource.hikari",name = "type", havingValue = "com.zaxxer.hikari.HikariDataSource")
    public DynamicDataSourceRouter hikariDataSourceRouter() throws Exception{
        // 可选择的数据源列表
        Map<Object, Object> targetDataSources = Maps.newHashMap();

        JdbcConfig master = hikariConfig.getMaster();
        DataSource masterDataSource = DataSourceBuilder.create()
                .url(master.getUrl())
                .username(master.getUsername())
                .password(master.getPassword())
                .type((Class<DataSource>) Class.forName(hikariConfig.getType())).build();
        // 复制配置信息
        bindHikariDataSourceConfig((HikariDataSource) masterDataSource,true);

        JdbcConfig slave = hikariConfig.getSlave();
        if(slave != null && slave.checking()){
            DataSource slaveDataSource = DataSourceBuilder.create()
                    .url(slave.getUrl())
                    .username(slave.getUsername())
                    .password(slave.getPassword())
                    .type((Class<DataSource>) Class.forName(hikariConfig.getType())).build();
            // 复制配置信息
            bindHikariDataSourceConfig((HikariDataSource) slaveDataSource,false);

            targetDataSources.put(((HikariDataSource) slaveDataSource).getPoolName(), slaveDataSource);
        }

        return new DynamicDataSourceRouter(masterDataSource, targetDataSources);
    }

    /**
     * 绑定druid配置
     */
    private void bindDruidDataSourceConfig(DruidAbstractDataSource dataSource, boolean isDefault) throws Exception{
        // TODO 这种写死方式不利于扩展
        dataSource.setInitialSize(Integer.parseInt(druidConfig.getInitialSize()));
        dataSource.setMaxActive(Integer.parseInt(druidConfig.getMaxActive()));
        dataSource.setMinIdle(Integer.parseInt(druidConfig.getMinIdle()));
        dataSource.setFilters(druidConfig.getFilters());
        dataSource.setTestOnBorrow(druidConfig.getTestOnBorrow());

        // 设置名称
        dataSource.setName(isDefault ? DynamicDataSourceConstant.DEFAULT_DRUID_DATASOURCE
                : DynamicDataSourceConstant.SECOND_DRUID_DATASOURCE);
    }

    /**
     * 绑定hikari配置
     */
    private void bindHikariDataSourceConfig(HikariDataSource dataSource, boolean isDefault) throws Exception{
        // TODO 这种写死方式不利于扩展
        dataSource.setMaximumPoolSize(Integer.parseInt(hikariConfig.getMaximumPoolSize()));
        dataSource.setMinimumIdle(Integer.parseInt(hikariConfig.getMinimumIdle()));
        dataSource.setIdleTimeout(Integer.parseInt(hikariConfig.getIdleTimeout()));
        dataSource.setMaxLifetime(Long.parseLong(hikariConfig.getMaxLifetime()));
        dataSource.setConnectionTimeout(Long.parseLong(hikariConfig.getConnectionTimeout()));
        dataSource.setConnectionTestQuery(hikariConfig.getConnectionTestQuery());

        // 设置名称
        dataSource.setPoolName(isDefault ? DynamicDataSourceConstant.DEFAULT_HIKARI_DATASOURCE
                : DynamicDataSourceConstant.SECOND_HIKARI_DATASOURCE);
    }
}
