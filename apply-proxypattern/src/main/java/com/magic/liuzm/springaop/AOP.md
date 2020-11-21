# aopapply
笔记工程：Aop相关引入

 [什么是切面]
 
 * 采用'横向'（比如多个Service类操作db，在每个类中切入开启事务，回顾事务，提交事务，这里可画个图）抽取机制，而非'纵向'（比如继承结构（父子关系），上下层依赖关系（Service与Dao关系）。

`【若图显示失败见resources/image/aop.png】`

 ![image](https://github.com/MrLiu007/aopapply/blob/develop/src/main/resources/image/aop.png)
 
 [AOP]
* 它的全名Aspect Oriented Programming，是一种规范（面向切面编程思想，不限定语言），将横切关注点（增加能力）与业务主体进行进一步分离，以提高程序代码的模块化程度。

* 它的作用:在程序运行期间，在不修改源码的情况下对方法进行功能增强 

* 它的优势:减少重复代码，提高开发效率，并且便于维护

* 它的名词：
> ```
> （1）连接点（JoinPoint）:它是接口(主要业务（被增强）)声明的方法； 
> （2）目标对象（Target）:它是接口(主要业务)实现类； 
> （3）切入点（Pointcut）:它是接口(主要业务)实现类的方法； 
> （4）切面（Aspect）:它是次要业务（增强能力）,通知/顾问的组合。其实aop就是写次要业务,通知/顾问,这符合了面向切面的编程思想; 
> （5）织入（Weaving）:它是将主+次业务进行绑定（例如jdk的InvocationHandler.invoke方法），让次要业务在主要业务的前置/后置/环绕/最终/异常5种情况下执行; 
> （6）通知（Advice）:它是主+次业务绑定（织入）方案1，即对接口所有实现方法绑定次要业务； 
> （7）顾问（Adviser）:它是主+次业务绑定（织入）方案2，即对接口单个实现方法进行绑定次要业务；
> ```

 [AOP 与 动态代理关系]
* Jdk实代理模式步骤：
> ```
> <1.声明接口：主要业务声明接口，也就是JoinPoint集合； 
> <2.接口实现类：主要业务接口实现类，也就是Target； 
> <3.InvocationHandler接口实现类，做了2件事： 
>   3-1.次要业务逻辑书写; 
>   3-2.将次要业务与主要业务绑定;
> <4.Proxy.newProxyInstance生成代理类：需3个参数，分别为参数1（接口实现类的类加载器），参数2（声明接口集合），参数3（InvocationHandler接口实现类）；
> ```

* cglib方式（依赖cglib库）:
> ```
> <1.目标对象：主要业务类； 
> <2.MethodInterceptor方法拦截器实现类，做了2件事： 
>   2-1.次要业务逻辑书写; 
>   2-2.将次要业务与主要业务绑定; 
> <3.Enhancer增强器生产代理类，需2个参数:目标对象，MethodInterceptor方法拦截器实现类;
> ```

[AOP 与 Spring Aop关系]
* Spring aop为aop的实现，借鉴aop思想：
* Spring aop本质为Jdk/cglib方式实现，只是简化了相关步骤：

> ```
>  <1.声明接口：主要业务声明接口，也就是JoinPoint集合； 
>  <2.接口实现类：主要业务接口实现类，也就是Target； 
>  <3.次要业务（增强能力）逻辑书写; 其实Spring帮开发者封装类似InvocationHandler接口实现类 + 创建Proxy代理对象这2个步骤,开发者只用关心次要业务就行;
> ```

* Spring aop 有xml和注解2种使用aop方式：
其中xml现在在springboot项目用的少；aspectJ注解采用也是动态代理实现，非org.aspectj。


* Spring aop对目标对象引入更细的控制：
1.通知类型（5种）
> ```
> <1.前置通知： 
> 执行时机：目标对象方法之前执行通知 
> 配置文件：<aop:before method="before" pointcut-ref="myPointcut"/> 
> 注解：@Before 
> 应用场景：参数，权限校验
> ```

> ```
>  <2.后置通知： 
> 执行时机：目标对象方法之后执行通知，有异常则不执行此通知 
> 配置文件：<aop:after-returning method="afterReturning" pointcut-ref="myPointcut"/> 
> 注解：@AfterReturning 
> 应用场景：可修改方法的返回值
> ```

> ```
>  <3.最终通知： 
> 执行时机：目标对象方法之后执行通知，有没有异常都会执行此通知 
> 配置文件：<aop:after method="after" pointcut-ref="myPointcut"/> 
> 注解：@After 
> 应用场景：释放资源
> ```

> ```
>  <4.环绕通知： 
> 执行时机：目标对象方法之前和之后都会执行通知 
> 配置文件：<aop:around method="around" pointcut-ref="myPointcut"/> 
> 注解：@Around 
> 应用场景：事务、统计、权限
> ```
  
> ```
>  <5.异常抛出通知： 
> 执行时机：在抛出异常后通知
> 配置文件：<aop:after-throwing method=" afterThrowing " pointcut- ref="myPointcut"/> 
> 注解：@AfterThrowing
> 应用场景：包装异常
> ```

2.通知类型执行顺序
> ```
> <1.在目标对象方法没抛出异常时执行顺序如下： 
> 前置通知 
> 环绕通知调用目标方法之前的代码 
> 目标方法 
> 环绕通知调用目标方法之后的代码 
> 后置通知 
> 最终通知
> ```

> ```
> <2.在目标方法抛出异常是执行顺序如下： 
> 前置通知 
> 环绕通知调用目标方法之前的代码 
> 目标方法(抛出异常) 
> 异常通知 
> 最终通知
> ```

> ```
> <3.多个切面同时（采用责任链设计模式）存在时执行顺序如下： 
> 切面的配置顺序决定了切面的执行顺序;（order越小越先执行，见图resources/image/1153743-20170917123507547-473922532.png）
> 多个切面执行的过程，类似于方法调用的过程，每次到环绕通知的proceed方法时(JdkDynamicAopProxy.invoke或者CglibAopProxy.intercept里逻辑)
> 就去执行下一个切面或执行当前切面的目标方法，见下面2张图（执行过程）：
> ```

`多切面时正确情况【若图显示失败见resources/image/1295451-20181018094517127-714598361.png】`

![image](https://github.com/MrLiu007/aopapply/blob/develop/src/main/resources/image/1295451-20181018094517127-714598361.png)

`多切面时异常情况【若图显示失败见resources/image/1295451-20181018094517127-714598361.png】`

![image](https://github.com/MrLiu007/aopapply/blob/develop/src/main/resources/image/1295451-20181018094517127-714598361.png)


[Spring Aop实现机制](https://github.com/MrLiu007/aopapply/blob/release/apply-proxypattern/src/main/java/com/magic/liuzm/springaop/SPRING_AOP.md)


[Spring tx实现机制](https://github.com/MrLiu007/aopapply/blob/release/apply-proxypattern/src/main/java/com/magic/liuzm/springaop/SPRING_TX.md)