package com.magic.liuzm.dynamic.asm;

import com.magic.liuzm.dynamic.base.AspectService;
import com.magic.liuzm.dynamic.base.Teacher;
import lombok.extern.log4j.Log4j2;
import jdk.internal.org.objectweb.asm.Opcodes;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.MethodVisitor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author zemin.liu
 * @date 2020/11/18 10:05
 * @description 代理类处理器
 */
@Log4j2
public class DynamicHandler extends ClassLoader implements Opcodes{

    public static Object invoke(Teacher target,AspectTeacher aspect) throws Throwable {
        // 目标类类名 字节码中类修饰符以 “/” 分割
        String targetName = target.getClass().getName().replace(".", "/");
        // 切面类类名
        String aspectName = aspect.getClass().getName().replace(".", "/");
        // 代理类类名
        String proxyName = targetName + "Proxy";
        // 创建一个 classWriter 它是继承了ClassVisitor
        ClassWriter classWriter = new ClassWriter(0);
        // 访问类 指定jdk版本号为1.8, 修饰符为 public，父类是target
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, proxyName, null, targetName, null);
        // 访问目标类成员变量 为类添加切面属性 “private Teacher Teacher”
        classWriter.visitField(ACC_PRIVATE, "target", "L" + targetName+";", null, null);
        // 访问切面类成员变量 为类添加目标属性 “private AspectTeacher AspectTeacher”
        classWriter.visitField(ACC_PRIVATE, "aspect", "L" + aspectName+";", null, null);

        // 访问默认构造方法 TeacherProxy()
        // 定义函数 修饰符为public 方法名为 <init>， 方法表述符为()V 表示无参数，无返回参数
        MethodVisitor initVisitor1 = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        // 从局部变量表取第0个元素 “this”
        initVisitor1.visitVarInsn(ALOAD, 0);
        // 调用super 的构造方法 invokeSpecial在这里的意思是调用父类方法
        initVisitor1.visitMethodInsn(INVOKESPECIAL, targetName, "<init>", "()V", false);
        // 方法返回
        initVisitor1.visitInsn(RETURN);
        // 设置最大栈数量，最大局部变量表数量
        initVisitor1.visitMaxs(1, 1);
        // 访问结束
        initVisitor1.visitEnd();

        // 创建代理方法 修饰符为public，方法名为setTarget
        MethodVisitor setTargetVisitor = classWriter.visitMethod(ACC_PUBLIC, "setTarget", "(L" + Object.class + ";)V", null, null);
        // 从局部变量表取第0个元素 “this”压入栈顶
        setTargetVisitor.visitVarInsn(ALOAD, 0);
        // 从局部变量表取第1个元素 “target”压入栈顶
        setTargetVisitor.visitVarInsn(ALOAD, 1);
        // this 和 target 出栈, 调用target put 赋值给this.target
        setTargetVisitor.visitFieldInsn(PUTFIELD, proxyName, "target", "L" + Object.class + ";");
        // 方法返回
        setTargetVisitor.visitInsn(RETURN);
        // 设置最大栈数量，最大局部变量表数量
        setTargetVisitor.visitMaxs(2, 3);
        // 方法返回
        setTargetVisitor.visitEnd();

        // 创建代理方法 修饰符为public，方法名为setAspect
        MethodVisitor setAspectVisitor = classWriter.visitMethod(ACC_PUBLIC, "setAspect", "(L" + aspect.getClass().getName() + ";)V", null, null);
        // 从局部变量表取第0个元素 “this”压入栈顶
        setAspectVisitor.visitVarInsn(ALOAD, 0);
        // 从局部变量表取第1个元素 “aspect”压入栈顶
        setAspectVisitor.visitVarInsn(ALOAD, 1);
        // this 和 target 出栈, 调用target put 赋值给this.target
        setAspectVisitor.visitFieldInsn(PUTFIELD, proxyName, "aspect", "L" + aspect.getClass().getName() + ";");
        // 方法返回
        setAspectVisitor.visitInsn(RETURN);
        // 设置最大栈数量，最大局部变量表数量
        setAspectVisitor.visitMaxs(2, 3);
        // 方法返回
        setAspectVisitor.visitEnd();


        // 创建代理方法 修饰符为public，方法名为delete
        MethodVisitor deleteVisitor = classWriter.visitMethod(ACC_PUBLIC, "delete", "(L" + String.class + ";L" + Integer.class + ";)V", null, null);
        // 从局部变量表取第0个元素 “this”压入栈顶
        deleteVisitor.visitVarInsn(ALOAD, 0);
        // this 出栈 将this.aspectService压入栈顶
        deleteVisitor.visitFieldInsn(GETFIELD, proxyName, "target", "L"+aspectName+";");
        // 从局部变量表取第1个元素(入参数) “var1”压入栈顶
        deleteVisitor.visitVarInsn(ALOAD, 1);
        deleteVisitor.visitVarInsn(ALOAD, 2);
        // 取栈顶元素出栈 也就是target 调用其preLog方法
        deleteVisitor.visitMethodInsn(INVOKEVIRTUAL, aspectName,"preLog", "(L" + String.class + ";L" + Integer.class + ";)V", false);
        // 从局部变量表取第0个元素 “this”压入栈顶
        deleteVisitor.visitVarInsn(ALOAD, 0);
        // this 出栈, 取this.targetService压入栈顶
        deleteVisitor.visitFieldInsn(GETFIELD, proxyName, "aspect", "L"+targetName+";");
        // 从局部变量表取第1个元素(入参数) “var1”压入栈顶
        deleteVisitor.visitVarInsn(ALOAD, 2);
        // 取栈顶元素出栈 也就是aspect调用其delete方法
        deleteVisitor.visitMethodInsn(INVOKEVIRTUAL, targetName, "delete", "(L" + Integer.class + ";)V", false);
        // 方法返回
        deleteVisitor.visitInsn(RETURN);
        // 设置最大栈数量，最大局部变量表数量
        deleteVisitor.visitMaxs(1, 1);
        // 方法返回
        deleteVisitor.visitEnd();

        // 生成字节码二进制流
        byte[] code = classWriter.toByteArray();
        // 持久化(非必选，生产文件方便调试)
//        doSaveFile(proxyName, code);
        // 自定义classloader加载类
        Class<?> clazz = (new DynamicHandler()).defineClass(Teacher.class.getName() + "Proxy", code, 0, code.length);
        Object object = clazz.newInstance();
        return object;
    }

    /**
     * 若需要持久化，就调用此方法
     */
    private static void doSaveFile(String proxyName, byte[] code) throws IOException {
        String path = "/Users/cicada/IdeaProjects/demo/aopapply/apply-proxypattern/target/classes/" + proxyName + ".class";
        File file = new File(path);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            // 不存在创建目录
            fileParent.mkdirs();
        }
        if (file.exists()){
            // 删除旧文件
            file.delete();
            // 创建新文件
            file.createNewFile();
        }else {
            // 创建新文件
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(code);
        if(fos != null){
            fos.close();
        }
    }
}
