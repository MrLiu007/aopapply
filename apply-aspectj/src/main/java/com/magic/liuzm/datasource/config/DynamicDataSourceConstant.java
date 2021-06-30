package com.magic.liuzm.datasource.config;

import com.magic.liuzm.datasource.enums.DataSourceEnum;
import com.magic.liuzm.datasource.enums.RdbmsTypeEnum;
import com.magic.liuzm.datasource.enums.WriteReadEnum;

/**
 * @author zemin.liu
 * @date 2021/6/29 11:16
 * @description 常量
 */
public class DynamicDataSourceConstant {

    /**
     * druid默认数据源
     */
    public static final String DEFAULT_DRUID_DATASOURCE = RdbmsTypeEnum.MYSQL.name() + "_"
            + DataSourceEnum.DRUID.name() + "_"  + WriteReadEnum.MASTER.name();


    /**
     * druid其他数据源
     */
    public static final String SECOND_DRUID_DATASOURCE = RdbmsTypeEnum.MYSQL.name() + "_"
            + DataSourceEnum.DRUID.name() + "_"  + WriteReadEnum.SLAVE.name();

    /**
     * hikari默认数据源
     */
    public static final String DEFAULT_HIKARI_DATASOURCE = RdbmsTypeEnum.MYSQL.name() + "_"
            + DataSourceEnum.HIKARICP.name() + "_"  + WriteReadEnum.MASTER.name();


    /**
     * hikari其他数据源
     */
    public static final String SECOND_HIKARI_DATASOURCE = RdbmsTypeEnum.MYSQL.name() + "_"
            + DataSourceEnum.HIKARICP.name() + "_"  + WriteReadEnum.SLAVE.name();
}