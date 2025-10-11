package com.abirch.service.impl;

import com.abirch.mapper.ClazzMapper;
import com.abirch.mapper.StudentMapper;
import com.abirch.pojo.PageResult;
import com.abirch.pojo.Student;
import com.abirch.pojo.StudentQueryParam;
import com.abirch.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public void delete(List<Integer> ids) {
        // 批量删除员工基本信息
        studentMapper.deleteById(ids);
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student getInfoById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateById(student);
    }

    @Override
    public void handleViolation(Integer id, Short score) {
        // 1. 根据ID查询学员信息
        Student student = studentMapper.getById(id);
        
        // 检查学员是否存在
        if (student == null) {
            throw new RuntimeException("学员不存在，ID：" + id);
        }
        
        // 2. 处理学员扣分
        student.setViolationScore((short) (student.getViolationScore() + score));
        // 2. 处理学员违纪次数
        student.setViolationCount((short) (student.getViolationCount() + 1));
        // 3. 更新学员信息
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateById(student);
    }
}