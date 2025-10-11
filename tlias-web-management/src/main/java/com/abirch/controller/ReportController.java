package com.abirch.controller;

import com.abirch.pojo.ClazzOption;
import com.abirch.pojo.JobOption;
import com.abirch.pojo.Result;
import com.abirch.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计员工职位人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别数据
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别数据");
        List<Map<String,Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }
    /**
     * 学员学历统计
     */
    @GetMapping("/studentDegreeData")
    public Result getStuDegreeData(){
        log.info("学员学历统计");
        List<Map<String,Object>> degreeList = reportService.getStuDegreeData();
        return Result.success(degreeList);
    }
    /**
     * 班级人数统计
     */
    @GetMapping("/studentCountData")
    public Result getStuClazzData(){
        log.info("班级人数统计");
        ClazzOption clazzOption = reportService.getStuClazzData();
        return Result.success(clazzOption);
    }
}
