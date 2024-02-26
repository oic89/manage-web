package com.turing.service;

import com.turing.pojo.PageBean;
import com.turing.pojo.Work;


public interface WorkService {
    //分页条件查询用户出勤信息
    PageBean<Work> selectByPageAndCondition(int currentPage, int pageSize, Work work);

    //分页条件查询登录用户出勤信息
    PageBean<Work> selectByPageAndCondition1(int currentPage, int pageSize, Work work);

    //修改出勤信息
    void updateWork(Work work);

    //删除出勤信息
    void deleteWork(Work work);

    //添加出勤信息
    String addWork(Work work);
}
