package com.turing.pojo;

//工作表现
public class Work {
    //id
    private Integer id;
    //用户工号
    private Integer userId;
    //用户名
    private String name;
    //头像
    private String face;
    //性别
    private String sex;
    //账号
    private String account;
    //类型
    private String type;
    //日期
    private String date;

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", face='" + face + '\'' +
                ", sex='" + sex + '\'' +
                ", account='" + account + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
