package com.ms.aop.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ms.aop.aop.ExceptionCatch;
import com.ms.aop.service.KafkaProducerImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @author huahua
 * 2019/7/20
 * 异常切面类 获取异常并 输出
 */
@Aspect
@Component
public class ExceptionAopConfig {

    @Resource
    KafkaProducerImpl kafkaProducer;

    //切入点
    @Pointcut(("@annotation(exceptionCatch)"))
    public void saveException(ExceptionCatch exceptionCatch){}



    @AfterThrowing(pointcut = "saveException(exceptionCatch)",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception,ExceptionCatch exceptionCatch) {

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String url = request.getRequestURL().toString();
        String  method_name =   joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

        String string = JSON.toJSONString(exception,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);
        String message = exception.getMessage();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("url",url);
        map.put("method_name",method_name);
        map.put("message",message);
        //map.put("error",string);

        kafkaProducer.sendMassage(map);
    }
}
