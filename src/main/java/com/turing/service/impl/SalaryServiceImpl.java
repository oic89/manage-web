package com.turing.service.impl;

import com.turing.mapper.LeaveMapper;
import com.turing.mapper.SalaryMapper;
import com.turing.mapper.UserMapper;
import com.turing.mapper.WorkMapper;
import com.turing.pojo.*;
import com.turing.service.SalaryService;
import com.turing.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalaryServiceImpl implements SalaryService {
    //创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //分页条件查询薪资信息
    @Override
    public PageBean<Salary> selectByPageAndCondition(int currentPage, int pageSize, Salary salary) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取SalaryMapper
        SalaryMapper mapper = sqlSession.getMapper(SalaryMapper.class);
        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条数
        int size = pageSize;
        //调用mapper
        //当前页数据
        List<Salary> rows = mapper.selectByPageAndCondition(begin, size, salary);
        //按新到久排序
        Collections.reverse(rows);
        //查询总记录数
        int totalCount = mapper.selectTotalCountAndCondition(salary);
        PageBean<Salary> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        // 释放资源
        sqlSession.close();
        return pageBean;
    }

    //薪资统计
    @Override
    public void salaryStat() {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取mapper
        SalaryMapper salaryMapper = sqlSession.getMapper(SalaryMapper.class);
        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);
        LeaveMapper leaveMapper = sqlSession.getMapper(LeaveMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //获取上个月月份
        LocalDate lastMonth = LocalDate.from(LocalDate.now().minusMonths(1));
        LocalDate startDay = lastMonth.withDayOfMonth(1);
        LocalDate lastDay = lastMonth.with(TemporalAdjusters.lastDayOfMonth());
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = formatter1.format(lastMonth);
        String date_ = "%" + date + "%";
        //调用mapper
        //获取在职userId
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            //获取请假天数
            int leaveTime = -1;
            Leave leave = new Leave();
            leave.setUserId(user.getId());
            List<Leave> leaves = leaveMapper.selectLeaveByUserId(leave);
            Map<LocalDate, Integer> days = new HashMap<>();
            for (Leave leaf : leaves) {
                String startDate_ = leaf.getStartDate();
                String lastDate_ = leaf.getLastDate();
                LocalDate startDate = LocalDate.parse(startDate_, formatter2);
                LocalDate lastDate = LocalDate.parse(lastDate_, formatter2);
                if ((startDate.isBefore(startDay) && lastDate.isBefore(startDay)) || (startDate.isAfter(lastDay) && lastDate.isAfter(lastDay))) {
                    //不在上一个月
                    continue;
                }
                if (startDate.isBefore(startDay) && lastDate.isAfter(lastDay)) {
                    leaveTime = lastDay.getDayOfMonth();
                    //上一个月全部请假
                    break;
                }
                if (startDate.isBefore(startDay) && lastDate.isBefore(lastDay)) {
                    LocalDate start = startDay;
                    //请假第一天在不在上个月
                    while (!start.isAfter(lastDate)) {
                        days.put(start, 1);
                        start = start.plusDays(1);
                    }
                    continue;
                }
                if (startDate.isAfter(startDay) && lastDate.isAfter(lastDay)) {
                    LocalDate start = startDate;
                    //请假最后一天在不在上个月
                    while (!start.isAfter(lastDay)) {
                        days.put(start, 1);
                        start = start.plusDays(1);
                    }
                    continue;
                }
                //请假全部在上个月
                LocalDate start = startDate;
                while (!start.isAfter(lastDate)) {
                    days.put(start, 1);
                    start = start.plusDays(1);
                }
            }
            if (leaveTime == -1) {
                leaveTime = days.size();
            }
            //获取work
            Work work = new Work();
            work.setUserId(user.getId());
            work.setDate(date_);
            int lateTime = 0;
            int leaveEarlyTime = 0;
            int absenceTime = 0;
            List<Work> works = workMapper.selectWorkByUserId(work);
            for (Work work1 : works) {
                String type = work1.getType();
                if ("迟到".equals(type)) {
                    lateTime++;
                } else if ("早退".equals(type)) {
                    leaveEarlyTime++;
                } else if ("缺勤".equals(type)) {
                    absenceTime++;
                }
            }
            //计算工资
            //全勤奖500，请假一天不算，请假两天50%，三天没有全勤奖
            //迟到、早退两次算请假一天，迟到早退一次扣10
            //缺勤一次扣50，缺勤没有全勤奖
            int salary = user.getBasicSalary() - (leaveEarlyTime + lateTime) * 10 - absenceTime * 50;
            if (leaveTime + (leaveEarlyTime + lateTime) / 2 <= 1 && absenceTime == 0) {
                salary += 500;
            } else if (leaveTime + (leaveEarlyTime + lateTime) / 2 == 2 && absenceTime == 0) {
                salary += 250;
            }
            //封装salary
            Salary s = new Salary();
            s.setUserId(user.getId());
            s.setBasicSalary(user.getBasicSalary());
            s.setSalary(salary);
            s.setLateTime(lateTime);
            s.setLeaveEarlyTime(leaveEarlyTime);
            s.setAbsenceTime(absenceTime);
            s.setLeaveTime(leaveTime);
            s.setDate(date);
            //添加salary
            salaryMapper.insertSalary(s);
        }
        //提交事务
        sqlSession.commit();
        System.out.println("salaryStat2");
        //释放资源
        sqlSession.close();
    }
}
