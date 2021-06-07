package com.ncoding.backend.test.course.dao.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ncoding.backend.test.course.core.Category;
import com.ncoding.backend.test.course.dao.RepositoryUtil;
import com.ncoding.backend.test.course.service.Pagination;
import com.ncoding.backend.test.course.util.exception.RepositoryException;

@Repository
public class RCategoryRepository {

    @Autowired
    MCategoryMapper mapper;

    public Category getObjectById(Optional<Long> id) throws RepositoryException {
        return mapper.getRecordById(id.orElse(null));
    }

    public List<Category> getAllObjects(Optional<Integer> status, Optional<String> name, Integer pageSize, Integer page)
            throws RepositoryException {
        int limit = RepositoryUtil.calcLimit(Pagination.MAX_PAGE_SIZE.getCode(), pageSize);
        int offset = RepositoryUtil.calcOffset(limit, page);

        return mapper.getAllRecords(status.orElse(null), name.orElse(null), limit, offset);
    }

    public Integer getCountAllObjects(Optional<Integer> status, Optional<String> name) throws RepositoryException {
        return mapper.getCountAllRecords(status.orElse(null), name.orElse(null));
    }

    public Category save(Category object) throws RepositoryException {
        mapper.saveRecord(object);
        return object;
    }
}
