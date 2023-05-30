package schoolmanagement.system.schoolmanagementsystem.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Teacher;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.Gender;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.FindAllTeacherRequest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TeacherServiceImplTest {
    Teacher savedTeacher;
    @Autowired
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        Teacher teacher = Teacher.builder()
                .email("teacher@gmail.com")
                .gender(Gender.MALE)
                .firstName("ololade")
                .password("1234")
                .lastName("tosin")
                .dateOfBirth(LocalDate.now())
                .build();
         savedTeacher = teacherService.addTeacher(teacher);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void addTeacher() {
        assertEquals("ololade", savedTeacher.getFirstName());
        assertThat(savedTeacher.getId()).isNotNull();
    }

    @Test
    public void findTeacherById() {
        teacherService.findTeacherById(savedTeacher.getId());
//        assertEquals("ololade", savedTeacher.getFirstName());
//        assertThat(savedTeacher.getId()).isNotNull();
    }
    @Test
    public void findTeacherByName() {
        teacherService.findTeacherByName(savedTeacher.getFirstName());
//        assertEquals("ololade", savedTeacher.getFirstName());
//        assertThat(savedTeacher.getId()).isNotNull();
    }
    @Test
    public void findAllTeachers() {
        FindAllTeacherRequest findAllTeacherRequest = FindAllTeacherRequest.builder()
                        .page(1)
                                .limit(1)
                                        .build();
        teacherService.findAllTeachers(findAllTeacherRequest.getLimit(), findAllTeacherRequest.getPage());
//        assertEquals("ololade", savedTeacher.getFirstName());
//        assertThat(savedTeacher.getId()).isNotNull();
    }

    @Test
    public void findAllTeachers() {
        FindAllTeacherRequest findAllTeacherRequest = FindAllTeacherRequest.builder()
                .page(1)
                .limit(1)
                .build();
        teacherService.findAllTeachers(findAllTeacherRequest.getLimit(), findAllTeacherRequest.getPage()


}
