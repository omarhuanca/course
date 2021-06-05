package com.ncoding.backend.test.course.core;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Student {

    private Long uid;

    private Integer status;

    private String firstName;

    private String lastName;

    private String address;

    private String mobilePhone;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    private List<Enroll> listEnroll;

    public Student() {
    }

    public Student(Integer status, String firstName, String lastName) {
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
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

    public List<Enroll> getListEnroll() {
        return listEnroll;
    }

    public void setListEnroll(List<Enroll> listEnroll) {
        this.listEnroll = listEnroll;
    }

    @Override
    public boolean equals(Object obj) {
        boolean response = false;

        if (this == obj) {
            response = true;
        }

        if (obj instanceof Student) {
            Student other = (Student) obj;

            response = Objects.equals(this.uid, other.uid);
        }

        return response;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
}
