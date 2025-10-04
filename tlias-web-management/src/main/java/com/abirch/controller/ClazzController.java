package com.abirch.controller;

import com.abirch.pojo.*;
import com.abirch.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班级管理Controller
 */
@Slf4j // 记录日志
@RequestMapping("/clazzs")
@RestController// 标识当前类是一个请求处理类，可以将方法的返回值作为响应数据直接响应给前端，这玩意会自动将响应回前端的对象或者集合转为JSON再响应回去
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("条件分页查询：{}",clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 查询所有员工
     */
//    @GetMapping("/list")
//    public Result list(){
//        log.info("查询全部员工名称：");
//        List<Emp> empList = clazzService.findAll();
//        return Result.success(empList);
//    }


    /**
     * 删除班级
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据ID删除班级信息：{}",id);
        clazzService.delete(id);
        return Result.success();
    }

}
