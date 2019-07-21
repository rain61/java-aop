package com.ms.aop.config;


import com.ms.aop.aop.MyAop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MyAopConfig {

    private Logger logger = LoggerFactory.getLogger(AopConfig.class);

    @Around("@annotation(myAop)")
    public Object around(ProceedingJoinPoint joinPoint, MyAop myAop) throws Throwable {
        Object result = null;
        long startTime = System.currentTimeMillis();
        result = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println(" -------------> Time Taken by " + joinPoint + " with param[" + myAop.param() + "] is " + timeTaken);
        return result;
    }
}
