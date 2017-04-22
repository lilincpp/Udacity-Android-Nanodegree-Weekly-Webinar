package com.cpp.lilin.sqlitedatabasedemo.bean;

/**
 * Created by Colin on 2017/4/22.
 */

public class Class {

    private int id;
    private String major;

    public Class() {
    }

    public Class(String major) {
        this.major = major;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", major='" + major + '\'' +
                '}';
    }
}
