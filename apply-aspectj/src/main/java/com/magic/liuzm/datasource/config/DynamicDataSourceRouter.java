package com.magic.liuzm.datasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 自定义数据源路由器
 */
public class DynamicDataSourceRouter extends AbstractRoutingDataSource {

    public DynamicDataSourceRouter(DataSource defaultTargetSource, Map<Object,Object> targetDataSources){
        // 默认数据源
        super.setDefaultTargetDataSource(defaultTargetSource);
        // 可选择的数据源列表
        super.setTargetDataSources(targetDataSources);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        // 钩子函数具体实现：设置当前线程对应的数据源，此方法调用时机在DataSource.getConnection()执行后
        return DynamicDataSourceContext.getDataSourceType();
    }
}
