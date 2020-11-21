package com.magic.liuzm.dynamic.cglib;

import com.magic.liuzm.dynamic.base.AspectTeacher;
import com.magic.liuzm.dynamic.base.Teacher;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author zemin.liu
 * @date 2020/11/17 17:10
 * @description 操作例子
 */
public class Do {

    public static void main(String[] args) {
        //代理类class文件存入本地磁盘
        String path = "/Users/cicada/IdeaProjects/demo/aopapply/apply-proxypattern/target/classes/";
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, path);

        // 真实角色对象
        Teacher teacher = new Teacher();
        // 抽象增强角色对象（这里可不用给，看业务情况，可在执行器中打印一行日志也行）
        AspectTeacher aspectTeacher = new AspectTeacher();
        // 方法拦截器
        DynamicMethodInterceptor interceptor = new DynamicMethodInterceptor();
        // 绑定真实角色对象
        interceptor.setTarget(teacher);
        interceptor.setAspect(aspectTeacher);

        // 增强器
        Enhancer enhancer = new Enhancer();
        // 绑定真实角色对象（可为类或者接口）
        enhancer.setSuperclass(teacher.getClass());
        // 绑定方法拦截器
        enhancer.setCallback(interceptor);
        // 创建代理对象
        Teacher proxy = (Teacher) enhancer.create();
        // 调用业务方法
        proxy.delete(001);
    }


    /**
     * 特殊说明：
     *
     * 1.cglib不支持final，static修饰的目标对象类，因为它要继承目标对象。
     * 2.cglib封装了asm，实现底层逻辑。
     *
     * 从Enhancer的create()方法开始分析:
     *
     * 核心方法为createHelper(),内部调用EnhancerKey接口的newInstance(),
     * 其中Enhancer内部保存着这个EnhancerKey接口实现对象（KEY_FACTORY）。
     *
     * 这个KEY_FACTORY是通过KeyFactory.create()创建，她又是通过Enhancer父类的AbstractClassGenerator.create()
     * 创建代理类，此方法内部真正创建代理对象方法在nextInstance()方法（AbstractClassGenerator抽象类，需子类实现），
     * 这样它本质是Enhancer的nextInstance().
     *
     *
     * Enhancer的nextInstance()方法：
     * 最终的data.newInstance(argumentTypes, arguments, callbacks)方法返回代理对象，其中
     * 参数1为代理对象的构成器，参数2位代理对象构造方法参数，参数3为回调对象（也就是自定义DynamicMethodInterceptor）
     *
     * 最后说下DynamicMethodInterceptor，本质为接口回调，用于将调用传递给proxy对象；
     *
     */
}
