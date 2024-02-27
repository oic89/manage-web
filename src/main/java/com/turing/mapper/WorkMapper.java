package com.turing.mapper;

import com.turing.pojo.Work;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface WorkMapper {

    //分页条件查询在职用户出勤信息
    List<Work> selectByPageAndCondition(Work work);

    //条件查询在职用户出勤信息总页数
    int selectTotalCountAndCondition(Work work);

    //分页条件查询登录用户出勤信息
    List<Work> selectByPageAndCondition1(Work work);

    //条件查询登录用户出勤信息总页数
    int selectTotalCountAndCondition1(Work work);

    //修改出勤信息
    @Update("update tb_work set type=#{type},date=#{date} where id=#{id}")
    @ResultMap("workResultMap")
    void updateWork(Work work);

    //删除出勤信息
    @Delete("delete from tb_work where id=#{id}")
    @ResultMap("workResultMap")
    void delete(Work work);

    //新增出勤信息
    @Insert("insert into tb_work (id,user_id,name,face,sex,account,type,date)values (null,#{userId},#{name},#{face},#{sex},#{account},#{type},#{date})")
    @ResultMap("workResultMap")
    void insertWork(Work work);

    //用userId删除work
    @Delete("delete from tb_work where user_id=#{userId}")
    @ResultMap("workResultMap")
    void deleteWorkByUserId(Work work);

    //用userId修改work
    @Update("update tb_work set name=#{name},face=#{face},sex=#{sex},account=#{account} where user_id=#{userId}")
    @ResultMap("workResultMap")
    void updateWorkByUserId(Work work);

    //用userId查询本月work
    @Select("select * from tb_work where user_id=#{userId} and date like #{date}")
    @ResultMap("workResultMap")
    List<Work> selectWorkByUserId(Work work);
}
