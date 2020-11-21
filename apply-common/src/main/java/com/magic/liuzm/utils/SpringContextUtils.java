package com.magic.liuzm.utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * 实现描述：获取spring容器，以访问容器中定义的其他bean
 * 
 * @author zc
 * @version v1.0.0
 * @see
 * @since 2016-11-18
 */
@Component
@Lazy(false)
public class SpringContextUtils implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    /**
     * 管理当spring容器还没有启动的时候被调用的问题
     */
    public static CountDownLatch latch = new CountDownLatch(1);

    /**
     * 通过Class类型拿到实例对象，前提实例名字和类名一致且第一个字符为小写
     * 
     * @param targetClass
     * @return
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> targetClass) throws BeansException {
        String clazzName = StringUtils.substringAfterLast(targetClass.getName(), ".");
        String instanceName = clazzName.substring(0, 1).toLowerCase() + clazzName.substring(1);
        return (T) getBean(instanceName);
    }

    /**
     * 根据类名返回其bean对象
     *
     * @param name
     * @return bean的实例
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        if (applicationContext == null) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                return null;
            }
        }
        return SpringContextUtils.applicationContext.getBean(name);
    }

    /**
     * 设置上下文
     */
    private static void initialize(ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return SpringContextUtils.applicationContext;
    }

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     * 
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtils.initialize(applicationContext);
        latch.countDown();
    }

}