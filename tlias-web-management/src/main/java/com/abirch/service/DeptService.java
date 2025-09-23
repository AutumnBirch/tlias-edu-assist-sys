package com.abirch.service;

import com.abirch.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 将该类交给IOC容器管理
public interface DeptService {
    /**
     * 功能：查询所有部门
     */
    List<Dept> findAll();

    /**
     * 根据ID删除部门
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 根据Id查询部门
     * @param id
     * @return
     */
    Dept getById(Integer id);

    /**
     * 根据id修改部门
     * @param dept
     */
    void update(Dept dept);
}
