package com.sparta.spartabulletinboardbackend.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class TimeTraceAop {
    @Pointcut("execution(* com.sparta.spartabulletinboardbackend.post..*(..))")
    public void post() {}

    @Pointcut("execution(* com.sparta.spartabulletinboardbackend.user..*(..))")
    public void user() {}

    @Pointcut("execution(* com.sparta.spartabulletinboardbackend.comment..*(..))")
    public void comment() {}

    @Around("post() || user() || comment()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
