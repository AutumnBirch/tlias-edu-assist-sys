package com.abirch.mapper;

import com.abirch.pojo.Student;
import com.abirch.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam studentQueryParam);

    void deleteById(List<Integer> ids);
}