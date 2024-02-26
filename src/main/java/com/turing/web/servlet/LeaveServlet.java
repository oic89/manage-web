package com.turing.web.servlet;



import com.alibaba.fastjson.JSON;
import com.turing.pojo.Leave;
import com.turing.pojo.PageBean;
import com.turing.service.LeaveService;
import com.turing.service.impl.LeaveServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/leave/*")
public class LeaveServlet extends BaseServlet {
    private final LeaveService leaveService=new LeaveServiceImpl();

    //分页条件查询在职用户请假信息
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage_ = request.getParameter("currentPage");
        String pageSize_ = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(currentPage_);
        int pageSize=Integer.parseInt(pageSize_);
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Leave对象
        Leave leave= JSON.parseObject(params, Leave.class);
        //调用Service
        PageBean<Leave> pageBean = leaveService.selectByPageAndCondition(currentPage,pageSize,leave);
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //分页条件查询登录用户请假信息
    public void selectByPageAndCondition1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage_ = request.getParameter("currentPage");
        String pageSize_ = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(currentPage_);
        int pageSize=Integer.parseInt(pageSize_);
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Leave对象
        Leave leave= JSON.parseObject(params, Leave.class);
        //获取session
        HttpSession session=request.getSession();
        int id = (int) session.getAttribute("userId");
        leave.setUserId(id);
        //调用Service
        PageBean<Leave> pageBean = leaveService.selectByPageAndCondition1(currentPage,pageSize,leave);
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //修改请假信息
    public void updateLeave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Leave对象
        Leave leave= JSON.parseObject(params, Leave.class);
        //调用Service
        leaveService.updateLeave(leave);
        //写数据
        response.getWriter().write("success");
    }

    //删除请假信息
    public void deleteLeave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Leave对象
        Leave leave= JSON.parseObject(params, Leave.class);
        //调用Service
        leaveService.deleteLeave(leave);
        //写数据
        response.getWriter().write("success");
    }

    //查看请假/离职信息
    public void selectApplyLeave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        request.setCharacterEncoding("utf-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //封装leave
        Leave leave = new Leave();
        leave.setState(params);
        //调用Service
        List<Leave> leaves = leaveService.selectApplyLeave(leave);
        //转为JSON
        String jsonString = JSON.toJSONString(leaves);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //查看登录用户请假/离职信息
    public void selectApplyLeaveByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        request.setCharacterEncoding("utf-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //封装leave
        Leave leave = new Leave();
        leave.setState(params);
        //获取session
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("userId");
        leave.setUserId(id);
        //调用Service
        List<Leave> leaves = leaveService.selectApplyLeaveByUserId(leave);
        //转为JSON
        String jsonString = JSON.toJSONString(leaves);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //同意请假
    public void updateApplyYes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Leave对象
        Leave leave= JSON.parseObject(params, Leave.class);
        //调用Service
        leaveService.updateApplyYes(leave);
        //写数据
        response.getWriter().write("success");
    }

    //拒绝请假
    public void updateApplyNo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Leave对象
        Leave leave= JSON.parseObject(params, Leave.class);
        //调用Service
        leaveService.updateApplyNo(leave);
        //写数据
        response.getWriter().write("success");
    }

    //离职
    public void depart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Leave对象
        Leave leave= JSON.parseObject(params, Leave.class);
        //调用Service
        leaveService.depart(leave);
        //写数据
        response.getWriter().write("success");
    }

    //提交请假/离职申请
    public void applyLeave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Leave对象
        Leave leave= JSON.parseObject(params, Leave.class);
        //获取session
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("userId");
        leave.setUserId(id);
        //调用Service
        leaveService.addLeave(leave);
        //写数据
        response.getWriter().write("success");
    }
}
