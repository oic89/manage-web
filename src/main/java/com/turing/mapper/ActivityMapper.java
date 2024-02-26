package com.turing.mapper;

import com.turing.pojo.Activity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ActivityMapper {
    //分页条件查询活动
    List<Activity> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("activity") Activity activity);

    //条件查询活动总页数
    int selectTotalCountAndCondition(Activity activity);

    //查询全部活动
    @Select("select * from tb_activity")
    @ResultMap("activityResultMap")
    List<Activity> selectAll();

    //修改活动信息
    @Update("update tb_activity set number=#{number},name=#{activityName},brief=#{brief},date=#{date},state=#{state} where id=#{activityId}")
    @ResultMap("activityResultMap")
    void updateActivity(Activity activity);

    //添加活动
    @Insert("insert into tb_activity (id,number,name,brief,date,state) values (null,#{number},#{activityName},#{brief},#{date},#{state})")
    @ResultMap("activityResultMap")
    void insertActivity(Activity activity);

    //删除活动
    @Delete("delete from tb_activity where id=#{activityId}")
    @ResultMap("activityResultMap")
    void deleteActivityById(Activity activity);

    //查看活动报名
    List<Activity> selectActivityDataById(Activity activity);

    //查找我的活动数据
    @Select("select * from tb_activity_data where user_id=#{userId}")
    @ResultMap("activityDataResultMap")
    List<Activity> selectMyActivityDataById(Activity activity);

    //用activityId删除activityData
    @Delete("delete from tb_activity_data where activity_id=#{activityId}")
    @ResultMap("activityDataResultMap")
    void deleteActivityDataById(Activity activity);

    //修改成绩
    @Update("update tb_activity_data set score=#{score} where id=#{activityDataId}")
    @ResultMap("activityDataResultMap")
    void updateScore(Activity activity);

    //用userId删除activityData
    @Delete("delete from tb_activity_data where user_id=#{userId}")
    @ResultMap("activityDataResultMap")
    void deleteActivityDataByUserId(Activity activity);

    //用userId查找activityData
    @Select("select * from tb_activity_data where user_id=#{userId}")
    @ResultMap("activityDataResultMap")
    List<Activity> selectActivityDataByUserId(Activity activity);

    //用userId修改activityData
    @Update("update tb_activity_data set face=#{face},account=#{account},name=#{userName} where user_id=#{userId}")
    @ResultMap("activityDataResultMap")
    void updateActivityDataByUserId(Activity activity);

    //用activityId人数-1
    @Update("update tb_activity set number=(number-1) where id=#{activityId}")
    @ResultMap("activityResultMap")
    void updateNumberByActivityId(Activity activity);

    //用activityId人数+1
    @Update("update tb_activity set number=(number+1) where id=#{activityId}")
    @ResultMap("activityResultMap")
    void updateNumberByActivityId2(Activity activity);

    //用activityId和userId查找activityData
    @Select("select * from tb_activity_data where activity_id=#{activityId} and user_id=#{userId}")
    @ResultMap("activityDataResultMap")
    Activity selectActivityDataByActivityIdAndUserId(Activity activity);

    //添加activityData
    @Insert("insert into tb_activity_data (id,activity_id,user_id,face,account,name,score) values (null,#{activityId},#{userId},#{face},#{account},#{userName},#{score})")
    @ResultMap("activityDataResultMap")
    void insertActivityData(Activity activity);

    //用activityId查找activity
    @Select("select * from tb_activity where id=#{activityId}")
    @ResultMap("activityResultMap")
    Activity selectActivityByActivityId(Activity activity);

    //用userId和activityId删除activityData
    @Delete("delete from tb_activity_data where user_id=#{userId} and activity_id=#{activityId}")
    @ResultMap("activityDataResultMap")
    void deleteActivityDataByUserIdAndActivityId(Activity activity);
}
