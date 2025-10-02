package com.abirch.controller;

import com.abirch.pojo.Emp;
import com.abirch.pojo.EmpQueryParam;
import com.abirch.pojo.PageResult;
import com.abirch.pojo.Result;
import com.abirch.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 员工管理Controller
 */
@Slf4j // 记录日志
@RequestMapping("/emps")
@RestController
public class EmpController {
    // 注入service
    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     */
/*    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page ,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("查询员工信息, page={}, pageSize={}", page, pageSize);
        PageResult pageResult = empService.page(page, pageSize);
        return Result.success(pageResult);
    }*/
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("条件分页查询：{}",empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工信息：{}",emp);
        empService.save(emp);
        return Result.success();
    }

/*    *//**
     * 删除员工-数组
     *//*
    @DeleteMapping
    public Result delete(Integer[] ids){
        log.info("删除员工：{}", Arrays.toString(ids));

        return Result.success();
    }*/
    /**
     * 删除员工-集合
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据Id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据Id查询员工信息：{}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工：{}",emp);

        empService.update(emp);

        return Result.success();
    }

}
