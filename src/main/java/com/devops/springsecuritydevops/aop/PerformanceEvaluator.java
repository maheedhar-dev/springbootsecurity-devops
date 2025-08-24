package com.devops.springsecuritydevops.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceEvaluator {
Logger log = LoggerFactory.getLogger(PerformanceEvaluator.class);

    @Around("@annotation(com.devops.springsecuritydevops.aop.TimeEvaluator)")
    public Object evaluateMethodPerformance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        Long startTime = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        log.info("CLASS: {} METHOD: {} TIME-TAKEN: {} ",className,methodName,(endTime-startTime));
        return object;
    }

}
