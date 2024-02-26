package com.turing.service;

import com.turing.pojo.PageBean;
import com.turing.pojo.Salary;
import com.turing.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalaryService {

    //分页条件查询薪资信息
    PageBean<Salary> selectByPageAndCondition(int currentPage, int pageSize, Salary salary);

    //薪资统计
    void salaryStat();
}
