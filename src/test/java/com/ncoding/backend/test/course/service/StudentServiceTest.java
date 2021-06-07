package com.ncoding.backend.test.course.service;

import com.ncoding.backend.test.course.core.Student;
import com.ncoding.backend.test.course.util.exception.response.custom.CustomBadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    StudentService service;

    @Test
    public void test01() {
        assertThrows(CustomBadRequestException.class, () -> {
            final Student student = this.createStudent();

            service.save(student);
        }, Student.DISABLE_OBJECT);
    }

    private Student createStudent() {
        final Integer status = ClassStatusEnum.DISABLE.getCode();
        final String firstName = "Omar";
        final String lastName = "Huanca";
        return new Student(status, firstName, lastName);
    }
}
