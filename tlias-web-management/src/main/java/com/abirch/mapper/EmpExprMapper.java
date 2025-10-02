package com.abirch.mapper;

import com.abirch.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * 员工工作经历
 */
@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存员工的工作经历
     */
    void insertBatch(List<EmpExpr> exprList);
    /**
     * 批量删除员工的工作经历信息
     */

    void deleteByEmpIds(List<Integer> empIds);

}
