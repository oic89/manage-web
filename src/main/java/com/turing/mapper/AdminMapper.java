package com.turing.mapper;

import com.turing.pojo.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminMapper {
    //用账号密码搜索管理员
    @Select("select * from tb_admin where account=#{account} and password=#{password}")
    Admin selectAdminByAccountAndPassword(Admin admin);

    //用账号搜索管理员
    @Select("select * from tb_admin where account=#{account}")
    Admin selectAdminByAccount(Admin admin);

    //添加管理员
    @Insert("insert into tb_admin(id,account,name,password) values (null,#{account},#{name},#{password})")
    void insertAdmin(Admin admin);

    //用id获取管理员
    @Select("select * from tb_admin where id=#{id}")
    Admin selectAdminById(int id);

    //用id删除管理员
    @Delete("delete from tb_admin where id=#{id}")
    void deleteAdminById(int id);

    //用id修改信息
    @Update("update tb_admin set account=#{account},name=#{name},password=#{password} where id=#{id}")
    void updateAdminById(Admin admin);

}
