package com.abirch.service.impl;

import com.abirch.mapper.EmpMapper;
import com.abirch.mapper.StudentMapper;
import com.abirch.pojo.ClazzOption;
import com.abirch.pojo.JobOption;
import com.abirch.service.ReportService;
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
}
