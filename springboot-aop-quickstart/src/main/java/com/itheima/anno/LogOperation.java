package com.itheima.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 修饰注解的注解，叫做元注解
@Retention(RetentionPolicy.RUNTIME)
public @interface LogOperation {

}
