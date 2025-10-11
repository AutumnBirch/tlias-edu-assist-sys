package com.abirch.service.impl;

import com.abirch.mapper.EmpMapper;
import com.abirch.mapper.OperateLogMapper;
import com.abirch.mapper.StudentMapper;
import com.abirch.pojo.*;
import com.abirch.service.ReportService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public JobOption getEmpJobData() {
        // 1. 调用mapper接口,获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData(); // map: pos=教研主管,num=1

        // 2. 组装结果并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList,dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStuDegreeData() {
        return studentMapper.countStuDegreeData();
    }

    @Override
    public ClazzOption getStuClazzData() {
        // 1. 调用mapper接口,获取统计数据
        List<Map<String, Object>> list = studentMapper.countStuClazzData(); // map: pos=班级名称,num=1

        // 2. 组装结果并返回
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new ClazzOption(clazzList,dataList);
    }

    @Override
    public PageResult<OperateLog> page(OperateLogQueryParam operateLogQueryParam) {
// 1. 设置分页参数（pageHelper）
        PageHelper.startPage(operateLogQueryParam.getPage(), operateLogQueryParam.getPageSize());
        // 2. 执行查询
        List<OperateLog> logList = operateLogMapper.list(operateLogQueryParam);
        // 3. 解析查询结果并封装数据
        Page<OperateLog> l = (Page<OperateLog>) logList;
        return new PageResult<OperateLog>(l.getTotal(),l.getResult());
    }
}
