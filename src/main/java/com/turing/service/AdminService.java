package com.turing.service;

import com.turing.pojo.Admin;
import com.turing.pojo.User;

public interface AdminService {

    //登录
    User login(Admin admin);

    //注册
    String addAdmin(Admin admin);

    //返回登录管理员信息
    Admin loadData(int id);

    //注销
    String logOffAdmin(Admin admin);

    //修改信息
    String modifyInformation(Admin admin);

    //修改密码
    String modifyPassword(Admin admin);
}
