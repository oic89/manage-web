package com.turing.service.impl;

import com.turing.mapper.*;
import com.turing.pojo.*;
import com.turing.service.UserService;
import com.turing.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    //创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //登录
    @Override
    public User login(User user) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用mapper
        User u = mapper.selectUserByAccountAndPassword(user);
        //释放资源
        sqlSession.close();
        return u;
    }

    //注册
    @Override
    public String addUser(User user) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用mapper
        User u = mapper.selectUserByAccount(user);
        if (u == null) {
            user.setIsJob("在职");
            //按性别设置头像
            if ("男".equals(user.getSex())) {
                user.setFace("img/male.png");
            } else {
                user.setFace("img/female.png");
            }
            mapper.insertUser(user);
            //提交事务
            sqlSession.commit();
            // 释放资源
            sqlSession.close();
            return "success";
        }
        // 释放资源
        sqlSession.close();
        return "fail";
    }

    //删除用户
    @Override
    public void deleteUser(User user) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        LeaveMapper leaveMapper = sqlSession.getMapper(LeaveMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        ActivityMapper activityMapper = sqlSession.getMapper(ActivityMapper.class);
        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);
        SalaryMapper salaryMapper = sqlSession.getMapper(SalaryMapper.class);
        //调用mapper
        //删除work
        Work work = new Work();
        work.setUserId(user.getId());
        workMapper.deleteWorkByUserId(work);
        //删除activityData
        Activity activity = new Activity();
        activity.setUserId(user.getId());
        List<Activity> activityDatas = activityMapper.selectActivityDataByUserId(activity);
        if (!activityDatas.isEmpty()) {
            //更新number
            for (Activity activityData : activityDatas) {
                activityMapper.updateNumberByActivityId(activityData);
            }
        }
        activityMapper.deleteActivityDataByUserId(activity);
        //删除leave
        Leave leave = new Leave();
        leave.setUserId(user.getId());
        leaveMapper.deleteByUserId(leave);
        //删除salary
        Salary salary = new Salary();
        salary.setUserId(user.getId());
        salaryMapper.deleteSalaryByUserId(salary);
        //删除user
        userMapper.deleteUser(user);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //修改用户信息
    @Override
    public String updateUser(User user) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用mapper
        User user1 = userMapper.selectUserByAccount(user);
        if (user1 != null && !Objects.equals(user1.getId(), user.getId())) {
            //账号已存在
            // 释放资源
            sqlSession.close();
            return "fail";
        }
        User user2 = userMapper.selectUserById(user);
        String urlData = user.getFace();
        if ("img/female.png".equals(urlData)) {
            //没换头像，为女
            if ("男".equals(user.getSex())) {
                //换性别了
                user2.setSex("男");
                user2.setFace("img/male.png");
            }
        } else if ("img/male.png".equals(urlData)) {
            if ("女".equals(user.getSex())) {
                user2.setSex("女");
                user2.setFace("img/female.png");
            }
        } else {
            user2.setFace("img/" + user.getId() + ".jpg");
        }
        if (user.getAccount() != null && !user.getAccount().isEmpty() && user.getBasicSalary() !=null) {
            //管理员修改
            user2.setAccount(user.getAccount());
            user2.setBasicSalary(user.getBasicSalary());
        }
        user2.setName(user.getName());
        user2.setSex(user.getSex());
        userMapper.updateUserById(user2);
        //获取ActivityMapper
        ActivityMapper activityMapper = sqlSession.getMapper(ActivityMapper.class);
        Activity activity = new Activity();
        activity.setUserId(user2.getId());
        activity.setUserName(user2.getName());
        activity.setFace(user2.getFace());
        activity.setAccount(user2.getAccount());
        activityMapper.updateActivityDataByUserId(activity);
        //获取LeaveMapper
        LeaveMapper leaveMapper = sqlSession.getMapper(LeaveMapper.class);
        Leave leave = new Leave();
        leave.setUserId(user2.getId());
        leave.setName(user2.getName());
        leave.setAccount(user2.getAccount());
        leave.setSex(user2.getSex());
        leave.setFace(user2.getFace());
        leaveMapper.updateLeaveByUserId(leave);
        //获取WorkMapper
        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);
        Work work = new Work();
        work.setName(user2.getName());
        work.setFace(user2.getFace());
        work.setSex(user2.getSex());
        work.setAccount(user2.getAccount());
        work.setUserId(user2.getId());
        workMapper.updateWorkByUserId(work);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
        return "success";
    }

    //修改密码
    @Override
    public String modifyPassword(User user) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用mapper
        User user1 = mapper.selectUserById(user);
        if (!Objects.equals(user1.getPassword(), user.getAccount())) {
            //密码错误
            // 释放资源
            sqlSession.close();
            return "fail";
        }
        user1.setPassword(user.getPassword());
        mapper.updateUserById(user1);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
        return "success";
    }

    //分页条件查询（当前页码，每页展示条数）
    @Override
    public PageBean<User> selectByPageAndCondition(int currentPage, int pageSize, User user) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条数
        int size = pageSize;
        //处理模糊表达式
        String account = user.getAccount();
        if (account != null && !account.isEmpty()) {
            user.setAccount("%" + account + "%");
        }
        String name = user.getName();
        if (name != null && !name.isEmpty()) {
            user.setName("%" + name + "%");
        }
        //调用mapper
        //当前页数据
        List<User> rows = mapper.selectByPageAndCondition(begin, size, user);
        //查询总记录数
        int totalCount = mapper.selectTotalCountAndCondition(user);
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        // 释放资源
        sqlSession.close();
        return pageBean;
    }

    //分页条件查询离职（当前页码，每页展示条数）
    @Override
    public PageBean<User> selectByPageAndCondition2(int currentPage, int pageSize, User user) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条数
        int size = pageSize;
        //处理模糊表达式
        String account = user.getAccount();
        if (account != null && !account.isEmpty()) {
            user.setAccount("%" + account + "%");
        }
        String name = user.getName();
        if (name != null && !name.isEmpty()) {
            user.setName("%" + name + "%");
        }
        //调用mapper
        //当前页数据
        List<User> rows = mapper.selectByPageAndCondition2(begin, size, user);
        //查询总记录数
        int totalCount = mapper.selectTotalCountAndCondition2(user);
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        // 释放资源
        sqlSession.close();
        return pageBean;
    }

    //复职
    @Override
    public void reWork(User user) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用mapper
        user.setIsJob("在职");
        mapper.updateState(user);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //返回登录员工信息
    @Override
    public User loadData(User user) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用mapper
        User u = mapper.selectUserById(user);
        // 释放资源
        sqlSession.close();
        return u;
    }

    //用id找用户
    @Override
    public User selectUserById(User user) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用mapper
        User user1 = mapper.selectUserById(user);
        // 释放资源
        sqlSession.close();
        return user1;
    }
}
