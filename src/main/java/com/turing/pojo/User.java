package com.turing.pojo;

public class User {
    //id
    private Integer id;
    //工号
    private String account;
    //名字
    private String name;
    //性别
    private String sex;
    //密码
    private String password;
    //基础工资
    private Integer basicSalary;
    //是否在职
    private String isJob;
    //头像
    private String face;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Integer basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getIsJob() {
        return isJob;
    }

    public void setIsJob(String isJob) {
        this.isJob = isJob;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", basicSalary=" + basicSalary +
                ", isJob='" + isJob + '\'' +
                ", face='" + face + '\'' +
                '}';
    }
}
