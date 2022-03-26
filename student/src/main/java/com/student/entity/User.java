package com.student.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private Integer id;

    private String name;

    private String pwd;

    private String phone;

    private Integer access;

    private Integer valid;

    private Integer index;

    private List<Integer> buildId;

    private List<Integer> classId;

    public List<Integer> getBuildId() {
        return buildId;
    }

    public void setBuildId(List<Integer> buildId) {
        this.buildId = buildId;
    }

    public List<Integer> getClassId() {
        return classId;
    }

    public void setClassId(List<Integer> classId) {
        this.classId = classId;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}