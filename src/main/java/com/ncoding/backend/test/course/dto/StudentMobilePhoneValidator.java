package com.ncoding.backend.test.course.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ncoding.backend.test.course.service.StudentService;

public class StudentMobilePhoneValidator implements ConstraintValidator<StudentMobilePhone, String>{

    @Autowired
    private StudentService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return service.validateMobilePhone(value);
    }
}
