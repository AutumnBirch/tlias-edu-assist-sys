package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MyAspect5 {

    @Before("@annotation(com.itheima.anno.LogOperation)")
    public void before(){
        log.info("before ......");
    }

}
