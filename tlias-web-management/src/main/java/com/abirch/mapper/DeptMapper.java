package com.abirch.mapper;

import com.abirch.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper // 应用程序会在运行时自动地为该接口创建一个实现类对象（代理对象），并且会自动地将实现类对象放入IOC容器
public interface DeptMapper {

    // 查询所有的部门数据
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();
}
