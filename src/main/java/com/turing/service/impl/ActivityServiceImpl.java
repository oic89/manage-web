package com.turing.service.impl;

import com.turing.mapper.ActivityMapper;
import com.turing.mapper.NoticeMapper;
import com.turing.mapper.UserMapper;
import com.turing.pojo.Activity;
import com.turing.pojo.Notice;
import com.turing.pojo.PageBean;
import com.turing.pojo.User;
import com.turing.service.ActivityService;
import com.turing.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.text;

public class ActivityServiceImpl implements ActivityService {
    //创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //分页条件查询（当前页码，每页展示条数）
    @Override
    public PageBean<Activity> selectByPageAndCondition(int currentPage, int pageSize, Activity activity) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取ActivityMapper
        ActivityMapper mapper = sqlSession.getMapper(ActivityMapper.class);
        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条数
        int size = pageSize;
        //处理模糊表达式
        String activityName = activity.getActivityName();
        if (activityName != null && !activityName.isEmpty()) {
            activity.setActivityName("%" + activityName + "%");
        }
        String date = activity.getDate();
        if (date != null && !date.isEmpty()) {
            activity.setDate("%" + date + "%");
        }
        //调用mapper
        //更新状态
        for (Activity a : mapper.selectAll()) {
            if ("待办".equals(a.getState())){
                LocalDate localDate = LocalDate.parse(a.getDate());
                LocalDate now = LocalDate.now();
                if (localDate.isBefore(now)){
                    a.setState("结束");
                    mapper.updateActivity(a);
                }
            }
            if ("结束".equals(a.getState())){
                LocalDate localDate = LocalDate.parse(a.getDate());
                LocalDate now = LocalDate.now();
                if (localDate.isAfter(now)){
                    a.setState("待办");
                    mapper.updateActivity(a);
                }
            }
        }
        //提交事务
        sqlSession.commit();
        List<Activity> rows = mapper.selectByPageAndCondition(begin, size, activity);
        if (rows != null) {
            int n = rows.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    LocalDate localDate1 = LocalDate.parse(rows.get(j).getDate());
                    LocalDate localDate2 = LocalDate.parse(rows.get(j + 1).getDate());
                    if (localDate1.isBefore(localDate2)) {
                        Activity temp = rows.get(j);
                        rows.set(j, rows.get(j + 1));
                        rows.set(j + 1, temp);
                    }
                }
            }
        }
        //查询总记录数
        int totalCount = mapper.selectTotalCountAndCondition(activity);
        PageBean<Activity> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        // 释放资源
        sqlSession.close();
        return pageBean;
    }

    //修改活动信息
    @Override
    public void updateActivity(Activity activity) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取ActivityMapper
        ActivityMapper mapper = sqlSession.getMapper(ActivityMapper.class);
        //调用mapper
        mapper.updateActivity(activity);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //添加活动
    @Override
    public void addActivity(Activity activity) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取ActivityMapper
        ActivityMapper mapper = sqlSession.getMapper(ActivityMapper.class);
        //调用mapper
        mapper.insertActivity(activity);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //删除活动
    @Override
    public void deleteActivity(Activity activity) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取ActivityMapper
        ActivityMapper mapper = sqlSession.getMapper(ActivityMapper.class);
        //调用mapper
        mapper.deleteActivityDataById(activity);
        mapper.deleteActivityById(activity);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //查看活动报名
    @Override
    public List<Activity> selectActivityData(Activity activity) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取ActivityMapper
        ActivityMapper mapper = sqlSession.getMapper(ActivityMapper.class);
        //调用mapper
        String userName = activity.getUserName();
        if (userName!=null && !userName.isEmpty()){
            activity.setUserName("%"+userName+"%");
        }
        List<Activity> activities = mapper.selectActivityDataById(activity);
        // 释放资源
        sqlSession.close();
        return activities;
    }

    //修改成绩
    @Override
    public void updateScore(Activity activity) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取ActivityMapper
        ActivityMapper mapper = sqlSession.getMapper(ActivityMapper.class);
        //调用mapper
        mapper.updateScore(activity);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //加入活动
    @Override
    public String addActivityData(Activity activity) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取ActivityMapper
        ActivityMapper activityMapper = sqlSession.getMapper(ActivityMapper.class);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        //调用mapper
        //判断是否已经加入活动
        Activity activity1 = activityMapper.selectActivityDataByActivityIdAndUserId(activity);
        if (activity1 != null){
            // 释放资源
            sqlSession.close();
            return "fail";
        }
        User user=new User();
        user.setId(activity.getUserId());
        User user1 = userMapper.selectUserById(user);
        activity.setFace(user1.getFace());
        activity.setAccount(user1.getAccount());
        activity.setUserName(user1.getName());
        activity.setScore(0);
        activityMapper.insertActivityData(activity);
        activityMapper.updateNumberByActivityId2(activity);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
        return "success";
    }

    //查找我的活动
    @Override
    public List<Activity> selectMyActivityData(Activity activity) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取ActivityMapper
        ActivityMapper mapper = sqlSession.getMapper(ActivityMapper.class);
        //调用mapper
        List<Activity> activities = mapper.selectMyActivityDataById(activity);
        List<Activity> rows=new ArrayList<>();
        //封装activities
        for (Activity a : activities) {
            Activity activity1 = mapper.selectActivityByActivityId(a);
            activity1.setScore(a.getScore());
            rows.add(activity1);
        }
        //排序
        int n = rows.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                LocalDate localDate1 = LocalDate.parse(rows.get(j).getDate());
                LocalDate localDate2 = LocalDate.parse(rows.get(j + 1).getDate());
                if (localDate1.isBefore(localDate2)) {
                    Activity temp = rows.get(j);
                    rows.set(j, rows.get(j + 1));
                    rows.set(j + 1, temp);
                }
            }
        }
        // 释放资源
        sqlSession.close();
        return rows;
    }

    //退出活动
    @Override
    public void deleteActivityData(Activity activity) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取ActivityMapper
        ActivityMapper mapper = sqlSession.getMapper(ActivityMapper.class);
        //调用mapper
        mapper.deleteActivityDataByUserIdAndActivityId(activity);
        mapper.updateNumberByActivityId(activity);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }
}
