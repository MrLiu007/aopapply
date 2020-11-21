package com.magic.liuzm.dynamic.jdk;

import com.magic.liuzm.dynamic.base.AspectTeacher;
import com.magic.liuzm.dynamic.base.Instructor;
import com.magic.liuzm.dynamic.base.UserService;

import java.lang.reflect.Proxy;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:10
 * @description 操作例子
 */
public class Do {

    public static void main(String[] args) {
        // 1.真实角色对象-辅导员
        Instructor teacher = new Instructor();
        // 抽象增强角色对象（这里可不用给，看业务情况，可在执行器中打印一行日志也行）
        AspectTeacher aspectTeacher = new AspectTeacher();
        // 2.执行器对象
        DynamicInvocationHandler handler = new DynamicInvocationHandler();
        // 绑定真实角色对象
        handler.setTarget(teacher);
        handler.setAspect(aspectTeacher);

        /**
             参数1：真实角色对象的类加载器（因为需要创建文件，就存在2进制操作，也就需要类加载器）
             参数2. 抽象角色对象的接口列表
             参数3. InvocationHandler实现类
         */
        // 3.生成代理对象
        UserService proxy = (UserService) Proxy.newProxyInstance(
                teacher.getClass().getClassLoader(),
                teacher.getClass().getInterfaces(),
                handler);

        // 调用业务方法
        proxy.delete(001);


        /**
         * 特殊说明：
         *
         * 1、属于JDK包，且必须有接口，且接口数不能超过65535
         * 2、Proxy私有无参数构造，protected有参数构造，这样只能被子类创建或者通过Proxy.newProxyInstance创建
         *
         * 3、Proxy.newProxyInstance内部步骤大致如下：
         *    （1.Class<?> cl = getProxyClass0(loader, intfs)；//查找或生成指定的代理类，内部其实从弱引用map中获得代理类；
         *     (2.final Constructor<?> cons = cl.getConstructor(constructorParams);//执行构造方法
         *      (3.cons.newInstance(new Object[]{h});// 反射返回实例
         *
         * 4、Proxy内有WeakCache<ClassLoader, Class<?>[], Class<?>>，为弱引用map保存生成的代理类：
         * key： 若接口数=0时为new Object()的hashcode()；
         *       若接口数=1时为接口全路径名称的hashcode()；
         *       若接口数=2时为 31 * intf1.hashCode() + intf2.hashCode();
         *       其他情况为接口集合的hashcode()
         * value：ProxyClassFactory对象
         *
         * 其中调用get会返回代理类，内部为WeakCache代码，非Proxy逻辑：
         * 它内部用 ConcurrentMap<Object, ConcurrentMap<Object, Supplier<V>>> map保存，其中key1为classLoad，
         * key2为System.identityHashCode(key1)，value为ProxyClassFactory。
         *
         *
         * ProxyClassFactory只有一个apply方法，其中
         * ProxyGenerator.generateProxyClass方法（在sun.misc包）会生成代理类字节码数组（将次要方法和主要方法进行绑定）；
         * 最后用native方法defineClass0，将字节数组加载class对象。
         *
         *
         * 最后说下DynamicInvocationHandler，本质为接口回调，用于将调用传递给proxy对象；
         */
    }
}
