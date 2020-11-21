package com.magic.liuzm.check.apply.example.ultravires;

import com.magic.liuzm.check.apply.BizCheckUltraviresProcessor;
import com.magic.liuzm.exception.BizException;
import org.springframework.stereotype.Component;

/**
 * @author zemin.liu
 * @date 2020/6/10 23:59
 * @description 用户check权限接口
 */
@Component
public class UserUltraviresProcessor implements BizCheckUltraviresProcessor {

    @Override
    public Boolean checkDo(Object[] args) throws BizException {
        // 这里需要不同接口，入参数不同，可传入抽象类与子类，方便统一处理；还有就是统一检验token，接口权限可放到拦截器中，通过注解进行标记哪些接口需要处理。
        // 比如token是否存在
        // 比如检查用户是否存在
        // 比如用户是否有权限此接口能力
        return true;
    }
}
