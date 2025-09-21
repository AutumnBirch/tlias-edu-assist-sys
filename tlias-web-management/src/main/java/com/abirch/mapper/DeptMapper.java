package com.abirch.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper // 应用程序会在运行时自动地为该接口创建一个实现类对象（代理对象），并且会自动地将实现类对象放入IOC容器
public interface DeptMapper {

}
