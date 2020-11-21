package com.magic.liuzm.datasource.config;

import com.google.common.collect.Maps;
import com.magic.liuzm.datasource.DynamicDataSourceRouter;
import com.magic.liuzm.datasource.annotation.DynamicDataSource;
import com.magic.liuzm.datasource.enums.DataSourceEnum;
import com.magic.liuzm.datasource.enums.JdbcConfigEnum;
import com.magic.liuzm.datasource.enums.WriteReadEnum;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 数据源信息配置方式1
 *
 * 属性动态加载
 */
@Configuration
public class DynamicDataSourceConfig{

    @Autowired
    private Environment environment;

    /**
     * DynamicDataSource也为DataSource子类，这里用 @Primary进行标注，采用此实现类。
     */
    @Bean
    @Primary
    public DynamicDataSourceRouter dataSource() {
        // springboot 2.0替换
        Iterable<ConfigurationPropertySource> sources = ConfigurationPropertySources.get(environment);
        BindResult<Properties> bindResult = new Binder(sources).bind("spring.datasource", Properties.class);

        // 可选择的数据源列表
        Map<Object, Object> targetDataSources = targetDataSources(bindResult.get());
        // 这里设置druid-master为默认数据源
        DataSource defaultTargetSource = (DataSource) targetDataSources.get(DataSourceEnum.DRUID.name() + WriteReadEnum.MASTER.name());

        return new DynamicDataSourceRouter(defaultTargetSource, targetDataSources);
    }

    /**
     * 获得可选择的数据源列表
     */
    public Map<Object, Object> targetDataSources(Properties resolver) {
        Map<String, Map<String, JdbcPropertiesDO>> datasourceConfig = Maps.newHashMap();
        // 进行分类
        Set<Map.Entry<Object,Object>> set = resolver.entrySet();
        set.forEach(item ->{
            String key = (String) item.getKey();
            String value = (String) item.getValue();
            // 区分数据源
            String sourceType = key.substring(0,key.indexOf(".") - 1);
            if(!DataSourceEnum.checkType(sourceType)){
                return;
            }
            // 主从数据
            Map<String,JdbcPropertiesDO> valueMap = datasourceConfig.get(sourceType);
            if(CollectionUtils.isEmpty(valueMap)){
                valueMap = Maps.newHashMap();
            }
            // 区分主从数据
            String writeReadType = key.substring(0, key.indexOf(".",key.indexOf(".") + 1 ));
            JdbcPropertiesDO jdbcDO = valueMap.get(writeReadType);
            if(jdbcDO == null){
                jdbcDO = new JdbcPropertiesDO();
            }
            // 匹配不同配置参数
            valueMap.put(writeReadType, JdbcConfigEnum.bindTypeData(key,value,jdbcDO));

            datasourceConfig.put(sourceType,valueMap);
        });

        // 保存各种数据源
        Map<Object, Object> targetDataSources = Maps.newHashMap();
        // 将每类数据源创建为datasource对象
        Set<Map.Entry<String,Map<String, JdbcPropertiesDO>>> dataSourceSet = datasourceConfig.entrySet();
        dataSourceSet.forEach(item ->{
            String sourceType = item.getKey();
            Map<String, JdbcPropertiesDO> value = item.getValue();
            Set<Map.Entry<String,JdbcPropertiesDO>> jdbcSet = value.entrySet();
            jdbcSet.forEach(entry ->{
                try {
                    String writeReadType = entry.getKey();
                    JdbcPropertiesDO jdbcDO = entry.getValue();
                    DataSource dataSource = DataSourceBuilder.create()
                            .driverClassName(jdbcDO.getDriverClassName())
                            .url(jdbcDO.getJdbcUrl())
                            .username(jdbcDO.getUserName())
                            .password(jdbcDO.getPassWord())
                            .type((Class<DataSource>) Class.forName(jdbcDO.getType())).build();

                    targetDataSources.put(sourceType + writeReadType,dataSource);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e.getLocalizedMessage());
                }
            });
        });
        return targetDataSources;
    }
}
