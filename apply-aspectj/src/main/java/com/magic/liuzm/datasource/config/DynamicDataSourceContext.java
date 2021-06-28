package com.magic.liuzm.datasource.config;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 数据源上下文对象
 */
public class DynamicDataSourceContext {

    /**
     * 记录不同线程数据源标记
     */
    private static final ThreadLocal<String> CONTEXT_DATASOURCE = new ThreadLocal<>();

    /**
     * 设置数据源标记
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType){
        CONTEXT_DATASOURCE.set(dataSourceType);
    }

    /**
     * 获取数据源标记
     * @return
     */
    public static String  getDataSourceType(){
        return CONTEXT_DATASOURCE.get();
    }

    /***
     * 清空数据源标记
     */
    public static void clearDateSourceType(){
        CONTEXT_DATASOURCE.remove();
    }

}
