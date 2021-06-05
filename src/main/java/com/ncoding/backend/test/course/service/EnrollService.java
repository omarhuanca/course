package com.ncoding.backend.test.course.service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncoding.backend.test.course.core.Course;
import com.ncoding.backend.test.course.core.Enroll;
import com.ncoding.backend.test.course.core.Student;
import com.ncoding.backend.test.course.dao.enroll.REnrollRepository;
import com.ncoding.backend.test.course.util.exception.RepositoryException;

@Service
public class EnrollService {

    @Autowired
    REnrollRepository repository;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    public Enroll getObjectById(Optional<Long> id) throws RepositoryException {
        return repository.getObjectById(id);
    }

    public List<Enroll> getAllObjects(Optional<Integer> status, Optional<Long> studentId, Optional<Long> courseId,
            Integer pageSize, Integer pageNumber) throws RepositoryException {
        return repository.getAllObjects(status, studentId, courseId, pageSize, pageNumber);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<Long> studentId, Optional<Long> courseId)
            throws RepositoryException {
        return repository.getCountAllObjects(status, studentId, courseId);
    }

    public Enroll save(Enroll object) throws RepositoryException {
        this.verifyForeignKey(object);
        return repository.save(object);
    }

    public Enroll update(Enroll object) throws RepositoryException {
        this.verifyForeignKey(object);
        return repository.update(object);
    }

    public void delete(Enroll object) throws RepositoryException {
        repository.delete(object);
    }

    private void verifyForeignKey(Enroll object) {
        Student student = studentService.getObjectById(Optional.ofNullable(object.getStudent().getUid()));
        if (null == student) {
            throw new InvalidParameterException("The student id does not exist.");
        }
        Course course = courseService.getObjectById(Optional.ofNullable(object.getCourse().getUid()));
        if (null == course) {
            throw new InvalidParameterException("The course id does not exist.");
        }
    }
}
