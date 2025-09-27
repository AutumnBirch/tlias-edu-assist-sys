package com.abirch.service.impl;

import com.abirch.mapper.EmpMapper;
import com.abirch.pojo.Emp;
import com.abirch.pojo.EmpQueryParam;
import com.abirch.pojo.PageResult;
import com.abirch.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service //声明为springIOC容器的Bean
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    // ==================================原始分页查询的实现方式====================================
/*    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {

        // 1. 调用mapper接口，查询总记录数
        Long total = empMapper.count();
        // 2. 调用mapper接口，查询结果列表
        Integer start = (page - 1)*pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);
        // 3. 封装结果 pageResult
        return new PageResult<Emp>(total,rows);
    }*/

    // ==================================pageHelper分页查询的实现方式====================================

    /**
     * 基于pageHelper来进行分页查询
     * @param page 页码
     * @param pageSize 每页记录数
     * @return
     */
/*    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        // 1. 设置分页参数（pageHelper）
        PageHelper.startPage(page,pageSize);
        // 2. 执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        // 3. 解析查询结果，并封装数据
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }*/
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 1. 设置分页参数（pageHelper）
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        // 2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        // 3. 解析查询结果，并封装数据
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }
}
