package com.ncoding.backend.test.course.dto;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ncoding.backend.test.course.core.Student;

public class StudentDTOV {

    @Min(value = 0, message = "The id cannot be less than 0.")
    private Long uid;

    @Min(value = 0, message = "The state cannot be less than 0 o greather than 1.")
    @Max(1)
    private Integer status;

    @NotNull(message = "The address is not valid.")
    @Size(max = 500, message = "The address no should be greather than 500 characters.")
    private String address;

    @NotBlank(message = "The mobilePhone status is not valid.")
    @Size(max = 11, message = "The mobilePhone should not be greather than 11 characters.")
    @StudentMobilePhone
    private String mobilePhone;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    public void copyCoreObject(Student object) {
        object.setStatus(status);
        object.setFirstName(address);
        object.setLastName(address);
        object.setAddress(address);
        object.setMobilePhone(mobilePhone);
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
}
