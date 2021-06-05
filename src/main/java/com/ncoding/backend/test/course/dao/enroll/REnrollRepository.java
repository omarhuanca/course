package com.ncoding.backend.test.course.dao.enroll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ncoding.backend.test.course.core.Enroll;
import com.ncoding.backend.test.course.dao.RepositoryUtil;
import com.ncoding.backend.test.course.service.Pagination;
import com.ncoding.backend.test.course.util.exception.RepositoryException;

@Repository
public class REnrollRepository {

    @Autowired
    MEnrollMapper mapper;

    public Enroll getObjectById(Optional<Long> id) throws RepositoryException {
        return mapper.getRecordById(id.orElse(null));
    }

    public List<Enroll> getAllObjects(Optional<Integer> status, Optional<Long> studentId, Optional<Long> courseId,
            Integer pageSize, Integer page) throws RepositoryException {
        int limit = RepositoryUtil.calcLimit(Pagination.MAX_PAGE_SIZE.getCode(), pageSize);
        int offset = RepositoryUtil.calcOffset(limit, page);

        return mapper.getAllRecords(status.orElse(null), studentId.orElse(null), courseId.orElse(null), limit, offset);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<Long> studentId, Optional<Long> courseId)
            throws RepositoryException {
        return mapper.getCountAllRecords(status.orElse(null), studentId.orElse(null), courseId.orElse(null));
    }

    public Enroll save(Enroll object) throws RepositoryException {
        mapper.saveRecord(object);
        return object;
    }

    public Enroll update(Enroll object) throws RepositoryException {
        mapper.updateRecord(object);
        return object;
    }

    public void delete(Enroll object) throws RepositoryException {
        mapper.deleteRecord(object);
    }
}
