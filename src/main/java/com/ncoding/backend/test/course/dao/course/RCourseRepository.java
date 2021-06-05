package com.ncoding.backend.test.course.dao.course;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ncoding.backend.test.course.core.Course;
import com.ncoding.backend.test.course.dao.RepositoryUtil;
import com.ncoding.backend.test.course.service.Pagination;
import com.ncoding.backend.test.course.util.exception.RepositoryException;

@Repository
public class RCourseRepository {

    @Autowired
    MCourseMapper mapper;

    public Course getObjectById(Optional<Long> id) throws RepositoryException {
        return mapper.getRecordById(id.orElse(null));
    }

    public List<Course> getAllObjects(Optional<Integer> status, Optional<Double> price, Optional<String> title,
            Integer pageSize, Integer page) throws RepositoryException {
        int limit = RepositoryUtil.calcLimit(Pagination.MAX_PAGE_SIZE.getCode(), pageSize);
        int offset = RepositoryUtil.calcOffset(limit, page);

        return mapper.getAllRecords(status.orElse(null), price.orElse(null), title.orElse(null), limit, offset);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<Double> price, Optional<String> title)
            throws RepositoryException {
        return mapper.getCountAllRecords(status.orElse(null), price.orElse(null), title.orElse(null));
    }

    public Course save(Course object) throws RepositoryException {
        mapper.saveRecord(object);
        return object;
    }

    public Course update(Course object) throws RepositoryException {
        mapper.updateRecord(object);
        return object;
    }

    public void delete(Course object) throws RepositoryException {
        mapper.deleteRecord(object);
    }
}
