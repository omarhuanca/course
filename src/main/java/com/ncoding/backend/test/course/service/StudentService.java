package com.ncoding.backend.test.course.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncoding.backend.test.course.core.Student;
import com.ncoding.backend.test.course.dao.student.RStudentRepository;
import com.ncoding.backend.test.course.util.exception.RepositoryException;
import com.ncoding.backend.test.course.util.exception.response.custom.CustomBadRequestException;

@Service
public class StudentService {

    private final String SUBSCRIBER_CELLPHONE_REGEX = "^\\+[5]{1}[9]{1}[1]{1}[0-9]{8}$";

    @Autowired
    RStudentRepository repository;

    public Student getObjectById(Optional<Long> id) throws RepositoryException {
        return repository.getObjectById(id);
    }

    public List<Student> getAllObjects(Optional<Integer> status, Optional<String> firstName, Optional<String> lastName,
            Integer pageSize, Integer pageNumber) throws RepositoryException {
        return repository.getAllObjects(status, firstName, lastName, pageSize, pageNumber);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<String> firstName, Optional<String> lastName)
            throws RepositoryException {
        return repository.getCountAllObjects(status, firstName, lastName);
    }

    public Student save(Student object) throws RepositoryException {
        if (object.verifyStatusEnable(ClassStatusEnum.DISABLE.getCode())) {
            throw new CustomBadRequestException("The student is disable.");
        }
        return repository.save(object);
    }

    public Student update(Student object) throws RepositoryException {
        return repository.update(object);
    }

    public Boolean validateMobilePhone(String value) {
        if (StringUtils.isBlank(value)) {
            return Boolean.TRUE;
        }
        return value.matches(SUBSCRIBER_CELLPHONE_REGEX);
    }
}
