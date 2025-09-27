package com.abirch.mapper;

import com.abirch.pojo.Emp;
import com.abirch.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {
    // ==================================原始分页查询的实现方式====================================
    /**
     * 总记录数
     */
    //    @Select("select count(1) from emp e left join dept d on e.dept_id = d.id")
    //    public Long count();
    /**
     * 分页查询
     */
    //    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
    //            "order by e.update_time desc limit #{start},#{pageSize}")
    //    public List<Emp> list(Integer start,Integer pageSize);

    // ==================================pageHelper分页查询的实现方式====================================

//    @Select("select e.*,d.name from emp e left join dept d on e.dept_id = d.id" +
//            "where e.name like #{name} and e.gender = #{gender} and e.entry_date between #{begin} and #{end}" +
//            "order by e.update_time desc")
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 条件查询员工信息的方法
     * @param empQueryParam
     * @return
     */
    // SQL语句在XML文件里配置好了，所以这里没有
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工基本信息
     * @param emp
     */
    @Options(useGeneratedKeys = true, keyProperty = "id") // 获取生成的主键--主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "    values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);
}
