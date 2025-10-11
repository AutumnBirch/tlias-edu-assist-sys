package com.abirch.service.impl;

import com.abirch.mapper.StudentMapper;
import com.abirch.pojo.PageResult;
import com.abirch.pojo.Student;
import com.abirch.pojo.StudentQueryParam;
import com.abirch.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        // 1. 设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        // 2. 执行查询
        List<Student> studentList = studentMapper.list(studentQueryParam);
        // 3. 解析查询结果并封装数据
        Page<Student> s = (Page<Student>) studentList;
        return new PageResult<Student>(s.getTotal(),s.getResult());
    }
}