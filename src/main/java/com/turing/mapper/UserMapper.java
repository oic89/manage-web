package com.turing.mapper;

import com.turing.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    //用账号密码搜索用户
    @Select("select * from tb_user where account=#{account} and password=#{password} ")
    @ResultMap("userResultMap")
    User selectUserByAccountAndPassword(User user);

    //用账号搜索用户
    @Select("select * from tb_user where account=#{account}")
    @ResultMap("userResultMap")
    User selectUserByAccount(User user);

    //添加用户
    @Insert("insert into tb_user(id,account,name,sex,password,basic_salary,is_job,face) " +
            "values (null,#{account},#{name},#{sex},#{password},#{basicSalary},#{isJob},#{face})")
    @ResultMap("userResultMap")
    void insertUser(User user);

    //删除用户
    @Delete("delete from tb_user where id=#{id}")
    @ResultMap("userResultMap")
    void deleteUser(User user);

    //修改用户信息
    @Update("update tb_user set account=#{account},name=#{name},sex=#{sex},basic_salary=#{basicSalary},face=#{face},password=#{password} where id=#{id}")
    @ResultMap("userResultMap")
    void updateUserById(User user);

    //用id找用户
    @Select("select * from tb_user where id=#{id}")
    @ResultMap("userResultMap")
    User selectUserById(User user);

    //分页条件查询在职用户
    List<User> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("user") User user);

    //条件查询在职用户总页数
    int selectTotalCountAndCondition(User user);

    //分页条件查询离职用户
    List<User> selectByPageAndCondition2(@Param("begin") int begin, @Param("size") int size, @Param("user") User user);

    //条件查询离职用户总页数
    int selectTotalCountAndCondition2(User user);

    //修改用户状态
    @Update("update tb_user set is_job=#{isJob} where id=#{id}")
    @ResultMap("userResultMap")
    void updateState(User user);

    //查询全部在职用户id
    @Select("select * from tb_user")
    @ResultMap("userResultMap")
    List<User> selectAll();
}
