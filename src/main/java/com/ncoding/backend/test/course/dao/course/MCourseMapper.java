package com.ncoding.backend.test.course.dao.course;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ncoding.backend.test.course.core.Course;
import com.ncoding.backend.test.course.util.exception.RepositoryException;

@Mapper
public interface MCourseMapper {

    public Course getRecordById(@Param("recordId") Long recordId) throws RepositoryException;

    public List<Course> getAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordPrice") Double recordPrice, @Param("recordTitle") String recordTitle,
            @Param("limit") Integer limit, @Param("offset") Integer offset) throws RepositoryException;

    public Integer getCountAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordPrice") Double recordPrice, @Param("recordTitle") String recordTitle)
            throws RepositoryException;

    public Integer saveRecord(@Param("course") Course course) throws RepositoryException;

    public void updateRecord(@Param("course") Course course) throws RepositoryException;

    public void deleteRecord(@Param("course") Course course) throws RepositoryException;
}
