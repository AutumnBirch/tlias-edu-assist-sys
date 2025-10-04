package com.abirch.mapper;

import com.abirch.pojo.Emp;
import com.abirch.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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

    void deleteById(List<Integer> ids);

    /**
     * 根据id查询员工信息和员工工作经历
     */
    Emp getById(Integer id);

    /**
     * 根据ID更新员工的基本信息
     */
    void updateById(Emp emp);
    /**
     * 查询员工信息统计
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 根据用户名和密码查询员工信息
     */
    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
    @Select("select * from emp")
    List<Emp> getAllEmpInfo();
}
