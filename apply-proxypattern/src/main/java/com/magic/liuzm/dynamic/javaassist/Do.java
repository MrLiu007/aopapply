package com.magic.liuzm.dynamic.javaassist;

import com.magic.liuzm.dynamic.base.AspectTeacher;
import com.magic.liuzm.dynamic.base.Teacher;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:10
 * @description 操作例子
 */
public class Do {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        // 1.真实角色对象
        Teacher teacher = new Teacher();
        // 抽象增强角色对象（这里可不用给，看业务情况，可在执行器中打印一行日志也行）
        AspectTeacher aspectTeacher = new AspectTeacher();
        // 2.方法拦截器
        DynamicMethodHandler handler = new DynamicMethodHandler();
        // 绑定真实角色对象
        handler.setTarget(teacher);
        handler.setAspect(aspectTeacher);

        // 3.代理工厂对象
        ProxyFactory proxyFactory = new ProxyFactory();
        // 绑定真实角色对象（仅支持类）
        proxyFactory.setSuperclass(teacher.getClass());
        // 创建代理对象
        Teacher  proxy = (Teacher) proxyFactory.createClass().newInstance();
        // 绑定方法拦截器
        ((Proxy)proxy).setHandler(handler);

        // 调用业务方法
        proxy.delete(001);
    }

    /**
     * 1.Javassist是在Java中操作字节码的类库;
     * 它让程序能够在运行时定义一个新类, 并在JVM加载时修改类文件；
     * 2.Javassist为类似asm的工具类。
     *
     * 官网：http://www.javassist.org/
     *
     *
     * 从proxyFactory.createClass().newInstance()开始看createClass3（）方法；
     *
     */
}
