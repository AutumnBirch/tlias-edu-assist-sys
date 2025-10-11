package com.abirch.controller;

import com.abirch.pojo.PageResult;
import com.abirch.pojo.Result;
import com.abirch.pojo.Student;
import com.abirch.pojo.StudentQueryParam;
import com.abirch.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生管理Controller
 */
@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("条件分页查询学生信息：{}",studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }
    /**
     * 删除学员
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("根据ID删除学员：{}",ids);
        studentService.delete(ids);
        return Result.success();
    }
    /**
     * 添加学员
     */
    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("添加学员信息：{}",student);
        studentService.add(student);
        return Result.success();
    }
    /**
     * 根据ID查询学员
     */
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id){
        log.info("查询的学员ID为：{}",id);
        Student student = studentService.getInfoById(id);
        return Result.success(student);
    }
}