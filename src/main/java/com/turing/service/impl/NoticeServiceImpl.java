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
        int begin = (currentPage - 1) * pageSize;
        //计算查询条数
        int size = pageSize;
        //处理模糊表达式
        String text = notice.getText();
        if (text != null && !text.isEmpty()) {
            notice.setText("%" + text + "%");
            notice.setTitle("%" + text + "%");
        }
        String date = notice.getDate();
        if (date != null && !date.isEmpty()) {
            notice.setDate("%" + date + "%");
        }
        //调用mapper
        //当前页数据
        String state = notice.getState();
        List<Notice> rows = new ArrayList<>();
        if (state.isEmpty() || "置顶".equals(state)) {
            //取出置顶公告
            List<Notice> rows1 = mapper.selectByPageAndCondition1(notice);
            List<Notice> notices1 = sortRows(rows1);
            rows.addAll(notices1);
        }
        if (state.isEmpty() || "普通".equals(state)) {
            //取出普通公告
            List<Notice> rows2 = mapper.selectByPageAndCondition2(notice);
            List<Notice> notices2 = sortRows(rows2);
            rows.addAll(notices2);
        }
        List<Notice> rows3 = new ArrayList<>();
        //取出所需页码的公告
        for (int i = begin; i < begin + size; i++) {
            if (!(i < rows.size())) {
                break;
            }
            rows3.add(rows.get(i));
        }
        //查询总记录数
        int totalCount = mapper.selectTotalCountAndCondition(notice);
        PageBean<Notice> pageBean = new PageBean<>();
        pageBean.setRows(rows3);
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
        if (!n.getText().equals(notice.getText()) || !n.getTitle().equals(notice.getTitle())) {
            //获取时间
            LocalDate date = LocalDate.now();
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
        LocalDate date = LocalDate.now();
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

    //将公告信息排列
    private List<Notice> sortRows(List<Notice> rows) {
        int n = rows.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                LocalDate localDate1 = LocalDate.parse(rows.get(j).getDate());
                LocalDate localDate2 = LocalDate.parse(rows.get(j + 1).getDate());
                if (localDate1.isBefore(localDate2)) {
                    Notice temp = rows.get(j);
                    rows.set(j, rows.get(j + 1));
                    rows.set(j + 1, temp);
                }
            }
        }
        return rows;
    }

}
