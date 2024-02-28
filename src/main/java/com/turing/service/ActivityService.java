package com.turing.service;

import com.turing.pojo.Activity;
import com.turing.pojo.PageBean;

import java.util.List;

public interface ActivityService {
    //分页条件查询活动
    PageBean<Activity> selectByPageAndCondition(int currentPage, int pageSize, Activity activity);

    //修改活动信息
    void updateActivity(Activity activity);

    //添加活动
    void addActivity(Activity activity);

    //删除活动
    void deleteActivity(Activity activity);

    //查看活动报名
    List<Activity> selectActivityData(Activity activity);

    //修改成绩
    void updateScore(Activity activity);

    //加入活动
    String addActivityData(Activity activity);

    //查找我的活动
    List<Activity> selectMyActivityData(Activity activity);

    //退出活动
    void deleteActivityData(Activity activity);
}
