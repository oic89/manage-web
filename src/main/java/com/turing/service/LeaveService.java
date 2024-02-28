package com.turing.service;

import com.turing.pojo.Leave;
import com.turing.pojo.PageBean;

import java.util.List;


public interface LeaveService {
    //分页条件查询请假信息
    PageBean<Leave> selectByPageAndCondition(int currentPage, int pageSize, Leave leave);

    //分页条件查询登录用户请假信息
    PageBean<Leave> selectByPageAndCondition1(int currentPage, int pageSize, Leave leave);

    //修改请假信息
    void updateLeave(Leave leave);

    //删除请假信息
    void deleteLeave(Leave leave);

    //查询请假/离职申请
    List<Leave> selectApplyLeave(Leave leave);

    //同意请假
    void updateApplyYes(Leave leave);

    //拒绝请假
    void updateApplyNo(Leave leave);

    //离职
    void depart(Leave leave);

    //添加请假/离职申请
    void addLeave(Leave leave);

    //查看登录用户的离职/请假申请
    List<Leave> selectApplyLeaveByUserId(Leave leave);
}
