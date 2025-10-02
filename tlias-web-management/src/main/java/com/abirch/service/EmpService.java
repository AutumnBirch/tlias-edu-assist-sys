package com.abirch.service;

import com.abirch.pojo.Emp;
import com.abirch.pojo.EmpQueryParam;
import com.abirch.pojo.PageResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询的方法
     */
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     * @param emp
     */
    void save(Emp emp);

    /**
     * 批量删除员工信息
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工
     * @param emp
     */
    void update(Emp emp);
}
