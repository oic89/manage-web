package com.turing.service.impl;

import com.turing.mapper.*;
import com.turing.pojo.*;
import com.turing.service.LeaveService;
import com.turing.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class LeaveServiceImpl implements LeaveService {
    //创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //分页条件查询（当前页码，每页展示条数）
    @Override
    public PageBean<Leave> selectByPageAndCondition(int currentPage, int pageSize, Leave leave) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
        //计算开始索引
        int begin=(currentPage-1)*pageSize;
        //计算查询条数
        int size=pageSize;
        //处理模糊表达式
        String name = leave.getName();
        if (name!= null && !name.isEmpty()) {
            leave.setName("%" + name + "%");
        }
        String reason = leave.getReason();
        if (reason!= null && !reason.isEmpty()) {
            leave.setReason("%" + reason + "%");
        }
        String startDate = leave.getStartDate();
        if (startDate!= null && !startDate.isEmpty()) {
            leave.setStartDate("%" + startDate + "%");
            leave.setLastDate("%" + startDate + "%");
        }
        //调用mapper
        List<Leave> rows = mapper.selectByPageAndCondition(begin, size, leave);
        if (rows != null) {
            int n = rows.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    LocalDate localDate1 = LocalDate.parse(rows.get(j).getStartDate());
                    LocalDate localDate2 = LocalDate.parse(rows.get(j + 1).getStartDate());
                    if (localDate1.isBefore(localDate2)) {
                        Leave temp = rows.get(j);
                        rows.set(j, rows.get(j + 1));
                        rows.set(j + 1, temp);
                    }
                }
            }
        }
        //查询总记录数
        int totalCount = mapper.selectTotalCountAndCondition(leave);
        PageBean<Leave> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        // 释放资源
        sqlSession.close();
        return pageBean;
    }

    //分页条件查询登录用户（当前页码，每页展示条数）
    @Override
    public PageBean<Leave> selectByPageAndCondition1(int currentPage, int pageSize, Leave leave) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
        //计算开始索引
        int begin=(currentPage-1)*pageSize;
        //计算查询条数
        int size=pageSize;
        String reason = leave.getReason();
        if (reason!= null && !reason.isEmpty()) {
            leave.setReason("%" + reason + "%");
        }
        String startDate = leave.getStartDate();
        if (startDate!= null && !startDate.isEmpty()) {
            leave.setStartDate("%" + startDate + "%");
            leave.setLastDate("%" + startDate + "%");
        }
        //调用mapper
        List<Leave> rows = mapper.selectByPageAndCondition1(begin, size, leave);
        if (rows != null) {
            int n = rows.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    LocalDate localDate1 = LocalDate.parse(rows.get(j).getStartDate());
                    LocalDate localDate2 = LocalDate.parse(rows.get(j + 1).getStartDate());
                    if (localDate1.isBefore(localDate2)) {
                        Leave temp = rows.get(j);
                        rows.set(j, rows.get(j + 1));
                        rows.set(j + 1, temp);
                    }
                }
            }
        }
        //查询总记录数
        int totalCount = mapper.selectTotalCountAndCondition1(leave);
        PageBean<Leave> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        // 释放资源
        sqlSession.close();
        return pageBean;
    }


    //修改请假信息
    @Override
    public void updateLeave(Leave leave) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
        //调用mapper
        mapper.updateLeave(leave);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //删除请假信息
    @Override
    public void deleteLeave(Leave leave) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
        //调用mapper
        mapper.deleteLeave(leave);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //查看请假/离职申请
    @Override
    public List<Leave> selectApplyLeave(Leave leave) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
        //调用mapper
        List<Leave> leaves = mapper.selectApplyLeave(leave);
        if (leaves!=null){
            int n = leaves.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    LocalDate localDate1 = LocalDate.parse(leaves.get(j).getStartDate());
                    LocalDate localDate2 = LocalDate.parse(leaves.get(j + 1).getStartDate());
                    if (localDate1.isBefore(localDate2)) {
                        Leave temp = leaves.get(j);
                        leaves.set(j, leaves.get(j + 1));
                        leaves.set(j + 1, temp);
                    }
                }
            }
        }
        // 释放资源
        sqlSession.close();
        return leaves;
    }

    //查看登录用户的请假/离职申请
    @Override
    public List<Leave> selectApplyLeaveByUserId(Leave leave) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
        //调用mapper
        List<Leave> leaves = mapper.selectApplyLeaveByUserId(leave);
        if (leaves!=null){
            int n = leaves.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    LocalDate localDate1 = LocalDate.parse(leaves.get(j).getStartDate());
                    LocalDate localDate2 = LocalDate.parse(leaves.get(j + 1).getStartDate());
                    if (localDate1.isBefore(localDate2)) {
                        Leave temp = leaves.get(j);
                        leaves.set(j, leaves.get(j + 1));
                        leaves.set(j + 1, temp);
                    }
                }
            }
        }
        // 释放资源
        sqlSession.close();
        return leaves;
    }

    //同意请假
    @Override
    public void updateApplyYes(Leave leave) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
        //调用mapper
        leave.setState("请假同意");
        mapper.updateLeave(leave);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //拒绝请假
    @Override
    public void updateApplyNo(Leave leave) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        LeaveMapper mapper = sqlSession.getMapper(LeaveMapper.class);
        //调用mapper
        leave.setState("请假拒绝");
        mapper.updateLeave(leave);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //离职
    @Override
    public void depart(Leave leave) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取Mapper
        LeaveMapper leaveMapper = sqlSession.getMapper(LeaveMapper.class);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        ActivityMapper activityMapper=sqlSession.getMapper(ActivityMapper.class);
        WorkMapper workMapper=sqlSession.getMapper(WorkMapper.class);
        SalaryMapper salaryMapper=sqlSession.getMapper(SalaryMapper.class);
        //调用mapper
        //删除work
        Work work=new Work();
        work.setUserId(leave.getUserId());
        workMapper.deleteWorkByUserId(work);
        //删除activityData
        Activity activity=new Activity();
        activity.setUserId(leave.getUserId());
        List<Activity> activityDatas = activityMapper.selectActivityDataByUserId(activity);
        if (!activityDatas.isEmpty()){
            //更新number
            for (Activity activityData : activityDatas) {
                activityMapper.updateNumberByActivityId(activityData);
            }
        }
        activityMapper.deleteActivityDataByUserId(activity);
        //删除salary
        Salary salary = new Salary();
        salary.setUserId(leave.getUserId());
        salaryMapper.deleteSalaryByUserId(salary);
        //改user状态
        User user=new User();
        user.setId(leave.getUserId());
        user.setIsJob("离职");
        userMapper.updateState(user);
        //删除leave
        leaveMapper.deleteByUserId(leave);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //添加请假/离职申请
    @Override
    public void addLeave(Leave leave) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取Mapper
        LeaveMapper leaveMapper = sqlSession.getMapper(LeaveMapper.class);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        //调用mapper
        User user = new User();
        user.setId(leave.getUserId());
        User user1 = userMapper.selectUserById(user);
        //封装leave
        if (leave.getStartDate()==null|| Objects.equals(leave.getStartDate(), "")){
            leave.setState("离职申请");
            //获取时间
            LocalDate date=LocalDate.now();
            leave.setStartDate(String.valueOf(date));
        }else {
            leave.setState("请假申请");
        }
        leave.setName(user1.getName());
        leave.setAccount(user1.getAccount());
        leave.setSex(user1.getSex());
        leave.setFace(user1.getFace());
        leaveMapper.insertLeave(leave);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }
}
