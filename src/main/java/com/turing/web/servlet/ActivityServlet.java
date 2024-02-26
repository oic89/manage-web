package com.turing.web.servlet;

import com.alibaba.fastjson.JSON;
import com.turing.pojo.Activity;
import com.turing.pojo.Notice;
import com.turing.pojo.PageBean;
import com.turing.service.ActivityService;
import com.turing.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/activity/*")
public class ActivityServlet extends BaseServlet{
    private final ActivityService activityService=new ActivityServiceImpl();

    //分页条件查询活动
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage_ = request.getParameter("currentPage");
        String pageSize_ = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(currentPage_);
        int pageSize=Integer.parseInt(pageSize_);
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Activity对象
        Activity activity= JSON.parseObject(params, Activity.class);
        //调用Service
        PageBean<Activity> pageBean = activityService.selectByPageAndCondition(currentPage,pageSize,activity);
        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //修改活动信息
    public void updateActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Activity对象
        Activity activity= JSON.parseObject(params, Activity.class);
        //调用Service
        activityService.updateActivity(activity);
        //写数据
        response.getWriter().write("success");
    }

    //添加活动
    public void addActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Activity对象
        Activity activity= JSON.parseObject(params, Activity.class);
        activity.setState("待办");
        activity.setNumber(0);
        //调用Service
        activityService.addActivity(activity);
        //写数据
        response.getWriter().write("success");
    }

    //删除活动
    public void deleteActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Activity对象
        Activity activity= JSON.parseObject(params, Activity.class);
        //调用Service
        activityService.deleteActivity(activity);
        //写数据
        response.getWriter().write("success");
    }

    //查看报名
    public void selectActivityData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Activity对象
        Activity activity= JSON.parseObject(params, Activity.class);
        //调用Service
        List<Activity> rows = activityService.selectActivityData(activity);
        //转为JSON
        String jsonString = JSON.toJSONString(rows);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //修改成绩
    public void updateScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Activity对象
        Activity activity= JSON.parseObject(params, Activity.class);
        //调用Service
        activityService.updateScore(activity);
        response.getWriter().write("success");
    }

    //加入活动
    public void addActivityData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Activity对象
        Activity activity= JSON.parseObject(params, Activity.class);
        //获取session
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("userId");
        activity.setUserId(id);
        //调用Service
        String result = activityService.addActivityData(activity);
        response.getWriter().write(result);
    }

    //查找我的活动
    public void selectMyActivityData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Activity activity=new Activity();
        //获取session
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("userId");
        activity.setUserId(id);
        //调用Service
        List<Activity> activities = activityService.selectMyActivityData(activity);
        //转为JSON
        String jsonString = JSON.toJSONString(activities);
        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //退出活动
    public void deleteActivityData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收json字符串数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Activity对象
        Activity activity= JSON.parseObject(params, Activity.class);
        //获取session
        HttpSession session=request.getSession();
        int id= (int) session.getAttribute("userId");
        activity.setUserId(id);
        //调用Service
        activityService.deleteActivityData(activity);
        response.getWriter().write("success");
    }

}
