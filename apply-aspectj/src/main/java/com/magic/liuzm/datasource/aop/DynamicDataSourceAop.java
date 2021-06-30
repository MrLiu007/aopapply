package com.magic.liuzm.datasource.aop;

import com.magic.liuzm.datasource.annotation.DynamicDataSource;
import com.magic.liuzm.datasource.config.DynamicDataSourceConstant;
import com.magic.liuzm.datasource.config.DynamicDataSourceContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @author zemin.liu
 * @date 2021/6/28 11:31
 * @description 业务动态数据源切面类
 */
@Aspect
@Component
public class DynamicDataSourceAop {

    @Before("@annotation(cn.soeasypay.efp.bill.repository.datasource.annotation.DynamicDataSource)")
    public void beforeRouter(JoinPoint point) throws Throwable{
        // 当前访问class
        Class<?> className = point.getTarget().getClass();

        // 当前访问方法名
        String methodName = point.getSignature().getName();

        // 当前访问方法参数
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();

        // 当前访问方法对象
        Method method = className.getMethod(methodName, argClass);

        // 默认数据源
        String dataSourceType = DynamicDataSourceConstant.DEFAULT_DRUID_DATASOURCE;
        // 判断是否存在相关注解
        if (method.isAnnotationPresent(DynamicDataSource.class)) {
            // 获得方法上相关注解
            DynamicDataSource dataSource = method.getAnnotation(DynamicDataSource.class);
            // 是否开启数据源切换，默认开启
            if(dataSource.available()){
                // 业务执行前动态设置数据源
                StringBuffer stringBuffer = new StringBuffer(dataSource.rdbmsType().name())
                        .append("_").append(dataSource.dataSource().name())
                        .append("_").append(dataSource.writeRead().name());
                dataSourceType = stringBuffer.toString();
            }
        }
        // 切换数据源
        DynamicDataSourceContext.setDataSourceType(dataSourceType);
    }


    @After("@annotation(cn.soeasypay.efp.bill.repository.datasource.annotation.DynamicDataSource)")
    public void afterRouter(JoinPoint point){
        DynamicDataSourceContext.clearDateSourceType();
    }
}
