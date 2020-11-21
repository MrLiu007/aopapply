这里为采用org.aspectj，实现静态代理：

原理: 在编译期织入代码，编译成class文件
优点: 可增强任何类，任何方法，包括（final,static修饰）
缺点: 需要使用aspecj提供的Ajc编译器来编译Aj文件。

这里直说原理，不举例，例子请看apply-aspectj模块。
