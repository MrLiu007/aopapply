package com.magic.liuzm.check.enums;

import com.magic.liuzm.check.CommonCheck;

import java.util.function.BiFunction;

/**
 * @author zemin.liu
 * @date 2020/6/8 11:31
 * @description 常规参数验证规则枚举
 */
public enum CheckRuleEnum {

	/**
	 * 规则-参数必须不为null
	 */
	NotNull("参数必须不为null", CommonCheck::isNotNull),

	/**
	 * 规则-参数必须非空
	 */
	NotEmpty("参数必须非空", CommonCheck::isNotEmpty),

	/**
	 * 规则-参数必须为 true
	 */
	True("参数必须为 true", CommonCheck::isTrue),

	/**
	 * 规则-参数必须为 false
	 */
	False("参数必须为 false", CommonCheck::isFalse),

	/**
	 * 规则-参数必须是一个日期 yyyy-MM-dd
	 */
	Date("参数必须是一个日期 yyyy-MM-dd", CommonCheck::isDate),

	/**
	 * 规则-参数必须是一个日期时间 yyyy-MM-dd HH:mm:ss
	 */
	DateTime("参数必须是一个日期时间 yyyy-MM-dd HH:mm:ss", CommonCheck::isDateTime),

	/**
	 * 规则-参数必须是一个过去的日期
	 */
	Past("参数必须是一个过去的日期", CommonCheck::isPast),

	/**
	 * 规则-参数必须是一个将来的日期
	 */
	Future("参数必须是一个将来的日期", CommonCheck::isFuture),

	/**
	 * 规则-参数必须今天的日期
	 */
	Today("参数必须今天的日期", CommonCheck::isToday),

	/**
	 * 规则-参数必须在数组中
	 */
	Enum("参数必须在数组中", CommonCheck::inArray),

	/**
	 * 规则-参数必须是Email地址
	 */
	Email("参数必须是Email地址", CommonCheck::isEmail),

	/**
	 * 规则-参数必须在合适的范围内
	 */
	Range("参数必须在合适的范围内", CommonCheck::inRange),

	/**
	 * 规则-参数必须不在指定的范围内
	 */
	NotIn("参数必须不在指定的范围内", CommonCheck::outRange),

	/**
	 * 规则-参数长度必须在指定范围内
	 */
	Length("参数长度必须在指定范围内", CommonCheck::inLength),

	/**
	 * 规则-参数必须大于指定值
	 */
	gt("参数必须大于指定值", CommonCheck::isGreaterThan),

	/**
	 * 规则-参数必须小于指定值
	 */
	lt("参数必须小于指定值", CommonCheck::isLessThan),

	/**
	 * 规则-参数必须大于等于指定值
	 */
	ge("参数必须大于等于指定值", CommonCheck::isGreaterThanEqual),

	/**
	 * 规则-参数必须小于等于指定值
	 */
	le("参数必须小于等于指定值", CommonCheck::isLessThanEqual),

	/**
	 * 规则-参数必须不等于指定值
	 */
	ne("参数必须不等于指定值", CommonCheck::isNotEqual),

	/**
	 * 规则-参数必须不等于指定值
	 */
	Equal("参数必须不等于指定值", CommonCheck::isEqual),

	/**
	 * 规则-参数必须符合指定的正则表达式
	 */
	Pattern("参数必须符合指定的正则表达式", CommonCheck::isPattern);


	public String msg;
	
	/**
	 * 规定出入参数，返回值如下：
	 * 接收字段值(Object)和表达式(String)，返回是否符合规则(Boolean)
	 */
	public BiFunction<Object, String, Boolean> fun;

	CheckRuleEnum(String msg, BiFunction<Object, String, Boolean> fun) {
		this.msg = msg;
		this.fun = fun;
	}
}
