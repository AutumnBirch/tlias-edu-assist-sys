package com.abirch.controller;

import com.abirch.pojo.Dept;
import com.abirch.pojo.Result;
import com.abirch.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门表controller类
 */
@Slf4j
@RequestMapping("/depts")
@RestController // 标识当前类是一个请求处理类，可以将方法的返回值作为响应数据直接响应给前端，这玩意会自动将响应回前端的对象或者集合转为JSON再响应回去
public class DeptController {

//    public static final Logger log = LoggerFactory.getLogger(DeptController.class); // 固定代码

    @Autowired // 应用程序运行时，会自动查找对应类型的bean对象并赋值给该成员变量
    private DeptService deptService;

    /**
     * 功能：该方法用于查询全部部门列表数据
     * 参数：void
     * 返回值：Result 后端统一返回的结果
     */
    // @RequestMapping(value = "/depts",method = RequestMethod.GET) // 标识请求路径和请求方式
    @GetMapping
    public Result list(){
        // System.out.println("查询全部的部门数据");
        log.info("查询全部的部门数据");
        // 把查询到的数据封装到list集合中便于统一返回给前端
        List<Dept> deptList = deptService.findAll();
        // 代码能走到这一步就已经说明成功了，所以直接返回成功的结果
        return Result.success(deptList);
    }

    /**
     * 功能：删除部门
     *  如何接收前端返回的ID数据呢？
     *  方式一：靠参数HttpServletRequest request 比较繁琐，并不推荐，获取的数据都是字符串，还得强转
     *  参数：HttpServletRequest request 需要删除的部门ID
     *  方式二：spring提供的注解@RequestParam，接收前端传过来的请求参数，将请求参数绑定给方法形参，会自动进行类型转换
     *  @RequestParam注解required属性默认为true，代表该参数必须传递，如果不传递将报错。如果参数可选，可以将属性设置为false。
     *  方式三：如果请求参数名与形参变量名相同，直接定义方法形参即可接收。（省略@RequestParam）
     * 返回值：Result 是本项目用于增删改查的统一响应结果，它定义在实体包的result类里面
     */
/*
    方式一：@DeleteMapping("/depts")
    public Result delete(HttpServletRequest request){
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        System.out.println("获取的删除ID为："+id);
        // 走到这一步就代表前端发来的数据已经获取成功了，由于删除部门并不需要给前端额外返回数据，所以调用无参的success方法
        return Result.success();
    }*/
/*    // 方式二:
    @DeleteMapping("/depts")
    public Result delete(@RequestParam(value = "id",required = false) Integer deptId){
        System.out.println("获取的删除ID为："+ deptId);
        // 走到这一步就代表前端发来的数据已经获取成功了，由于删除部门并不需要给前端额外返回数据，所以调用无参的success方法
        return Result.success();
    }*/
    // 方式三:如果请求参数名与形参变量名相同，直接定义方法形参即可接收。（省略@RequestParam）
    @DeleteMapping
    public Result delete(Integer id){
        // System.out.println("获取的删除ID为："+ id);
        log.info("获取的删除ID为："+ id);
        deptService.deleteById(id);
        // 走到这一步就代表前端发来的数据已经获取成功了，由于删除部门并不需要给前端额外返回数据，所以调用无参的success方法
        return Result.success();
    }
    /**
     * 功能：新增部门
     * 参数：@RequestBody Dept dept，前面注解的作用是把请求响应过来的请求体，也就是JSON格式的请求数据封装到后面的实体类当中，
     * 想把JSON数据封装到实体类当中，就必须保证该实体类中有一个属性的属性名与JSON数据当中的键的名字保持一致
     * 这里响应过来的数据是"name": "教研部"这种形式的，刚好就能封装给实体类当中的name属性
     * 返回值：
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        System.out.println("新增部门为："+dept.getName());
        // 这个新增操作不需要返回值
        deptService.add(dept);
        return Result.success(dept);
    }
    /**
     * 获取要修改部门的信息
     * 如果路径参数名与controller方法形参名称一致，@PathVariable注解的value属性是可以省略的。此处省略前：@PathVariable("id") Integer deptId
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
//        System.out.println("根据id查询的部门为："+id);
        log.info("根据id查询的部门为：{}",id);
        // 根据id查询的数据是啥？这个要查询的数据就是返回值，所以这里是有返回值的
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
    /**
     * 修改部门
     */
    @PutMapping
    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门："+dept);
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
