package com.turing.service.impl;

import com.turing.mapper.UserMapper;
import com.turing.mapper.WorkMapper;
import com.turing.pojo.PageBean;
import com.turing.pojo.User;
import com.turing.pojo.Work;
import com.turing.service.WorkService;
import com.turing.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.time.LocalDate;
import java.util.List;

public class WorkServiceImpl implements WorkService {
    //创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //分页条件查询（当前页码，每页展示条数）
    @Override
    public PageBean<Work> selectByPageAndCondition(int currentPage, int pageSize, Work work) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取WorkMapper
        WorkMapper mapper = sqlSession.getMapper(WorkMapper.class);
        //计算开始索引
        int begin=(currentPage-1)*pageSize;
        //计算查询条数
        int size=pageSize;
        //处理模糊表达式
        String name = work.getName();
        if (name!= null && !name.isEmpty()) {
            work.setName("%" + name + "%");
        }
        //处理模糊表达式
        String date = work.getDate();
        if (date!= null && !date.isEmpty()) {
            work.setDate("%" + date + "%");
        }
        //调用mapper
        //当前页数据
        List<Work> rows = mapper.selectByPageAndCondition(begin,size,work);
        if (rows!=null){
            int n = rows.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    LocalDate localDate1 = LocalDate.parse(rows.get(j).getDate());
                    LocalDate localDate2 = LocalDate.parse(rows.get(j + 1).getDate());
                    if (localDate1.isBefore(localDate2)) {
                        Work temp = rows.get(j);
                        rows.set(j, rows.get(j + 1));
                        rows.set(j + 1, temp);
                    }
                }
            }
        }
        //查询总记录数
        int totalCount = mapper.selectTotalCountAndCondition(work);
        PageBean<Work> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        // 释放资源
        sqlSession.close();
        return pageBean;
    }

    //分页条件查询登录用户（当前页码，每页展示条数）
    @Override
    public PageBean<Work> selectByPageAndCondition1(int currentPage, int pageSize, Work work) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取WorkMapper
        WorkMapper mapper = sqlSession.getMapper(WorkMapper.class);
        //计算开始索引
        int begin=(currentPage-1)*pageSize;
        //计算查询条数
        int size=pageSize;
        //处理模糊表达式
        String date = work.getDate();
        if (date!= null && !date.isEmpty()) {
            work.setDate("%" + date + "%");
        }
        //调用mapper
        //当前页数据
        List<Work> rows = mapper.selectByPageAndCondition1(begin,size,work);
        if (rows!=null){
            int n = rows.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    LocalDate localDate1 = LocalDate.parse(rows.get(j).getDate());
                    LocalDate localDate2 = LocalDate.parse(rows.get(j + 1).getDate());
                    if (localDate1.isBefore(localDate2)) {
                        Work temp = rows.get(j);
                        rows.set(j, rows.get(j + 1));
                        rows.set(j + 1, temp);
                    }
                }
            }
        }
        //查询总记录数
        int totalCount = mapper.selectTotalCountAndCondition1(work);
        PageBean<Work> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        // 释放资源
        sqlSession.close();
        return pageBean;
    }

    //修改出勤信息
    @Override
    public void updateWork(Work work) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取WorkMapper
        WorkMapper mapper = sqlSession.getMapper(WorkMapper.class);
        //调用mapper
        mapper.updateWork(work);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //删除出勤信息
    @Override
    public void deleteWork(Work work) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取WorkMapper
        WorkMapper mapper = sqlSession.getMapper(WorkMapper.class);
        //调用mapper
        mapper.delete(work);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //添加出勤信息
    @Override
    public String addWork(Work work) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取WorkMapper和userMapper
        WorkMapper workMapper = sqlSession.getMapper(WorkMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用mapper
        User user=new User();
        user.setAccount(work.getAccount());
        User u = userMapper.selectUserByAccount(user);
        if (u==null){
            // 释放资源
            sqlSession.close();
            return "fail";
        }
        work.setUserId(u.getId());
        work.setName(u.getName());
        work.setFace(u.getFace());
        work.setSex(u.getSex());
        workMapper.insertWork(work);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
        return "success";
    }
}
