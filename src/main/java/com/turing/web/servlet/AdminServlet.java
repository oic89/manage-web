package com.turing.web.servlet;

import com.alibaba.fastjson.JSON;
import com.turing.pojo.Admin;
import com.turing.pojo.User;
import com.turing.service.AdminService;
import com.turing.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/admin/*")
public class AdminServlet extends BaseServlet{
    private final AdminService adminService=new AdminServiceImpl();

    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为admin对象
        Admin admin = JSON.parseObject(params, Admin.class);
        //调用Service
        User user=adminService.login(admin);
        //将id存入Session
        if(user!=null){
            HttpSession session=request.getSession();
            if (session.getAttribute("adminId")!=null||session.getAttribute("userId")!=null){
                response.getWriter().write("isFull");
                return;
            }
            session.setAttribute("adminId",user.getId());
        }
        //转为JSON
        String jsonString = JSON.toJSONString(user);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //注册
    public void addAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为admin对象
        Admin admin = JSON.parseObject(params, Admin.class);
        //调用Service
        String result=adminService.addAdmin(admin);
        //返回结果
        response.getWriter().write(result);
    }

    //返回数据
    public void loadData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session
        HttpSession session=request.getSession();
        int id = (int) session.getAttribute("adminId");
        //调用Service
        Admin admin = adminService.loadData(id);
        //转为JSON
        String jsonString = JSON.toJSONString(admin);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //登出
    public void loadOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session
        HttpSession session=request.getSession();
        //删除session
        session.invalidate();
        //返回结果
        response.getWriter().write("success");
    }

    //注销
    public void logOff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        // 分割字符串{"xx":"xxx"}
        String[] parts = params.split("\":\"");
        if (parts.length > 1) {
            String part=parts[1];
            String password=part.substring(0,part.length()-2);
            //获取Session
            HttpSession session=request.getSession();
            int id = (int) session.getAttribute("adminId");
            //封装admin
            Admin admin=new Admin();
            admin.setPassword(password);
            admin.setId(id);
            //调用Service
            String result=adminService.logOffAdmin(admin);
            //返回结果
            response.getWriter().write(result);
            return;
        }
        response.getWriter().write("fail");
    }

    //修改信息
    public void modifyInformation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为admin对象
        Admin admin = JSON.parseObject(params, Admin.class);
        //获取Session
        HttpSession session=request.getSession();
        int id = (int) session.getAttribute("adminId");
        admin.setId(id);
        //调用Service
        String result=adminService.modifyInformation(admin);
        response.getWriter().write(result);
    }

    //修改密码
    public void modifyPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为admin对象
        Admin admin = JSON.parseObject(params, Admin.class);
        //获取Session
        HttpSession session=request.getSession();
        int id = (int) session.getAttribute("adminId");
        admin.setId(id);
        //调用Service
        String result=adminService.modifyPassword(admin);
        response.getWriter().write(result);
    }
}
