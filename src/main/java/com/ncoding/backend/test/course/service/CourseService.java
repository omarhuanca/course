package com.ncoding.backend.test.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncoding.backend.test.course.core.Category;
import com.ncoding.backend.test.course.core.Course;
import com.ncoding.backend.test.course.dao.course.RCourseRepository;
import com.ncoding.backend.test.course.util.exception.RepositoryException;
import com.ncoding.backend.test.course.util.exception.response.custom.CustomBadRequestException;

@Service
public class CourseService {

    @Autowired
    RCourseRepository repository;

    @Autowired
    CategoryService categoryService;

    public Course getObjectById(Optional<Long> id) throws RepositoryException {
        return repository.getObjectById(id);
    }

    public List<Course> getAllObjects(Optional<Integer> status, Optional<Double> price, Optional<String> title,
            Integer pageSize, Integer pageNumber) throws RepositoryException {
        return repository.getAllObjects(status, price, title, pageSize, pageNumber);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<Double> price, Optional<String> title)
            throws RepositoryException {
        return repository.getCountAllObjects(status, price, title);
    }

    public Course save(Course object) throws RepositoryException {
        this.verifyForeignKey(object);
        return repository.save(object);
    }

    public Course update(Course object) throws RepositoryException {
        this.verifyForeignKey(object);
        return repository.update(object);
    }

    public void delete(Course object) throws RepositoryException {
        repository.delete(object);
    }

    private void verifyForeignKey(Course object) {
        Category category = categoryService.getObjectById(Optional.ofNullable(object.getCategory().getUid()));
        if (null == category || category.verifyStatusEnable(ClassStatusEnum.DISABLE.getCode())) {
            throw new CustomBadRequestException("The category id does not exist or category is disable.");
        }
    }
}
