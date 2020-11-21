package com.magic.liuzm.check.aop;

import com.magic.liuzm.check.annotation.CheckParam;
import com.magic.liuzm.check.annotation.CheckParams;
import com.magic.liuzm.exception.BizException;
import com.magic.liuzm.exception.ErrCode;
import com.magic.liuzm.utils.AopUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 通用参数校验切面类
 *
 */
@Aspect
@Component
public class CheckParamAop {

	@Pointcut("@annotation(com.magic.liuzm.check.annotation.CheckParam)")
	public void checkParam() {
	}

	@Pointcut("@annotation(com.magic.liuzm.check.annotation.CheckParams)")
	public void checkParams() {
	}

	@Before("checkParam()")
	public void checkParamDo(JoinPoint point){
		// 获取注解绑定方法
		Method method = AopUtil.getMethod(point);
		// 参数key数组
		String[] paramName = AopUtil.getParamName(point);
		// 参数value数组
		Object[] arguments = point.getArgs();
		// 获得方法上相关注解
		CheckParam check = method.getAnnotation(CheckParam.class);
		// 需要验证对象属性（或属性）
		String argName = check.argName();
		// 需要验证对象属性（或属性）值
		Object value = AopUtil.getParamValue(paramName, arguments, argName);
		// 根据验证规则（对应枚举类），然后调用checkUtil类方法
		boolean isValid = check.value().fun.apply(value, check.express());
		if (!isValid) {
			// 这里是直接抛异常，可用全局异常捕获，返回对应error code/msg
			throw new BizException(ErrCode.BIZ_PARAM_INVALID.getCode(),check.msg());
		}
	}

	@Before("checkParams()")
	public void checkParamsDo(JoinPoint point){
		// 获取注解绑定方法
		Method method = AopUtil.getMethod(point);
		// 参数key数组
		String[] paramName = AopUtil.getParamName(point);
		// 参数value数组
		Object[] arguments = point.getArgs();
		// 获得方法上相关注解
		CheckParams annotation = method.getAnnotation(CheckParams.class);
		CheckParam[] checks = annotation.value();
		// 循环组合注解
		for (CheckParam check : checks) {
			// 需要验证对象属性（或属性）
			String argName = check.argName();
			// 需要验证对象属性（或属性）值
			Object value = AopUtil.getParamValue(paramName, arguments, argName);
			// 根据验证规则（对应枚举类），然后调用checkUtil类方法
			boolean isValid = check.value().fun.apply(value, check.express());
			if(!isValid){
				// 这里是直接抛异常，可用全局异常捕获，返回对应error code/msg
				throw new BizException(ErrCode.BIZ_PARAM_INVALID.getCode(),check.msg());
			}
		}
	}
}