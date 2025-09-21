package com.abirch.controller;

import com.abirch.pojo.Dept;
import com.abirch.pojo.Result;
import com.abirch.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门表controller类
 */
@RestController // 标识当前类是一个请求处理类，可以将方法的返回值作为响应数据直接响应给前端，这玩意会自动将响应回前端的对象或者集合转为JSON再响应回去
public class DeptController {

    @Autowired // 应用程序运行时，会自动查找对应类型的bean对象并赋值给该成员变量
    private DeptService deptService;

    /**
     * 功能：该方法用于查询全部部门列表数据
     * 参数：void
     * 返回值：Result 后端统一返回的结果
     */
    // @RequestMapping(value = "/depts",method = RequestMethod.GET) // 标识请求路径和请求方式
    @GetMapping("/depts")
    public Result list(){
        System.out.println("查询全部的部门数据");
        // 把查询到的数据封装到list集合中便于统一返回给前端
        List<Dept> deptList = deptService.findAll();
        // 代码能走到这一步就已经说明成功了，所以直接返回成功的结果
        return Result.success(deptList);
    }

}
