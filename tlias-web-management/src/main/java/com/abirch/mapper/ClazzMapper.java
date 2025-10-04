package com.abirch.mapper;

import com.abirch.pojo.Clazz;
import com.abirch.pojo.ClazzQueryParam;
import com.abirch.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 条件查询班级信息的方法
     * @param clazzQueryParam
     * @return
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    @Delete("delete from clazz where id=#{id}")
    void delete(Integer id);

//    /**
//     * 查询所有员工信息
//     * @return
//     */
//    @Select("select name from emp")
//    List<Emp> getEmpList();
}
