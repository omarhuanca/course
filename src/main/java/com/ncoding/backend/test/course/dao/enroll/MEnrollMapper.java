package com.ncoding.backend.test.course.dao.enroll;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ncoding.backend.test.course.core.Enroll;
import com.ncoding.backend.test.course.util.exception.RepositoryException;

@Mapper
public interface MEnrollMapper {

    public Enroll getRecordById(@Param("recordId") Long recordId) throws RepositoryException;

    public List<Enroll> getAllRecords(@Param("recordStatus") Integer recordStatus, @Param("studentId") Long studentId,
            @Param("courseId") Long courseId, @Param("limit") Integer limit, @Param("offset") Integer offset)
            throws RepositoryException;

    public Integer getCountAllRecords(@Param("recordStatus") Integer recordStatus, @Param("studentId") Long studentId,
            @Param("courseId") Long courseId) throws RepositoryException;

    public Integer saveRecord(@Param("enroll") Enroll enroll) throws RepositoryException;

    public void updateRecord(@Param("enroll") Enroll enroll) throws RepositoryException;

    public void deleteRecord(@Param("enroll") Enroll enroll) throws RepositoryException;
}
