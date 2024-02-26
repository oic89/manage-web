package com.turing.pojo;

public class Leave {
    //id
    private Integer id;
    //员工id
    private Integer userId;
    //员工姓名
    private String name;
    //员工账号
    private String account;
    //请假原因
    private String reason;
    //头像
    private String face;
    //起始日期
    private String startDate;
    //终止日期
    private String lastDate;
    //状态
    private String state;
    //性别
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", reason='" + reason + '\'' +
                ", face='" + face + '\'' +
                ", startDate='" + startDate + '\'' +
                ", lastDate='" + lastDate + '\'' +
                ", state='" + state + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
