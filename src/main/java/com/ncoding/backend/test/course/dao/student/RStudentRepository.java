package com.ncoding.backend.test.course.dao.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ncoding.backend.test.course.core.Student;
import com.ncoding.backend.test.course.dao.RepositoryUtil;
import com.ncoding.backend.test.course.service.Pagination;
import com.ncoding.backend.test.course.util.exception.RepositoryException;

@Repository
public class RStudentRepository {

    @Autowired
    MStudentMapper mapper;

    public Student getObjectById(Optional<Long> id) throws RepositoryException {
        return mapper.getRecordById(id.orElse(null));
    }

    public List<Student> getAllObjects(Optional<Integer> status, Optional<String> firstName, Optional<String> lastName,
            Integer pageSize, Integer page) throws RepositoryException {
        int limit = RepositoryUtil.calcLimit(Pagination.MAX_PAGE_SIZE.getCode(), pageSize);
        int offset = RepositoryUtil.calcOffset(limit, page);

        return mapper.getAllRecords(status.orElse(null), firstName.orElse(null), lastName.orElse(null), limit, offset);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<String> firstName, Optional<String> lastName)
            throws RepositoryException {
        return mapper.getCountAllRecords(status.orElse(null), firstName.orElse(null), lastName.orElse(null));
    }

    public Student save(Student object) throws RepositoryException {
        mapper.saveRecord(object);
        return object;
    }

    public Student update(Student object) throws RepositoryException {
        mapper.updateRecord(object);
        return object;
    }
}
