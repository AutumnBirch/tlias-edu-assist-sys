package com.abirch.service.impl;

import com.abirch.mapper.EmpExprMapper;
import com.abirch.mapper.EmpMapper;
import com.abirch.pojo.*;
import com.abirch.service.EmpLogService;
import com.abirch.service.EmpService;
import com.abirch.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service //声明为springIOC容器的Bean
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    // ==================================原始分页查询的实现方式====================================
/*    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {

        // 1. 调用mapper接口，查询总记录数
        Long total = empMapper.count();
        // 2. 调用mapper接口，查询结果列表
        Integer start = (page - 1)*pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);
        // 3. 封装结果 pageResult
        return new PageResult<Emp>(total,rows);
    }*/

    // ==================================pageHelper分页查询的实现方式====================================

    /**
     * 基于pageHelper来进行分页查询
     * @return
     */
/*    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        // 1. 设置分页参数（pageHelper）
        PageHelper.startPage(page,pageSize);
        // 2. 执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        // 3. 解析查询结果，并封装数据
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }*/


    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 1. 设置分页参数（pageHelper）
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        // 2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        // 3. 解析查询结果，并封装数据
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }
    @Transactional // 事务管理注释
    @Override
    public void save(Emp emp) {
        try {
            // 1. 保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            // 2. 保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)){
                // 遍历集合，为empId赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally{
            // 记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        // 1. 批量删除员工基本信息
        empMapper.deleteById(ids);
        // 2. 批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        // 1. 根据ID修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        // 2. 根据ID修改员工工作经历信息信息
        // 2.1 先删除
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        // 2.1 再添加

        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
        }
        empExprMapper.insertBatch(exprList);
    }

    @Override
    public LoginInfo login(Emp emp) {
        // 1. 调用mapper接口，根据用户名和密码查询员工信息
        // mapper中的方法名可以不和业务层的方法名一样，不过还是得尽量做到见名知意
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        // 2. 判断是否存在员工，存在：组装登录成功信息
        if (e != null) {
            log.info("登录成功，员工信息为：{}",e);
            // 生成jwt令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);


            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        }
        // 3. 不存在，返回null
        return null;
    }

    @Override
    public List<Emp> findAll() {
        List<Emp> empList = empMapper.getAllEmpInfo();
        return empList;
    }
}
