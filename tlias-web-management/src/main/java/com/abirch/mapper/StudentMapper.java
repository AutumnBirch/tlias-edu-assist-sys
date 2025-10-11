package com.abirch.mapper;

import com.abirch.pojo.Student;
import com.abirch.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> list(@Param("studentQueryParam") StudentQueryParam studentQueryParam);

    void deleteById(List<Integer> ids);

    @Insert("insert into student(name,no,gender,phone,degree,clazz_id,id_card,is_College,address,graduation_date) values (#{name},#{no},#{gender},#{phone},#{degree},#{clazzId},#{idCard},#{isCollege},#{address},#{graduationDate})")
    void insert(Student student);

    Student getById(Integer id);

    void updateById(Student student);
}