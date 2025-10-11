package com.abirch.service;

import com.abirch.pojo.PageResult;
import com.abirch.pojo.Student;
import com.abirch.pojo.StudentQueryParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentService {
    /**
     * 分页查询
     * @param studentQueryParam
     * @return
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 删除学员
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 添加学员
     * @param student
     */
    void add(Student student);

    /**
     * 根据ID查询学员
     * @param id
     * @return
     */
    Student getInfoById(Integer id);

    /**
     * 修改学员
     * @param student
     */
    void update(Student student);
}
