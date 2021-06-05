package com.ncoding.backend.test.course.dao.student;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ncoding.backend.test.course.core.Student;
import com.ncoding.backend.test.course.util.exception.RepositoryException;

@Mapper
public interface MStudentMapper {

    public Student getRecordById(@Param("recordId") Long recordId) throws RepositoryException;

    public List<Student> getAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordFirstName") String recordFirstName, @Param("recordLastName") String recordLastName,
            @Param("limit") Integer limit, @Param("offset") Integer offset) throws RepositoryException;

    public Integer getCountAllRecords(@Param("recordStatus") Integer recordStatus,
            @Param("recordFirstName") String recordFirstName, @Param("recordLastName") String recordLastName)
            throws RepositoryException;

    public Integer saveRecord(@Param("student") Student student) throws RepositoryException;

    public void updateRecord(@Param("student") Student student) throws RepositoryException;
}
