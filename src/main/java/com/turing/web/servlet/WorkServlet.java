package com.turing.web.servlet;

import com.alibaba.fastjson.JSON;
import com.turing.pojo.PageBean;
import com.turing.pojo.Work;
import com.turing.service.WorkService;
import com.turing.service.impl.WorkServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/work/*")
public class WorkServlet extends BaseServlet{
    private final WorkService workService=new WorkServiceImpl();

    //分页条件查询出勤信息
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage_ = request.getParameter("currentPage");
        String pageSize_ = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(currentPage_);
        int pageSize=Integer.parseInt(pageSize_);
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Work对象
        Work work= JSON.parseObject(params, Work.class);
        //调用Service
        PageBean<Work> pageBean = workService.selectByPageAndCondition(currentPage,pageSize,work);
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //分页条件查询登录用户出勤信息
    public void selectByPageAndCondition1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage_ = request.getParameter("currentPage");
        String pageSize_ = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(currentPage_);
        int pageSize=Integer.parseInt(pageSize_);
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Work对象
        Work work= JSON.parseObject(params, Work.class);
        //获取session
        HttpSession session=request.getSession();
        int id = (int) session.getAttribute("userId");
        work.setUserId(id);
        //调用Service
        PageBean<Work> pageBean = workService.selectByPageAndCondition1(currentPage,pageSize,work);
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //修改出勤信息
    public void updateWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Work对象
        Work work= JSON.parseObject(params, Work.class);
        //调用Service
        workService.updateWork(work);
        //写数据
        response.getWriter().write("success");
    }

    //删除出勤信息
    public void deleteWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Work对象
        Work work= JSON.parseObject(params, Work.class);
        //调用Service
        workService.deleteWork(work);
        //写数据
        response.getWriter().write("success");
    }

    //添加出勤信息
    public void addWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Work对象
        Work work= JSON.parseObject(params, Work.class);
        //调用Service
        String result = workService.addWork(work);
        //写数据
        response.getWriter().write(result);
    }
}
