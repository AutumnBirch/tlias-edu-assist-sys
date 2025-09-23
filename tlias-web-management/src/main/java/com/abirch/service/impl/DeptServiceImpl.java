package com.abirch.service.impl;

import com.abirch.mapper.DeptMapper;
import com.abirch.pojo.Dept;
import com.abirch.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 功能：查询所有部门
     * @return
     */
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }
    /**
     * 功能：根据ID删除部门
     */
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }
    /**
     * 功能：新增部门
     */
    @Override
    public void add(Dept dept) {
        // 1. 补全基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 2. 调用mapper接口
        deptMapper.insert(dept);
    }

    /**
     * 根据ID查询部门
     */
    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        // 1. 补全基础属性-updateTime
        dept.setUpdateTime(LocalDateTime.now());
        // 2. 调用mapper接口方法更新部门数据
        deptMapper.update(dept);

    }
}
