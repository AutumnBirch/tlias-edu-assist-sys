package com.abirch.service;

import com.abirch.pojo.Clazz;
import com.abirch.pojo.ClazzQueryParam;
import com.abirch.pojo.Emp;
import com.abirch.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 分页查询的方法
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 根据ID删除班级信息
     */
    void delete(Integer id);

    /**
     * 添加班级
     */
    void add(Clazz clazz);

    /**
     * 根据ID查询班级信息
     */
    Clazz getInfo(Integer id);

}
