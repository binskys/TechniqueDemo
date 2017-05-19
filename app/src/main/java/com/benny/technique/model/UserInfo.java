package com.benny.technique.model;

/**
 * 描述：
 * Created by Benny on 2017/3/6.
 * PackageName com.benny.technique.model
 */

public class UserInfo {
    private String name;
    private int age;
    private String sex;
    private String tel;

    public UserInfo(String name, int age, String sex, String tel) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age+"";
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
