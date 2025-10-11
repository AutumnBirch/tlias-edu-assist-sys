package com.abirch.service;

import com.abirch.pojo.PageResult;
import com.abirch.pojo.Student;
import com.abirch.pojo.StudentQueryParam;
import org.springframework.stereotype.Component;

@Component
public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);
}
