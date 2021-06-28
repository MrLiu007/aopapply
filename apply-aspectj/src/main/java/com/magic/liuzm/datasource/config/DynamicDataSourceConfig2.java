package com.magic.liuzm.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Maps;
import com.magic.liuzm.datasource.enums.DataSourceEnum;
import com.magic.liuzm.datasource.enums.WriteReadEnum;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 数据源信息配置方式2
 *
 * 采用自动装配方式（穷举所有）,若存在多个slave，属性可设为如下例子：
 * spring.datasource.hikaricp.slave1，
 * spring.datasource.hikaricp.slave2
 */
@Configuration
public class DynamicDataSourceConfig2 {

    @Bean
    @ConfigurationProperties("spring.datasource.hikaricp.master")
    @ConditionalOnClass(HikariDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.zaxxer.hikari.HikariDataSource", matchIfMissing = true)
    public DataSource hikaricpMaster() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.hikaricp.slave")
    @ConditionalOnClass(HikariDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.zaxxer.hikari.HikariDataSource", matchIfMissing = true)
    public DataSource hikaricpSlave() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.tomcat.master")
//    @ConditionalOnClass(org.apache.tomcat.jdbc.pool.DataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "org.apache.tomcat.jdbc.pool.DataSource", matchIfMissing = true)
    public DataSource tomcatMaster() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.tomcat.slave")
//    @ConditionalOnClass(org.apache.tomcat.jdbc.pool.DataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "org.apache.tomcat.jdbc.pool.DataSource", matchIfMissing = true)
    public DataSource tomcatSlave() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    @ConfigurationProperties("spring.datasource.c3p0.master")
//    @ConditionalOnClass(org.apache.commons.dbcp2.BasicDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "org.apache.commons.dbcp2.BasicDataSource", matchIfMissing = true)
    public DataSource c3p0Master() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.c3p0.slave")
//    @ConditionalOnClass(org.apache.commons.dbcp2.BasicDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "org.apache.commons.dbcp2.BasicDataSource", matchIfMissing = true)
    public DataSource c3p0Slave() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    @ConditionalOnClass(DruidDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
    public DataSource druidMaster() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnClass(DruidDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
    public DataSource druidSlave() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.bonecp.master")
    // 代码注掉，是因为jar不存在，可pom引入
//    @ConditionalOnClass(BoneCPDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.jolbox.bonecp.BoneCPDataSource", matchIfMissing = true)
    public DataSource boneCPMaster() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.bonecp.slave")
    // 代码注掉，是因为jar不存在，可pom引入
//    @ConditionalOnClass(BoneCPDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.jolbox.bonecp.BoneCPDataSource", matchIfMissing = true)
    public DataSource boneCPSlave() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.dbcp.master")
    // 代码注掉，是因为jar不存在，可pom引入
//    @ConditionalOnClass(BasicDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "org.apache.commons.dbcp.BasicDataSource", matchIfMissing = true)
    public DataSource dbcpMaster() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.dbcp.slave")
    // 代码注掉，是因为jar不存在，可pom引入
//    @ConditionalOnClass(BasicDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "org.apache.commons.dbcp.BasicDataSource", matchIfMissing = true)
    public DataSource dbcpSlave() {
        return DataSourceBuilder.create().build();
    }


    /**
     * DynamicDataSource也为DataSource子类，这里用 @Primary进行标注，采用此实现类。
     */
    @Bean
    @Primary
    public DynamicDataSourceRouter dataSource(DataSource hikaricpMaster, DataSource hikaricpSlave,
                                              DataSource tomcatMaster, DataSource tomcatSlave,
                                              DataSource c3p0Master, DataSource c3p0Slave,
                                              DataSource druidMaster, DataSource druidSlave,
                                              DataSource boneCPMaster, DataSource boneCPSlave,
                                              DataSource dbcpMaster, DataSource dbcpSlave) {
        // 保存各种数据源
        Map<Object, Object> targetDataSources = Maps.newHashMap();
        if(hikaricpMaster != null){
            targetDataSources.put(DataSourceEnum.HIKARICP.name() + WriteReadEnum.MASTER.name(), hikaricpMaster);
        }
        if(hikaricpSlave != null){
            // 默认设置为从1
            targetDataSources.put(DataSourceEnum.HIKARICP.name() + WriteReadEnum.SLAVE1.name(), hikaricpMaster);
        }
        if(tomcatMaster != null){
            targetDataSources.put(DataSourceEnum.TOMCAT_JDBC_POOL.name() + WriteReadEnum.MASTER.name(), hikaricpMaster);
        }
        if(tomcatSlave != null){
            targetDataSources.put(DataSourceEnum.TOMCAT_JDBC_POOL.name() + WriteReadEnum.SLAVE1.name(), hikaricpMaster);
        }
        if(c3p0Master != null){
            targetDataSources.put(DataSourceEnum.C3P0.name() + WriteReadEnum.MASTER.name(), hikaricpMaster);
        }
        if(c3p0Slave != null){
            targetDataSources.put(DataSourceEnum.C3P0.name() + WriteReadEnum.SLAVE1.name(), hikaricpMaster);
        }
        if(druidMaster != null){
            targetDataSources.put(DataSourceEnum.DRUID.name() + WriteReadEnum.MASTER.name(), hikaricpMaster);
        }
        if(druidSlave != null){
            targetDataSources.put(DataSourceEnum.DRUID.name() + WriteReadEnum.SLAVE1.name(), hikaricpMaster);
        }
        if(boneCPMaster != null){
            targetDataSources.put(DataSourceEnum.BONECP.name() + WriteReadEnum.MASTER.name(), hikaricpMaster);
        }
        if(boneCPSlave != null){
            targetDataSources.put(DataSourceEnum.BONECP.name() + WriteReadEnum.SLAVE1.name(), hikaricpMaster);
        }
        if(dbcpMaster != null){
            targetDataSources.put(DataSourceEnum.DBCP.name() + WriteReadEnum.MASTER.name(), hikaricpMaster);
        }
        if(dbcpSlave != null){
            targetDataSources.put(DataSourceEnum.DBCP.name() + WriteReadEnum.SLAVE1.name(), hikaricpMaster);
        }
        // 这里设置druid-master为默认数据源
        DataSource defaultTargetSource = druidMaster;

        return new DynamicDataSourceRouter(defaultTargetSource, targetDataSources);
    }

}
