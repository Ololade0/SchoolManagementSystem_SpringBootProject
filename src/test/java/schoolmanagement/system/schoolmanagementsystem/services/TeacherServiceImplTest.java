package schoolmanagement.system.schoolmanagementsystem.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Teacher;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.Gender;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.FindAllTeacherRequest;

import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.UpdatedTeacherProfileRequest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

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
        teacherService.deleteAll();
    }

    @Test
    public void addTeacher() {
        assertEquals("ololade", savedTeacher.getFirstName());
        assertThat(savedTeacher.getId()).isNotNull();
        assertEquals(1, teacherService.size());
    }

    @Test
    public void findTeacherById() {
        Teacher teacher = teacherService.findTeacherById(savedTeacher.getId());
        assertThat(teacher.getId()).isEqualTo(savedTeacher.getId());
        assertThat(savedTeacher.getId()).isNotNull();
    }

    @Test
    public void findTeacherByName() {
        Teacher teacher1 = teacherService.findTeacherByName(savedTeacher.getFirstName());
        assertEquals("ololade", teacher1.getFirstName());
        assertEquals("tosin", teacher1.getLastName());

    }

    @Test
    public void findAllTeachers() {
        FindAllTeacherRequest findAllTeacherRequest = FindAllTeacherRequest.builder()
                .page(1)
                .limit(1)

                .build();
       List<Teacher> teacherList = teacherService.findAllTeachers(findAllTeacherRequest.getLimit(), findAllTeacherRequest.getPage());
       assertEquals("tosin", teacherList.get(0).getLastName());
        assertEquals("ololade", teacherList.get(0).getFirstName());
        assertEquals("teacher@gmail.com", teacherList.get(0).getEmail());

    }

    @Test
    public void deleteTeachersById() {
        teacherService.deleteTeachersById(savedTeacher.getId());
        assertEquals(0, teacherService.size());
    }

    @Test
    public void deleteAllTeachers() {
        teacherService.deleteAll();
        assertEquals(0, teacherService.size());
    }

    @Test
    public void testThatStudentCanBeUpdated() {
        UpdatedTeacherProfileRequest updatedProfileRequest = UpdatedTeacherProfileRequest.builder()
                .firstName("Eunice")
                .teacherId(savedTeacher.getId())
                .lastName("Demilade")
                .email("DemmyOlyns@gmail.com")
                .gender(Gender.FEMALE)
                .build();
        Teacher updatedTeacher = teacherService.updateTeachersProfile(updatedProfileRequest);
        assertEquals("Eunice", updatedTeacher.getFirstName());
        assertEquals("Demilade", updatedTeacher.getLastName());
        assertEquals("DemmyOlyns@gmail.com", updatedTeacher.getEmail());
        assertEquals(Gender.FEMALE, updatedTeacher.getGender());
    }

}
