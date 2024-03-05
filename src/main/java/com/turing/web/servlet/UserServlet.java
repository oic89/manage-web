package com.turing.web.servlet;

import com.alibaba.fastjson.JSON;
import com.turing.pojo.PageBean;
import com.turing.pojo.User;
import com.turing.service.UserService;
import com.turing.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为User对象
        User user = JSON.parseObject(params, User.class);
        //调用Service
        User u = userService.login(user);
        //将id存入Session
        if (u != null && "在职".equals(u.getIsJob())) {
            HttpSession session = request.getSession();
            if (session.getAttribute("adminId") != null || session.getAttribute("userId") != null) {
                //浏览器已登录
                response.getWriter().write("isFull");
                return;
            }
            session.setAttribute("userId", u.getId());
        }
        //转为JSON
        String jsonString = JSON.toJSONString(u);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //返回数据
    public void loadData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("userId");
        //调用Service
        User user = new User();
        user.setId(id);
        User u = userService.loadData(user);
        //转为JSON
        String jsonString = JSON.toJSONString(u);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //注册
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为User对象
        User user = JSON.parseObject(params, User.class);
        //调用Service
        String result = userService.addUser(user);
        //返回结果
        response.getWriter().write(result);
    }

    //删除用户
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为User对象
        User user = JSON.parseObject(params, User.class);
        //调用Service
        userService.deleteUser(user);
        response.getWriter().write("success");
    }

    //修改信息
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为User对象
        User user = JSON.parseObject(params, User.class);
        //获取session
        //获取Session
        HttpSession session = request.getSession();
        if (session.getAttribute("userId")!=null){
            user.setId((Integer) session.getAttribute("userId"));
        }
        //调用Service
        String result = userService.updateUser(user);
        response.getWriter().write(result);
    }

    //登出
    public void loadOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session
        HttpSession session = request.getSession();
        //删除session
        session.invalidate();
        //返回结果
        response.getWriter().write("success");
    }

    //分页条件查询在职用户
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取页码
        String currentPage_ = request.getParameter("currentPage");
        String pageSize_ = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(currentPage_);
        int pageSize = Integer.parseInt(pageSize_);
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为User对象
        User user = JSON.parseObject(params, User.class);
        //调用Service
        PageBean<User> pageBean = userService.selectByPageAndCondition(currentPage, pageSize, user);
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //分页条件查询离职用户
    public void selectByPageAndCondition2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取页码
        String currentPage_ = request.getParameter("currentPage");
        String pageSize_ = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(currentPage_);
        int pageSize = Integer.parseInt(pageSize_);
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为User对象
        User user = JSON.parseObject(params, User.class);
        //调用Service
        PageBean<User> pageBean = userService.selectByPageAndCondition2(currentPage, pageSize, user);
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //复职
    public void reWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为User对象
        User user = JSON.parseObject(params, User.class);
        //调用Service
        userService.reWork(user);
        //写数据
        response.getWriter().write("success");
    }

    //修改密码
    public void modifyPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为User对象
        User user = JSON.parseObject(params, User.class);
        //获取Session
        int id = getSessionId(request);
        user.setId(id);
        //调用Service
        String result = userService.modifyPassword(user);
        if("success".equals(result)){
            HttpSession session = request.getSession();
            //删除session
            session.invalidate();
        }
        //写数据
        response.getWriter().write(result);
    }

    //获取session的userId
    private int getSessionId(HttpServletRequest request) {
        //获取Session
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("userId");
        return id;
    }

}
