package com.magic.liuzm.datasource.aop;

import com.magic.liuzm.datasource.config.DynamicDataSourceContext;
import com.magic.liuzm.datasource.annotation.DynamicDataSource;
import com.magic.liuzm.utils.AopUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 业务动态数据源切面类
 */
@Aspect
@Component
public class DynamicDataSourceAop {

    @Pointcut("@annotation(com.magic.liuzm.datasource.annotation.DynamicDataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 获取注解绑定方法
        Method method = AopUtil.getMethod(point);
        // 获得方法上相关注解
        DynamicDataSource dataSource = method.getAnnotation(DynamicDataSource.class);
        // 是否开启数据源切换，默认开启
        if(!dataSource.available()){
            // 执行原方法
            return point.proceed();
        }
        // 业务执行前设置数据源
        StringBuffer stringBuffer = new StringBuffer(dataSource.dataSource().name()).append("_").append(dataSource.writeRead().name());
        DynamicDataSourceContext.setDataSourceType(stringBuffer.toString());
        try {
            // 执行原方法
            return point.proceed();
        } finally {
            // 业务执行后清空设置
            DynamicDataSourceContext.clearDateSourceType();
        }
    }

}
