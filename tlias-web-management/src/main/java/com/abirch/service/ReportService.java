package com.abirch.service;

import com.abirch.pojo.*;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计员工职位人数
     * @return
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别人数
     * @return
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 学员学历统计
     * @return
     */
    List<Map<String, Object>> getStuDegreeData();

    /**
     * 班级人数统计
     * @return
     */
    ClazzOption getStuClazzData();

    /**
     * 日志列表查询
     * @param operateLogQueryParam
     * @return
     */
    PageResult<OperateLog> page(OperateLogQueryParam operateLogQueryParam);
}
