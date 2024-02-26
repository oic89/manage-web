package com.turing.pojo;

public class Salary {
    //id
    private Integer id;
    //用户id
    private Integer userId;
    //总工资
    private Integer salary;
    //基础工资
    private Integer basicSalary;
    //迟到次数
    private Integer lateTime;
    //早退次数
    private Integer leaveEarlyTime;
    //缺勤次数
    private Integer absenceTime;
    //请假次数
    private Integer leaveTime;
    //日期
    private String date;

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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Integer basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Integer getLateTime() {
        return lateTime;
    }

    public void setLateTime(Integer lateTime) {
        this.lateTime = lateTime;
    }

    public Integer getLeaveEarlyTime() {
        return leaveEarlyTime;
    }

    public void setLeaveEarlyTime(Integer leaveEarlyTime) {
        this.leaveEarlyTime = leaveEarlyTime;
    }

    public Integer getAbsenceTime() {
        return absenceTime;
    }

    public void setAbsenceTime(Integer absenceTime) {
        this.absenceTime = absenceTime;
    }

    public Integer getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Integer leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", userId=" + userId +
                ", salary=" + salary +
                ", basicSalary=" + basicSalary +
                ", lateTime=" + lateTime +
                ", leaveEarlyTime=" + leaveEarlyTime +
                ", absenceTime=" + absenceTime +
                ", leaveTime=" + leaveTime +
                ", date='" + date + '\'' +
                '}';
    }
}
