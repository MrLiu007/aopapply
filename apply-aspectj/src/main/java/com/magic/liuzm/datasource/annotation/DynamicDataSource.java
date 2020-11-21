package com.magic.liuzm.datasource.annotation;

import com.magic.liuzm.datasource.enums.DataSourceEnum;
import com.magic.liuzm.datasource.enums.WriteReadEnum;
import java.lang.annotation.*;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 动态数据源注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDataSource {

    /**
     * 数据源类型
     */
    DataSourceEnum dataSource() default DataSourceEnum.HIKARICP;

    /**
     * 读写类型
     */
    WriteReadEnum writeRead() default WriteReadEnum.MASTER;

    /**
     * 开启多数据源，默认开启
     */
    boolean available() default true;
}
