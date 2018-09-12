package com.jaremo.test_mybatis.lesson2.domain;

import java.util.List;

public class Grade {
    private String gid;
    private String gname1;
    private List<Student> studentList;

    @Override
    public String toString() {
        return "Grade{" +
                "gid='" + gid + '\'' +
                ", gname1='" + gname1 + '\'' +
                ", studentList=" + studentList +
                '}';
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname1;
    }

    public void setGname(String gname) {
        this.gname1 = gname;
    }
}
