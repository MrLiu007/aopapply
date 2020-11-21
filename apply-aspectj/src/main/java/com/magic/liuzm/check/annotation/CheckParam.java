package com.magic.liuzm.check.annotation;

import com.magic.liuzm.check.enums.CheckRuleEnum;
import java.lang.annotation.*;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 通用参数验证注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckParam {

    /**
     * 验证规则
     */
    CheckRuleEnum value() default CheckRuleEnum.NotNull;

    /**
     * 表达式:多个值逗号隔开
     */
    String express() default "";

    /**
     * 参数名称用.表示层级，最多支持2级
     * 如： user.userId
     */
    String argName();

    /**
     * 参数类型
     */
    String argType() default "";

    /**
     * 错误提示信息
     */
    String msg() default "";

}