package com.cpp.lilin.sqlitedatabasedemo.bean;

/**
 * Created by Colin on 2017/4/22.
 */

public class Student {

    private int id;
    private String name;
    private String tel;
    private int clsId;

    public Student(String name, String tel, int clsId) {
        this.name = name;
        this.tel = tel;
        this.clsId = clsId;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getClsId() {
        return clsId;
    }

    public void setClsId(int clsId) {
        this.clsId = clsId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", clsId=" + clsId +
                '}';
    }
}
