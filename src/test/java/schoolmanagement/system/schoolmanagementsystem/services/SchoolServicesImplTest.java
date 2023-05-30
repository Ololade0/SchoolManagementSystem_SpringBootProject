package schoolmanagement.system.schoolmanagementsystem.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.*;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.CourseStatus;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.*;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.response.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SchoolServicesImplTest {
    RegisterSchoolResponse savedSchool;
    AdmitStudentResponse savedStudent;
    CreateCourseResponse createdCourse;

    @Autowired
    private SchoolServices schoolService;


    @BeforeEach
    void setUp() {
        RegisterSchoolRequest registerSchoolRequest = RegisterSchoolRequest
                .builder()
                .schoolLocation("Sabo")
                .email("Semicolon@gmail.com")
                .schoolName("Semicolon")
//                .roles(RoleType.ADMIN)
                .build();
        savedSchool = schoolService.registerSchool(registerSchoolRequest);



        AdmitStudentRequest admitStudentRequest = AdmitStudentRequest
                .builder()
                .studentFirstName("Eunice")
                .studentLastName("Demilade")
                .password("1234")
                .emailAddress("DemmyOlyns@gmail.com")
                .studentAge("30")
                .gender("FEMALE")
                .schoolId(savedSchool.getSchoolId())
                .build();
        savedStudent = schoolService.admitStudent(admitStudentRequest);



        CreateCourseRequest createCourseRequest = CreateCourseRequest.builder()
                .courseTitle("Introduction To Python")
                .courseCode("pyth101")
                .courseName("Python")
                .courseStatus(CourseStatus.ACTIVATED)
                .schoolId(savedSchool.getSchoolId())
                .build();
        createdCourse = schoolService.createCourse(createCourseRequest);


    }

    @AfterEach
    void tearDown() {

        schoolService.deleteAllSchools();
        schoolService.deleteAllStudents();
        schoolService.deleteAllCourses();
    }


    @Test
    public void testThatCanBeCreated() {
        School newSChool = School.builder()
                .schoolLocation("Sabo")
                .schoolName("Semicolon")
                .email("Semicolon@gmail.com")
                .build();
        assertEquals("Semicolon", newSChool.getSchoolName());
    }

    @Test
    public void testThatSchoolCanBeRegister() {
        assertEquals(1, schoolService.size());
    }

    @Test
    public void testThatSchoolCanBeFindById() {
        School foundSchool = schoolService.findSchoolById(savedSchool.getSchoolId());
        assertThat(foundSchool.getId()).isEqualTo(savedSchool.getSchoolId());
        assertThat(foundSchool.getId()).isGreaterThan("50");
        assertThat(foundSchool).isNotNull();
    }


    @Test
    public void findAllSchools() {
        FindAllSchoolRequest findAllSchoolRequest = FindAllSchoolRequest.builder()
                .page(1)
                .limit(1)
                .build();
        List<School> schoolList = schoolService.findAllSchool(findAllSchoolRequest.getPage(), findAllSchoolRequest.getLimit());
        assertEquals("Semicolon", schoolList.get(0).getSchoolName());
        assertEquals("Sabo", schoolList.get(0).getSchoolLocation());
        assertEquals("Semicolon@gmail.com", schoolList.get(0).getEmail());

    }

    @Test
    public void schoolCanBeDeletedById() {
        schoolService.deleteById(savedSchool.getSchoolId());
        assertEquals(0, schoolService.size());
    }

    @Test
    public void deleteAllSchools() {
        schoolService.deleteAllSchools();
        assertEquals(0, schoolService.size());

    }

    @Test
    public void schoolProfileCanBeUpdated() {
        UpdateSchoolProfileRequest updateSchoolProfileRequest = UpdateSchoolProfileRequest.builder()
                .schoolLocation("Lekki")
                .schoolName("Unilag")
                .email("Unilag@gmail.com")
                .build();
        updateSchoolProfileRequest.setSchoolId(savedSchool.getSchoolId());
        School updatedSchool = schoolService.updateSchoolProfile(updateSchoolProfileRequest);
        assertEquals("Unilag", updatedSchool.getSchoolName());
        assertEquals("Lekki", updatedSchool.getSchoolLocation());
        assertEquals("Unilag@gmail.com", updatedSchool.getEmail());

    }

    @Test
    public void testThatSchoolCanAdmitStudent() {
        assertEquals(1, schoolService.sizeOfStudents());

    }

    @Test
    public void testThatSchoolCanFindStudentById() {
        Student foundStudent = schoolService.findStudentById(savedSchool.getSchoolId(), savedStudent.getStudentId());
        assertThat(foundStudent.getId()).isEqualTo(savedStudent.getStudentId());
        assertThat(foundStudent).isNotNull();

    }

    @Test
    public void testThatSchoolCanFindStudentByName() {
        Student foundStudent = schoolService.findStudentByName(savedSchool.getSchoolId(), savedStudent.getFirstName());
        assertThat(foundStudent.getId()).isEqualTo(savedStudent.getStudentId());
        assertThat(foundStudent).isNotNull();
    }

    @Test
    public void testThatSchoolCanFindAllStudents() {
        FindAllStudentRequest findAllStudentRequest = FindAllStudentRequest.builder()
                .limit(1)
                .page(1)
                .schoolId(savedSchool.getSchoolId())
                .build();
        List<Student> allStudent = schoolService.findAllStudents(findAllStudentRequest.getLimit(), findAllStudentRequest.getPage(), savedSchool.getSchoolId());
        assertEquals("Eunice", allStudent.get(0).getStudentFirstName());
        assertEquals("Demilade", allStudent.get(0).getStudentLastName());
        assertEquals("DemmyOlyns@gmail.com", allStudent.get(0).getEmail());
        assertEquals(1, schoolService.sizeOfStudents());

    }

    @Test
    public void testThatSchoolCanDeleteAllStudents() {
        String deleteStudent = schoolService.deleteAllStudents();
        assertEquals(0, schoolService.sizeOfStudents());

    }

    @Test
    public void testThatSchoolCanDeleteStudentById() {
        String deletedStudent = schoolService.deleteStudentById(savedSchool.getSchoolId(), savedStudent.getStudentId());
        assertEquals("Student successfully deleted", deletedStudent);
        assertEquals(0, schoolService.sizeOfStudents());


    }

    @Test
    public void testThatSchoolCanUpdateStudentProfiles() {
        UpdatedStudentProfileRequest updatedStudentProfileRequest = UpdatedStudentProfileRequest
                .builder()
                .studentFirstName("Ololade")
                .studentLastName("Temidayo")
                .studentGender("Male")
                .schoolId(savedSchool.getSchoolId())
                .studentId(savedStudent.getStudentId())
                .emailAddress("Olyns@gmail.com")
                .studentAge("80")

                .build();

        UpdateStudentProfileResponse response = schoolService.updateStudentProfile(updatedStudentProfileRequest);
        assertEquals("Student profile successfully updated", response.getMessage());
        assertEquals("Ololade", response.getFirstName());
        assertThat(response.getId()).isNotNull();
    }

    @Test
    public void testThatSchoolCreateCourse() {
        CreateCourseRequest createCourseRequest = CreateCourseRequest.builder()
                .courseTitle("Introduction To Python")
                .courseCode("pyth101")
                .courseName("Python")
                .courseStatus(CourseStatus.ACTIVATED)
                .schoolId(savedSchool.getSchoolId())
                .build();
        CreateCourseResponse createdCourse = schoolService.createCourse(createCourseRequest);
        assertEquals("Python", createdCourse.getCourseName());
        System.out.println(createdCourse);
    }

    @Test
    public void testThatSchoolFindCourseById() {
        Course foundCourse = schoolService.findCourseById(createdCourse.getCourseId(), savedSchool.getSchoolId());
        assertThat(foundCourse.getId()).isEqualTo(createdCourse.getCourseId());
        assertThat(foundCourse.getId()).isNotNull();
    }

    @Test
    public void testThatSchoolFindCourseByName() {
        Course foundCourse = schoolService.findCourseByName(createdCourse.getCourseName(), savedSchool.getSchoolId());
        assertThat(foundCourse.getCourseName()).isEqualTo(createdCourse.getCourseName());
        assertThat(foundCourse.getCourseName()).isNotNull();


    }

    @Test
    public void testThatSchoolFindCourseByCourseCode() {
        Course foundCourse = schoolService.findCourseByCourseCode(createdCourse.getCourseCode(), savedSchool.getSchoolId());
        assertThat(foundCourse.getCourseCode()).isEqualTo(createdCourse.getCourseCode());
        assertThat(foundCourse.getCourseCode()).isNotNull();


    }

    @Test
    public void testThatSchoolFindCourseByCourseTitle() {
        Course foundCourse = schoolService.findCourseByCourseTitle(createdCourse.getCourseTitle(), savedSchool.getSchoolId());
        assertThat(foundCourse.getCourseTitle()).isEqualTo(createdCourse.getCourseTitle());
        assertThat(foundCourse.getCourseTitle()).isNotNull();


    }

    @Test
    public void testThatSchoolCanFindAllCourses() {
        FindAllCourseRequest findAllCourseRequest = FindAllCourseRequest.builder()
                .limit(1)
                .page(1)
                .schoolId(savedSchool.getSchoolId())
                .build();
        List<Course> allCourses = schoolService.findAllCourses(findAllCourseRequest.getLimit(), findAllCourseRequest.getPage(), savedSchool.getSchoolId());
        assertEquals("Python", allCourses.get(0).getCourseName());
        assertEquals(CourseStatus.ACTIVATED, allCourses.get(0).getCourseStatus());
        assertEquals("Introduction To Python", allCourses.get(0).getCourseTitle());
        assertEquals("pyth101", allCourses.get(0).getCourseCode());


    }

    @Test
    public void testThatSchoolCanDeleteAllCourses() {
        String deleteCourses = schoolService.deleteAllCourses();
        assertEquals(0, schoolService.sizeOfCourses());
        assertEquals("All Courses successfully deleted", deleteCourses);

    }


    @Test
    public void testThatSchoolCanDeleteCourseById() {
        String deletedCourse = schoolService.deleteCourseById(savedSchool.getSchoolId(), createdCourse.getCourseId());
        assertEquals("Course successfully deleted", deletedCourse);
        assertEquals(0, schoolService.sizeOfCourses());
    }


    @Test
    public void testThatSchoolCanUpdateCourses() {
        UpdateCourseRequest updateCourseRequest = UpdateCourseRequest
                .builder()
                .courseName("Javascript")
                .courseCode("106")
                .courseTitle("Introduction To Javascript")
                .courseStatus(CourseStatus.DISACTIVATED)
                .courseId(createdCourse.getCourseId())
                .schoolId(savedSchool.getSchoolId())
                .build();
        UpdateCourseResponse updateCourseResponse = schoolService.updateCourseProfile(updateCourseRequest);
        assertEquals("Course profile successfully updated", updateCourseResponse.getMessage());
    }

    @Test
    public void disactivateCourse(){
        String disactivateCourse = schoolService.disactivateCourse(createdCourse.getCourseName(), savedSchool.getSchoolId());
        assertEquals("Course successfully disactivated", disactivateCourse);
    }

    @Test
    public void activateCourse(){
        String activateCourse = schoolService.activateCourse(createdCourse.getCourseName(), savedSchool.getSchoolId());
        assertEquals("Course successfully activated", activateCourse);
    }

    @Test
    public void findAllActivatedCourse(){
        List<Course> foundCourse = schoolService.findAllActivatedCourse(savedSchool.getSchoolId());
        assertThat(foundCourse).isNotNull();
        assertNotNull(foundCourse);

    }

    @Test
    public void findAllDisactivatedCourse(){
        List<Course> foundCourse = schoolService.findAllDisactivatedCourse(savedSchool.getSchoolId());
        assertThat(foundCourse).isNull();
        assertNull(foundCourse);

    }

}







