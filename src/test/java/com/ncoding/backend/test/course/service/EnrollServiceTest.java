package com.ncoding.backend.test.course.service;

import com.ncoding.backend.test.course.core.Category;
import com.ncoding.backend.test.course.core.Course;
import com.ncoding.backend.test.course.core.Enroll;
import com.ncoding.backend.test.course.core.Student;

import com.ncoding.backend.test.course.util.exception.response.custom.CustomBadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EnrollServiceTest {

    @Autowired
    EnrollService service;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @Autowired
    CategoryService categoryService;

    @Test
    public void test01() {
        Student student = this.createStudent();
        studentService.save(student);

        List<Student> listStudent = studentService.getAllObjects(Optional.of(ClassStatusEnum.ENABLE.getCode()), Optional.of(student.getFirstName()), Optional.of(student.getLastName()), PaginationEnum.DEFAULT_PAGE_SIZE.getCode(), PaginationEnum.DEFAULT_PAGE.getCode());

        assertTrue(0 < listStudent.size());
    }

    @Test
    public void test02() throws CustomBadRequestException {
        //assertThrows(CustomBadRequestException.class, () -> {
            final Integer status = ClassStatusEnum.ENABLE.getCode();
            Student student = this.createStudent();
            studentService.save(student);

            Course course = this.createCourse();
            courseService.save(course);

            Enroll enroll = new Enroll(student, course, status);
            Enroll fetchEnroll = service.save(enroll);
            fetchEnroll.setUid(null);
            service.save(fetchEnroll);

        //}, Enroll.DUPLICATE_RECORD);
    }

    private Student createStudent() {
        final Integer status = ClassStatusEnum.ENABLE.getCode();
        final String firstName = "Omar";
        final String lastName = "Huanca";
        return new Student(status, firstName, lastName);
    }

    private Category createCategory() {
        final Integer status = ClassStatusEnum.ENABLE.getCode();
        final String name = "DevOps";
        return new Category(status, name);
    }

    private Course createCourse() {
        final Category category = this.createCategory();
        final Integer status = ClassStatusEnum.ENABLE.getCode();
        final Double price = 154.0;
        final String title = "IOS Practices";
        final String description = "The course use JavaScript to do simple mobile application";
        categoryService.save(category);
        return new Course(category, status, price, title, description);
    }
}
