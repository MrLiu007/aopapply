package com.magic.liuzm.check.apply;

import com.google.common.collect.Maps;
import com.magic.liuzm.cache.apply.BizEnum;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zemin.liu
 * @date 2020/6/11 00:04
 * @description 参数验证工厂类
 */
@Component
public class BizCheckParamFactory implements ApplicationContextAware {

    private static Map<BizEnum, BizCheckParamable> checkParamMap = Maps.newHashMap();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 找到所有BizCheckParamable的实现类
        Map<String, BizCheckParamable> map = applicationContext.getBeansOfType(BizCheckParamable.class);
        map.forEach((key, value) -> checkParamMap.put(value.getCode(), value));
    }

    /**
     * @author zemin.liu
     * @description 返回相关所有子类
     * @date 2020/6/11 00:15
     * @param code
     * @return T
     */
    public static <T extends BizCheckParamable> T getCheckParamMap(BizEnum code) {
        return (T)checkParamMap.get(code);
    }
}
