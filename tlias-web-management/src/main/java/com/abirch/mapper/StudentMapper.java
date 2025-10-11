package com.abirch.mapper;

import com.abirch.pojo.Student;
import com.abirch.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam studentQueryParam);

    void deleteById(List<Integer> ids);

    @Insert("insert into student(name,no,gender,phone,degree,clazz_id,id_card,is_College,address,graduation_date) values (#{name},#{no},#{gender},#{phone},#{degree},#{clazzId},#{idCard},#{isCollege},#{address},#{graduationDate})")
    void insert(Student student);

    Student getById(Integer id);

    void updateById(Student student);

    @MapKey("degreeStr")
    List<Map<String, Object>> countStuDegreeData();
    @MapKey("test")
    List<Map<String, Object>> countStuClazzData();
}