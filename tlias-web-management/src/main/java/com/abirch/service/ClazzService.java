package com.abirch.service;

import com.abirch.pojo.Clazz;
import com.abirch.pojo.ClazzQueryParam;
import com.abirch.pojo.Emp;
import com.abirch.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 分页查询的方法
     * @param clazzQueryParam
     * @return
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 根据ID删除班级信息
     * @param id
     */
    void delete(Integer id);

    /**
     * 查询所有员工
     * @return
     */
//    List<Emp> findAll();

}
