package com.ncoding.backend.test.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncoding.backend.test.course.core.Course;
import com.ncoding.backend.test.course.core.Enroll;
import com.ncoding.backend.test.course.core.Student;
import com.ncoding.backend.test.course.dao.enroll.REnrollRepository;
import com.ncoding.backend.test.course.util.exception.RepositoryException;
import com.ncoding.backend.test.course.util.exception.response.custom.CustomBadRequestException;

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
        Enroll response = new Enroll();
        this.verifyForeignKey(object);

        List<Enroll> filterEnroll = this.getAllObjects(Optional.of(ClassStatusEnum.ENABLE.getCode()), Optional.of(object.getStudent().getUid()), Optional.of(object.getCourse().getUid()), PaginationEnum.MAX_PAGE_SIZE.getCode(), PaginationEnum.DEFAULT_PAGE.getCode());
        if (0 < filterEnroll.size()) {
            new CustomBadRequestException(Enroll.DUPLICATE_RECORD);
        } else {
            response = repository.save(object);
        }

        return response;
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
        if (null == student || student.verifyStatusEnable(ClassStatusEnum.DISABLE.getCode())) {
            throw new CustomBadRequestException("The student id does not exist or student is disabled.");
        }
        Course course = courseService.getObjectById(Optional.ofNullable(object.getCourse().getUid()));
        if (null == course || course.verifyStatusEnable(ClassStatusEnum.DISABLE.getCode())) {
            throw new CustomBadRequestException("The course id does not exist or student is disabled.");
        }
    }
}
