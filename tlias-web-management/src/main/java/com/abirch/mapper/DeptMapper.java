package com.abirch.mapper;

import com.abirch.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 应用程序会在运行时自动地为该接口创建一个实现类对象（代理对象），并且会自动地将实现类对象放入IOC容器
public interface DeptMapper {

    @Results({

    })

    // 查询所有的部门数据
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    /**
     * 功能：根据ID删除部门
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept(name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);
    // 根据Id查询部门数据
    @Select("select id,name,create_time,update_time from dept where id = #{id}")
    Dept getById(Integer id);
    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
