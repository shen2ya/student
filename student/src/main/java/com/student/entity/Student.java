package com.student.entity;

import java.io.Serializable;

public class Student implements Serializable {
    private Integer id;

    private String name;

    private String no;

    private Integer sex;

    private Integer studentClass;

    private Integer studentBuild;

    private Integer studentRoom;

    private String phone;

    private Integer valid;

    private Integer index;

    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(Integer studentClass) {
        this.studentClass = studentClass;
    }

    public Integer getStudentBuild() {
        return studentBuild;
    }

    public void setStudentBuild(Integer studentBuild) {
        this.studentBuild = studentBuild;
    }

    public Integer getStudentRoom() {
        return studentRoom;
    }

    public void setStudentRoom(Integer studentRoom) {
        this.studentRoom = studentRoom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}