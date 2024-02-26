package com.turing.pojo;

public class Activity {
    //活动id
    private Integer activityId;
    //活动数据id
    private Integer activityDataId;
    //用户头像
    private String face;
    //用户id
    private Integer userId;
    //人数
    private Integer number;
    //活动名称
    private String activityName;
    //活动介绍
    private String brief;
    //活动日期
    private String date;
    //活动状态
    private String state;
    //用户账号
    private String account;
    //用户姓名
    private String userName;
    //分数
    private Integer score;

    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", activityDataId=" + activityDataId +
                ", face='" + face + '\'' +
                ", userId=" + userId +
                ", number=" + number +
                ", activityName='" + activityName + '\'' +
                ", brief='" + brief + '\'' +
                ", date='" + date + '\'' +
                ", state='" + state + '\'' +
                ", account='" + account + '\'' +
                ", userName='" + userName + '\'' +
                ", score=" + score +
                '}';
    }

    public Integer getActivityId() {
        return activityId;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getActivityDataId() {
        return activityDataId;
    }

    public void setActivityDataId(Integer activityDataId) {
        this.activityDataId = activityDataId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
