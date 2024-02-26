package com.turing.service;

import com.turing.pojo.Admin;
import com.turing.pojo.PageBean;
import com.turing.pojo.User;

import java.util.List;

public interface UserService {

    //登录
    User login(User user);

    //注册
    String addUser(User user);

    //删除
    void deleteUser(User user);

    //修改用户信息
    String updateUser(User user);

    //分页条件查询用户信息
    PageBean<User> selectByPageAndCondition(int currentPage,int pageSize,User user);

    //分页条件查询离职用户信息
    PageBean<User> selectByPageAndCondition2(int currentPage,int pageSize,User user);

    //复职
    void reWork(User user);

    //返回登录员工信息
    User loadData(User user);

    //修改密码
    String modifyPassword(User user);

    //用id找用户
    User selectUserById(User user);
}
