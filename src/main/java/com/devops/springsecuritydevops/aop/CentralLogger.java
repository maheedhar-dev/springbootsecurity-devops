package com.devops.springsecuritydevops.aop;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;

@Aspect
@Component
public class CentralLogger {

    Logger log = LoggerFactory.getLogger(CentralLogger.class);

    @Pointcut("execution(* com.devops.springsecuritydevops.controller.*.*(..)) || execution(* com.devops.springsecuritydevops.service.*.*(..))")
    public void pointCutDefinition(){}

    @Around("pointCutDefinition()")
    public Object logMethodActivities(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        Object[] array = proceedingJoinPoint.getArgs();
        String args;
        try {
            args = (array != null && array.length != 0) ? mapper.writeValueAsString(array) : "NONE";
        } catch (Exception e) {
            args = Arrays.toString(array); // fallback to simple string
        }
        log.info("CLASS INVOKED:{}  METHOD:{}  ARGUMENTS:{}",className,methodName,args);
        Object object = proceedingJoinPoint.proceed();
        String response;
        try {
            response = (object != null) ? mapper.writeValueAsString(object) : "NONE";
        } catch (Exception e) {
            response = object.toString();
        }
        log.info("CLASS INVOKED:{}  METHOD:{}  RESPONSE:{}",className,methodName,response);
        return object;
    }
}
