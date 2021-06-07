package com.ncoding.backend.test.course.core;

import java.sql.Timestamp;
import java.util.Objects;

public class Enroll {

    public static final String DUPLICATE_RECORD = "The same student and same course can not be register more times";

    private Long uid;

    private Student student;

    private Course course;

    private Integer status;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    public Enroll() {
    }

    public Enroll(Student student, Course course, Integer status) {
        this.student = student;
        this.course = course;
        this.status = status;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Boolean verifyStatusEnable(Integer otherStatus) {
        return status.equals(otherStatus);
    }

    @Override
    public boolean equals(Object obj) {
        boolean response = false;

        if (this == obj) {
            response = true;
        }

        if (obj instanceof Enroll) {
            Enroll other = (Enroll) obj;

            response = Objects.equals(this.uid, other.uid);
        }

        return response;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
}
