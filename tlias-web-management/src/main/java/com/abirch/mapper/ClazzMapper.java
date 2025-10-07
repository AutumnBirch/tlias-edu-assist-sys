package com.abirch.mapper;

import com.abirch.pojo.Clazz;
import com.abirch.pojo.ClazzQueryParam;
import com.abirch.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 条件查询班级信息的方法
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    @Delete("delete from clazz where id=#{id}")
    void delete(Integer id);

    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject) values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject})")
    void insert(Clazz clazz);

    Clazz getById(Integer id);

    void updateById(Clazz clazz);
    @Select("select * from clazz")
    List<Clazz> getAllClazzInfo();
}
