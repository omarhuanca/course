package com.ncoding.backend.test.course.service;

import java.util.List;
import java.util.Optional;

import com.ncoding.backend.test.course.core.Student;
import com.ncoding.backend.test.course.util.exception.response.custom.CustomBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncoding.backend.test.course.core.Category;
import com.ncoding.backend.test.course.dao.category.RCategoryRepository;
import com.ncoding.backend.test.course.util.exception.RepositoryException;

@Service
public class CategoryService {

    @Autowired
    RCategoryRepository repository;

    public Category getObjectById(Optional<Long> id) throws RepositoryException {
        return repository.getObjectById(id);
    }

    public List<Category> getAllObjects(Optional<Integer> status, Optional<String> name,
            Integer pageSize, Integer pageNumber) throws RepositoryException {
        return repository.getAllObjects(status, name, pageSize, pageNumber);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<String> name)
            throws RepositoryException {
        return repository.getCountAllObjects(status, name);
    }

    public Category save(Category object) throws RepositoryException {
        if (object.verifyStatusEnable(ClassStatusEnum.DISABLE.getCode())) {
            throw new CustomBadRequestException(Student.DISABLE_OBJECT);
        }
        return repository.save(object);
    }
}
