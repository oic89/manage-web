package com.turing.service.impl;

import com.turing.mapper.NoticeMapper;
import com.turing.pojo.Notice;
import com.turing.pojo.PageBean;
import com.turing.service.NoticeService;
import com.turing.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NoticeServiceImpl implements NoticeService {
    //创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //分页条件查询（当前页码，每页展示条数）
    @Override
    public PageBean<Notice> selectByPageAndCondition(int currentPage, int pageSize, Notice notice) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取NoticeMapper
        NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
        //计算开始索引
        int begin=(currentPage-1)*pageSize;
        //计算查询条数
        int size=pageSize;
        //处理模糊表达式
        String text = notice.getText();
        if (text != null && !text.isEmpty()) {
            notice.setText("%" + text + "%");
            notice.setTitle("%" + text + "%");
        }
        String date = notice.getDate();
        if (date!= null && !date.isEmpty()) {
            notice.setDate("%" + date + "%");
        }
        //调用mapper
        //当前页数据
        String state = notice.getState();
        List<Notice> rows1;
        List<Notice> rows2;
        List<Notice> rows =new ArrayList<>();
        if (state.isEmpty()||"置顶".equals(state)){
            rows1= mapper.selectByPageAndCondition1(begin,size,notice);
            if (rows1!=null){
                int n = rows1.size();
                for (int i = 0; i < n-1; i++) {
                    for (int j = 0; j < n-i-1; j++) {
                        LocalDate localDate1 = LocalDate.parse(rows1.get(j).getDate());
                        LocalDate localDate2 = LocalDate.parse(rows1.get(j+1).getDate());
                        if (localDate1.isBefore(localDate2)) {
                            Notice temp=rows1.get(j);
                            rows1.set(j,rows1.get(j+1));
                            rows1.set(j+1,temp);
                        }
                    }
                }
                rows.addAll(rows1);
            }
        }
        if (state.isEmpty()||"普通".equals(state)){
            rows2= mapper.selectByPageAndCondition2(begin,size,notice);
            if (rows2!=null){
                int n = rows2.size();
                for (int i = 0; i < n-1; i++) {
                    for (int j = 0; j < n-i-1; j++) {
                        LocalDate localDate1 = LocalDate.parse(rows2.get(j).getDate());
                        LocalDate localDate2 = LocalDate.parse(rows2.get(j+1).getDate());
                        if (localDate1.isBefore(localDate2)) {
                            Notice temp=rows2.get(j);
                            rows2.set(j,rows2.get(j+1));
                            rows2.set(j+1,temp);
                        }
                    }
                }
                rows.addAll(rows2);
            }
        }
        //查询总记录数
        int totalCount = mapper.selectTotalCountAndCondition(notice);
        PageBean<Notice> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        // 释放资源
        sqlSession.close();
        return pageBean;
    }

    //修改公告信息
    @Override
    public void updateNotice(Notice notice) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取NoticeMapper
        NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
        //调用mapper
        Notice n = mapper.selectNoticeById(notice);
        if (!n.getText().equals(notice.getText())||!n.getTitle().equals(notice.getTitle())){
            //获取时间
            LocalDate date=LocalDate.now();
            notice.setDate(String.valueOf(date));
        }
        mapper.updateNoticeById(notice);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //新增公告
    @Override
    public void addNotice(Notice notice) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取NoticeMapper
        NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
        //获取时间
        LocalDate date=LocalDate.now();
        notice.setDate(String.valueOf(date));
        //调用mapper
        mapper.insertNotice(notice);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    //删除公告
    @Override
    public void deleteNotice(Notice notice) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取NoticeMapper
        NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
        //调用mapper
        mapper.deleteNotice(notice);
        //提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }
}
