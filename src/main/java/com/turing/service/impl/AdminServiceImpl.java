package com.turing.service.impl;

import com.turing.mapper.AdminMapper;
import com.turing.pojo.Admin;
import com.turing.pojo.User;
import com.turing.service.AdminService;
import com.turing.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Objects;

public class AdminServiceImpl implements AdminService {
    //创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //登录
    @Override
    public User login(Admin admin) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取AdminMapper
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //调用mapper
        Admin ad = mapper.selectAdminByAccountAndPassword(admin);
        User user = new User();
        if (ad != null) {
            user.setId(ad.getId());
            user.setName(ad.getName());
        }
        //释放资源
        sqlSession.close();
        return user;
    }

    //注册
    @Override
    public String addAdmin(Admin admin) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取AdminMapper
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //调用mapper
        Admin ad = mapper.selectAdminByAccount(admin);
        if (ad == null) {
            mapper.insertAdmin(admin);
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

    //返回管理员信息
    @Override
    public Admin loadData(int id) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取AdminMapper
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //调用mapper
        Admin admin = mapper.selectAdminById(id);
        // 释放资源
        sqlSession.close();
        return admin;
    }

    //注销
    @Override
    public String logOffAdmin(Admin admin) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取AdminMapper
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //调用mapper
        Admin ad = mapper.selectAdminById(admin.getId());
        if (ad.getPassword().equals(admin.getPassword())) {
            //密码正确
            mapper.deleteAdminById(admin.getId());
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

    //修改信息
    @Override
    public String modifyInformation(Admin admin) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取AdminMapper
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //调用mapper
        Admin admin1 = mapper.selectAdminByAccount(admin);
        if (admin1 != null && !Objects.equals(admin1.getId(), admin.getId())) {
            //账号被占用
            // 释放资源
            sqlSession.close();
            return "fail";
        }
        Admin admin2 = mapper.selectAdminById(admin.getId());
        admin.setPassword(admin2.getPassword());
        mapper.updateAdminById(admin);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
        return "success";
    }

    //修改密码
    @Override
    public String modifyPassword(Admin admin) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取AdminMapper
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //调用mapper
        Admin admin1 = mapper.selectAdminById(admin.getId());
        if (!Objects.equals(admin1.getPassword(), admin.getAccount())) {
            //密码错误
            // 释放资源
            sqlSession.close();
            return "fail";
        }
        admin1.setPassword(admin.getPassword());
        mapper.updateAdminById(admin1);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
        return "success";
    }
}
