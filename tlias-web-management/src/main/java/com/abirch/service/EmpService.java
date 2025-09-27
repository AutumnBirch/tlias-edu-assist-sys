package com.abirch.service;

import com.abirch.pojo.Emp;
import com.abirch.pojo.EmpQueryParam;
import com.abirch.pojo.PageResult;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
