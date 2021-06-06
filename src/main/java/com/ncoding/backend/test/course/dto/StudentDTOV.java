package com.ncoding.backend.test.course.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ncoding.backend.test.course.core.Student;

public class StudentDTOV {

    @Min(value = 0, message = "The state cannot be less than 0 o greather than 1.")
    @Max(1)
    private Integer status;

    @NotNull(message = "The firstName is not valid.")
    @Size(max = 70, message = "The firstName no should be greather than 70 characters.")
    @NotBlank(message = "The firstName is not valid.")
    private String firstName;

    @NotNull(message = "The lastName is not valid.")
    @Size(max = 70, message = "The lastName no should be greather than 70 characters.")
    @NotBlank(message = "The lastName is not valid.")
    private String lastName;

    @NotNull(message = "The address is not valid.")
    @Size(max = 500, message = "The address no should be greather than 500 characters.")
    private String address;

    @NotNull(message = "The mobilePhone is not valid.")
    @Size(max = 15, message = "The mobilePhone should not be greather than 15 characters.")
    @StudentMobilePhone
    private String mobilePhone;

    public void copyCoreObject(Student object) {
        object.setStatus(status);
        object.setFirstName(firstName);
        object.setLastName(lastName);
        object.setAddress(address);
        object.setMobilePhone(mobilePhone);
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
}
