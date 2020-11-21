package com.magic.liuzm.check.apply.example.param;

import com.magic.liuzm.cache.apply.BizEnum;
import com.magic.liuzm.check.apply.BizCheckParamable;
import com.magic.liuzm.exception.BizException;
import org.springframework.stereotype.Service;

/**
 * @author zemin.liu
 * @date 2020/5/12 18:19
 * @description 对业务接口参数进行（业务逻辑）判断，为@CheckRuleEnum注解（常用判断）的补充
 */
@Service
public class ParamCheckService implements BizCheckParamable {
    
    @Override
    public BizEnum getCode() {
        // 标记为相关实现
        return BizEnum.USER;
    }

    /**
     * @author zemin.liu
     * @description 组合统一检查入参
     *
     * @date 2020/6/10 19:12
     * @param argItem 参数对象
     * @return boolean
     */
    @Override
    public Boolean checkDo(Object argItem) throws BizException {
        // 按面向接口或者抽象类思想，其中参数argItem为实现或子类，这样能做成统一处理。
        // 比如这里判断学生学号类型是否符合当前学校规则，不满足要求就抛出BizException
        return true;
    }
}