package com.turing.mapper;

import com.turing.pojo.Salary;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SalaryMapper {

    //分页条件查询薪资信息
    @Select("select * from tb_salary where user_id=#{salary.userId} limit #{begin},#{size}")
    @ResultMap("salaryResultMap")
    List<Salary> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("salary") Salary salary);

    //条件查询薪资信息总页数
    @Select("select count(*) from tb_salary where user_id=#{userId}")
    int selectTotalCountAndCondition(Salary salary);

    //用userId删除薪资信息
    @Delete("delete from tb_salary where user_id=#{userId}")
    @ResultMap("salaryResultMap")
    void deleteSalaryByUserId(Salary salary);

    //添加salary
    @Insert("insert into tb_salary (id,user_id,salary,basic_salary,late_time,leave_early_time,absence_time,leave_time,date)" +
            "values (null,#{userId},#{salary},#{basicSalary},#{lateTime},#{leaveEarlyTime},#{absenceTime},#{leaveTime},#{date})")
    @ResultMap("salaryResultMap")
    void insertSalary(Salary salary);
}
