package com.turing.web.servlet;

import com.alibaba.fastjson.JSON;
import com.turing.pojo.Notice;
import com.turing.pojo.PageBean;
import com.turing.pojo.User;
import com.turing.service.NoticeService;
import com.turing.service.impl.NoticeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/notice/*")
public class NoticeServlet extends BaseServlet{
    private final NoticeService noticeService=new NoticeServiceImpl();

    //分页条件查询公告
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取页码
        String currentPage_ = request.getParameter("currentPage");
        String pageSize_ = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(currentPage_);
        int pageSize=Integer.parseInt(pageSize_);
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Notice对象
        Notice notice= JSON.parseObject(params, Notice.class);
        //调用Service
        PageBean<Notice> pageBean = noticeService.selectByPageAndCondition(currentPage,pageSize,notice);
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //修改公告信息
    public void updateNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Notice对象
        Notice notice= JSON.parseObject(params, Notice.class);
        //调用Service
        noticeService.updateNotice(notice);
        //写数据
        response.getWriter().write("success");
    }

    //新增公告信息
    public void addNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Notice对象
        Notice notice= JSON.parseObject(params, Notice.class);
        //调用Service
        noticeService.addNotice(notice);
        //写数据
        response.getWriter().write("success");
    }

    //删除公告信息
    public void deleteNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Notice对象
        Notice notice= JSON.parseObject(params, Notice.class);
        //调用Service
        noticeService.deleteNotice(notice);
        //写数据
        response.getWriter().write("success");
    }
}
