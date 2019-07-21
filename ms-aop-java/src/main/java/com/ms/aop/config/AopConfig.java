package com.ms.aop.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author huahua
 * 2019/7/20
 * aop 切面配置类
 */
@Aspect
@Component
@ComponentScan("com.ms.aop.controller.*")
public class AopConfig {

    private Logger logger = LoggerFactory.getLogger(AopConfig.class);

    /**
     * 定义切入点，切入点为com.ms.aop下的所有函数
     */
    @Pointcut("execution(public * com.ms.aop..*.*(..))) ")
    public void webLog(){}

    /**
     * 前置通知：在连接点之前执行的通知
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
       /* // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));*/

    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret);
    }


 /*   定义切入点的时候需要一个包含名字和任意参数的签名，还有一个切入点表达式，如execution(public * com.example.aop...(..))

    切入点表达式的格式：execution([可见性]返回类型[声明类型].方法名(参数)[异常])
    其中[]内的是可选的，其它的还支持通配符的使用：
            1) *：匹配所有字符
2) ..：一般用于匹配多个包，多个参数
3) +：表示类及其子类
4)运算符有：&&,||,!

    切入点表达式关键词用例：
            1）execution：用于匹配子表达式。
//匹配com.cjm.model包及其子包中所有类中的所有方法，返回类型任意，方法参数任意
    @Pointcut(“execution(* com.cjm.model...(..))”)
    public void before(){}

2）within：用于匹配连接点所在的Java类或者包。
    //匹配Person类中的所有方法
    @Pointcut(“within(com.cjm.model.Person)”)
    public void before(){}
//匹配com.cjm包及其子包中所有类中的所有方法
    @Pointcut(“within(com.cjm..*)”)
    public void before(){}

3） this：用于向通知方法中传入代理对象的引用。
    @Before(“before() && this(proxy)”)
    public void beforeAdvide(JoinPoint point, Object proxy){
//处理逻辑
    }

4）target：用于向通知方法中传入目标对象的引用。
    @Before(“before() && target(target)
            public void beforeAdvide(JoinPoint point, Object proxy){
//处理逻辑
    }

5）args：用于将参数传入到通知方法中。
    @Before(“before() && args(age,username)”)
    public void beforeAdvide(JoinPoint point, int age, String username){
//处理逻辑
    }

6）@within ：用于匹配在类一级使用了参数确定的注解的类，其所有方法都将被匹配。
    @Pointcut(“@within(com.cjm.annotation.AdviceAnnotation)”)
－ 所有被@AdviceAnnotation标注的类都将匹配
    public void before(){}

7）@target ：和@within的功能类似，但必须要指定注解接口的保留策略为RUNTIME。
    @Pointcut(“@target(com.cjm.annotation.AdviceAnnotation)”)
    public void before(){}

8）@args ：传入连接点的对象对应的Java类必须被@args指定的Annotation注解标注。
    @Before(“@args(com.cjm.annotation.AdviceAnnotation)”)
    public void beforeAdvide(JoinPoint point){
//处理逻辑
    }

9）@annotation ：匹配连接点被它参数指定的Annotation注解的方法。也就是说，所有被指定注解标注的方法都将匹配。
    @Pointcut(“@annotation(com.cjm.annotation.AdviceAnnotation)”)
    public void before(){}

10）bean：通过受管Bean的名字来限定连接点所在的Bean。该关键词是Spring2.5新增的。
    @Pointcut(“bean(person)”)
    public void before(){}*/

}
