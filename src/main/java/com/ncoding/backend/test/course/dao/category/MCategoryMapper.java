package com.ncoding.backend.test.course.dao.category;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ncoding.backend.test.course.core.Category;
import com.ncoding.backend.test.course.util.exception.RepositoryException;

@Mapper
public interface MCategoryMapper {

    public Category getRecordById(@Param("recordId") Long recordId) throws RepositoryException;

    public List<Category> getAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordName") String recordName, @Param("limit") Integer limit, @Param("offset") Integer offset)
            throws RepositoryException;

    public Integer getCountAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordName") String recordName) throws RepositoryException;

    public Integer saveRecord(@Param("category") Category category) throws RepositoryException;
}
