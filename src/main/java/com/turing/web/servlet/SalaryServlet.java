package com.turing.web.servlet;

import com.alibaba.fastjson.JSON;
import com.turing.pojo.PageBean;
import com.turing.pojo.Salary;
import com.turing.pojo.User;
import com.turing.service.SalaryService;
import com.turing.service.impl.SalaryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/salary/*")
public class SalaryServlet extends BaseServlet{
    SalaryService salaryService=new SalaryServiceImpl();

    //分页条件查询薪资信息
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage_ = request.getParameter("currentPage");
        String pageSize_ = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(currentPage_);
        int pageSize=Integer.parseInt(pageSize_);
        //封装salary对象
        Salary salary = new Salary();
        //获取Session
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("userId");
        salary.setUserId(id);
        //调用Service
        PageBean<Salary> pageBean = salaryService.selectByPageAndCondition(currentPage,pageSize,salary);
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
