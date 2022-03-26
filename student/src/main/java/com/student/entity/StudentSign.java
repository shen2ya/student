package com.student.entity;

import java.io.Serializable;
import java.util.Date;

public class StudentSign implements Serializable {
    private Integer id;

    private Integer studentId;

    private Date signTime;

    private Integer signType;

    private Integer index;

    private String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }
}