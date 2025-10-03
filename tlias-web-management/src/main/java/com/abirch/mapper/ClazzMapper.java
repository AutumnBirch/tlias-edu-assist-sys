package com.abirch.mapper;

import com.abirch.pojo.Clazz;
import com.abirch.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 条件查询班级信息的方法
     * @param clazzQueryParam
     * @return
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);
}
