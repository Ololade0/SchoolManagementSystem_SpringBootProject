package schoolmanagement.system.schoolmanagementsystem.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Student;

import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.AdmitStudentRequest;

import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.FindAllStudentRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.UpdatedStudentProfileRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudentServiceImplTest {
    @Autowired
    private StudentService studentService;
    Student savedStudent;


    @AfterEach
    void tearDown() {
        studentService.deleteAll();
//        studentService.deleteAllCourses();

    }

    @BeforeEach
    void setUp() {
        AdmitStudentRequest admitStudentRequest = AdmitStudentRequest.builder()
                .studentFirstName("Adesuyi")
                .studentLastName("Ololade")
                .emailAddress("Ololade@gmail.com")
                .password("1234")
                .studentAge("20")
                .gender("FEMALE")
                .build();
        savedStudent =   studentService.admitstudent(admitStudentRequest);


    }


    @Test
    public  void testThatStudentCanBeCreated(){
        Student student = Student.builder()
                .studentFirstName("Adesuyi")
                .studentLastName("Ololade")
                .email("Ololade@gmail.com")
                .studentAge("20")
                .gender("Female")
                .build();
        assertEquals("Adesuyi" , student.getStudentFirstName());
    }
    @Test
    public  void testThatStudentCanBeAdmited(){
        AdmitStudentRequest admitStudentRequest = AdmitStudentRequest.builder()
                .studentFirstName("Adesuyi")
                .studentLastName("Ololade")
                .emailAddress("Ololade@gmail.com")
                .studentAge("20")
                .gender("Female")
                .build();
        studentService.admitstudent(admitStudentRequest);
        assertEquals(2, studentService.size());
    }

    @Test
    public  void testThatStudentCanBeFindById(){
        Student foundStudent =  studentService.findStudentById(savedStudent.getId());
        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.getId()).isEqualTo(savedStudent.getId());
    }
    @Test
    public  void testThatStudentCanBeFindByEmail(){
        Student foundStudent =  studentService.findStudentByEmail(savedStudent.getEmail());
        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.getId()).isEqualTo(savedStudent.getId());
    }

    @Test
    public  void testThatStudentCanBeFindByName(){
        Student foundStudent =  studentService.findStudentByName(savedStudent.getStudentFirstName());
        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.getId()).isEqualTo(savedStudent.getId());
    }
    @Test
    public void findAllAdmittedStudent(){
        FindAllStudentRequest findAllStudentRequest = FindAllStudentRequest.builder()
                .page(1)
                .limit(1)
                .build();
        List<Student> studentList = studentService.findAllStudent(findAllStudentRequest.getPage(), findAllStudentRequest.getLimit());
        assertEquals("Adesuyi", studentList.get(0).getStudentFirstName());
        assertEquals("Ololade", studentList.get(0).getStudentLastName());
        assertEquals("Ololade@gmail.com", studentList.get(0).getEmail());

    }

    @Test
    public  void testThatAllStudentCanBeDeleted(){
        studentService.deleteAll();
        assertEquals(0, studentService.size());
    }


    @Test
    public void testThatStudentCanBeDeletedById(){
        studentService.deleteStudentById(savedStudent.getId());
        assertEquals(0, studentService.size());
    }
    @Test
    public void testThatStudentCanBeUpdated(){
        UpdatedStudentProfileRequest updatedProfileRequest = UpdatedStudentProfileRequest.builder()
                .studentFirstName("Eunice")
                .studentId(savedStudent.getId())
                .studentLastName("Demilade")

                .emailAddress("DemmyOlyns@gmail.com")
                .studentAge("30")
                .studentGender("male")
                .build();
        Student updatedStudent =studentService.updateStudentProfile(updatedProfileRequest);
        assertEquals("Demilade",updatedStudent.getStudentLastName());
        assertEquals("Eunice", updatedStudent.getStudentFirstName());
        assertEquals("30", updatedStudent.getStudentAge());
        assertEquals("male", updatedStudent.getGender());
        assertEquals("DemmyOlyns@gmail.com", updatedStudent.getEmail());


    }

//    @Test
//    public void testThatStudentCanLogin() {
//        LoginRest loginRest = new LoginRest();
//        loginRest.setPassword(savedStudent.getPassword());
//        loginRest.setEmail(savedStudent.getEmail());
//        var email = studentService.login(loginRest);
//        assertEquals(200, email.getCode());
//        assertEquals("Login successful", email.getMessage());
//    }





}
