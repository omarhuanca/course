package com.ncoding.backend.test.course.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.ncoding.backend.test.course.core.Course;
import com.ncoding.backend.test.course.core.Enroll;
import com.ncoding.backend.test.course.core.Student;

public class EnrollDTOV {

    @NotNull(message = "The student is not valid.")
    private Student student;

    @NotNull(message = "The course is not valid.")
    private Course course;

    @Min(value = 0, message = "The state cannot be less than 0 o greather than 1.")
    @Max(1)
    private Integer status;

    public void copyCoreObject(Enroll object) {
        object.setStudent(student);
        object.setCourse(course);
        object.setStatus(status);
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
}
