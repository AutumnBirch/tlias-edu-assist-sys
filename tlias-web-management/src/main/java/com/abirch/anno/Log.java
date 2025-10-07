package com.abirch.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 该注解作用于方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时保留该注解
public @interface Log {
}