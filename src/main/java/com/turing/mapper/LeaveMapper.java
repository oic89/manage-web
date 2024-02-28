package com.turing.mapper;

import com.turing.pojo.Leave;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface LeaveMapper {
    //分页条件查询在职用户请假信息
    List<Leave> selectByPageAndCondition(Leave leave);

    //条件查询在职用户请假信息总页数
    int selectTotalCountAndCondition(Leave leave);

    //分页条件查询登录用户请假信息
    List<Leave> selectByPageAndCondition1(Leave leave);

    //条件查询登录用户请假信息总页数
    int selectTotalCountAndCondition1(Leave leave);

    //修改请假信息
    @Update("update tb_leave set reason=#{reason},start_date=#{startDate},last_date=#{lastDate},state=#{state} where id=#{id}")
    @ResultMap("leaveResultMap")
    void updateLeave(Leave leave);

    //删除请假信息
    @Delete("delete from tb_leave where id=#{id}")
    @ResultMap("leaveResultMap")
    void deleteLeave(Leave leave);

    //查看请假/离职申请
    @Select("select * from tb_leave where state=#{state}")
    @ResultMap("leaveResultMap")
    List<Leave> selectApplyLeave(Leave leave);

    //用user_id删除leave
    @Delete("delete from tb_leave where user_id=#{userId}")
    @ResultMap("leaveResultMap")
    void deleteByUserId(Leave leave);

    //用userId修改leave
    @Update("update tb_leave set name=#{name},account=#{account},sex=#{sex},face=#{face} where user_id=#{userId}")
    @ResultMap("leaveResultMap")
    void updateLeaveByUserId(Leave leave);

    //添加信息
    @Insert("insert into tb_leave (id,user_id,name,account,sex,reason,face,start_date,last_date,state) values (null,#{userId},#{name},#{account},#{sex},#{reason},#{face},#{startDate},#{lastDate},#{state})")
    @ResultMap("leaveResultMap")
    void insertLeave(Leave leave);

    //查看登录用户的请假申请
    List<Leave> selectApplyLeaveByUserId(Leave leave);

    //用userId查询本月请假同意
    @Select("select * from tb_leave where state='请假同意' and user_id=#{userId}")
    @ResultMap("leaveResultMap")
    List<Leave> selectLeaveByUserId(Leave leave);
}
