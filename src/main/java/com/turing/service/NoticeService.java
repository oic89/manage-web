package com.turing.service;

import com.turing.pojo.Notice;
import com.turing.pojo.PageBean;

public interface NoticeService {
    //分页条件查询用户信息
    PageBean<Notice> selectByPageAndCondition(int currentPage, int pageSize, Notice notice);

    //修改公告信息
    void updateNotice(Notice notice);

    //新增公告
    void addNotice(Notice notice);

    //删除
    void deleteNotice(Notice notice);
}
