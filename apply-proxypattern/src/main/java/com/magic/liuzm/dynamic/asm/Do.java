package com.magic.liuzm.dynamic.asm;

import com.magic.liuzm.dynamic.base.Teacher;

/**
 * @author zemin.liu
 * @date 2020/11/18 09:46
 * @description 操作例子
 */
public class Do {

    public static void main(String[] args) throws Throwable {
        // 真实角色对象
        Teacher teacher = new Teacher();
        // 抽象增强角色对象（这里可不用给，看业务情况，可在执行器中打印一行日志也行）
        AspectTeacher aspectTeacher = new AspectTeacher();

        Object object = DynamicHandler.invoke(teacher,aspectTeacher);

        // 使用target类型的引用接收这个对象
        if (!(object instanceof Teacher)) {
            return;
        }
        Teacher proxy = (Teacher)object;
        // 调用业务方法
        proxy.delete(001);
    }

    /**
     * 官网：
     * http://asm.ow2.org/
     *
     *
     *
     *
     *
     */
}
