package com.magic.liuzm.cache.aop;

import com.alibaba.fastjson.JSON;
import com.magic.liuzm.cache.annotation.BizCache;
import com.magic.liuzm.cache.apply.BizCacheFactory;
import com.magic.liuzm.cache.apply.BizCacheable;
import com.magic.liuzm.cache.enums.CacheOperateEnum;
import com.magic.liuzm.utils.AopUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 业务缓存切面类
 *
 * 注意：若设置执行顺序可用order(编号)标记
 */
@Aspect
@Component
public class BizCacheAop {

	/**
	 * 缓存前缀
	 */
	private static final String CACHE_PREFIX = "apply:cache:";

	@Pointcut("@annotation(com.magic.liuzm.cache.annotation.BizCache)")
	public void operationCache() {}

	@Around("operationCache()")
	public Object operationCache(ProceedingJoinPoint point) throws Throwable {
		// 获取注解绑定方法
		Method method = AopUtil.getMethod(point);
		// 获得方法上相关注解
		BizCache cache = method.getAnnotation(BizCache.class);
		// 是否开启缓存，默认开启
		if(!cache.available()){
			// 执行原方法
			return point.proceed();
		}
		Object object = null;
		// 方法返回值类型
		Type type = method.getAnnotatedReturnType().getType();
		// 操作cache key
		StringBuilder cacheKey = createCacheKey(method, cache);
		// 具体业务缓存能力接口
		BizCacheable efpBizCacheable = BizCacheFactory.getCacheMap(cache.cacheType(),cache.bizType());
		// 操作类型
		if(CacheOperateEnum.GET == cache.operateType()){
			// 查询缓存-json
			String json = efpBizCacheable.getValue(cacheKey.toString());
			if(StringUtils.isEmpty(json)){
				// 继续执行原方法
				object = point.proceed();
				// 重新保存
				efpBizCacheable.cacheValue(cacheKey.toString(),object,type.getClass());
				return object;
			}
			// 若不设置，则返回String类型
			object = JSON.parseObject(json, cache.cacheMapperClass());
		}else if(CacheOperateEnum.SET == cache.operateType()){
			// 继续执行原方法
			object = point.proceed();
			// 保存缓存-json
			efpBizCacheable.cacheValue(cacheKey.toString(),object,type.getClass());
		}else {
			// 删除缓存
			efpBizCacheable.remove(cacheKey.toString());
		}
		// 继续执行原方法或者直接返回数据
		return object == null ? point.proceed() : object;
	}

	/**
	 * @author zemin.liu
	 * @description 获得cache key
	 * @date 2020/7/5 22:45
	 * @param method
	 * @param cache
	 * @return java.lang.StringBuilder
	 */
	private StringBuilder createCacheKey(Method method, BizCache cache) {
		StringBuilder cacheKey = new StringBuilder(CACHE_PREFIX).append(cache.bizType().getDesc()).append(":");
		if(StringUtils.isEmpty(cache.cacheKey())){
			// 默认 saas:cache:业务:所在类名_所在方法名
			cacheKey.append(method.getDeclaringClass().getName()).append(".").append(method.getName());
		} else {
			// 自定义 saas:cache:业务:自定义key
			cacheKey.append(cache.cacheKey());
		}
		return cacheKey;
	}
}