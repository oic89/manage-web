package com.turing.mapper;

import com.turing.pojo.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface NoticeMapper {
    //分页条件查询置顶公告
    List<Notice> selectByPageAndCondition1(@Param("begin") int begin, @Param("size") int size, @Param("notice") Notice notice);

    //分页条件查询普通公告
    List<Notice> selectByPageAndCondition2(@Param("begin") int begin, @Param("size") int size, @Param("notice") Notice notice);

    //条件查询公告
    int selectTotalCountAndCondition(Notice notice);

    //修改公告信息
    @Update("update tb_notice set title=#{title},text=#{text},state=#{state},date=#{date} where id=#{id}")
    void updateNoticeById(Notice notice);

    //添加公告
    @Insert("insert into tb_notice(id,title,text,state,date) values (null,#{title},#{text},#{state},#{date})")
    void insertNotice(Notice notice);

    //删除公告
    @Delete("delete from tb_notice where id=#{id}")
    void deleteNotice(Notice notice);

    //用id查询公告
    @Select("select * from tb_notice where id=#{id}")
    Notice selectNoticeById(Notice notice);
}
