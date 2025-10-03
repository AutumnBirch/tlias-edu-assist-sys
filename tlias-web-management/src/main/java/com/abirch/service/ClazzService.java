package com.abirch.service;

import com.abirch.pojo.Clazz;
import com.abirch.pojo.ClazzQueryParam;
import com.abirch.pojo.PageResult;

public interface ClazzService {
    /**
     * 分页查询的方法
     * @param clazzQueryParam
     * @return
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);
}
