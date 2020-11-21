package com.magic.liuzm.check.aop;

import com.magic.liuzm.check.annotation.BizCheckParam;
import com.magic.liuzm.check.apply.BizCheckParamFactory;
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
 * @description 业务参数校验切面类
 */
@Aspect
@Component
public class BizCheckParamAop {

	@Pointcut("@annotation(com.magic.liuzm.check.annotation.BizCheckParam)")
	public void checkParam() {}

	@Before("checkParam()")
	public void checkParamDo(JoinPoint point){
		// 获取注解绑定方法
		Method method = AopUtil.getMethod(point);
		// 获得方法上相关注解
		BizCheckParam check = method.getAnnotation(BizCheckParam.class);
		// 是否开启校验，默认开启
		if(!check.available()){
			// 不增强
			return;
		}
		// 方法的参数value数组
		Object[] obj = point.getArgs();
		// 若方法存在多个参数，循环个参数对象
		for (Object argItem : obj) {
			// 执行biz检查
			BizCheckParamFactory.getCheckParamMap(check.bizType()).checkDo(argItem);
		}
	}
}