package schoolmanagement.system.schoolmanagementsystem.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.*;
import schoolmanagement.system.schoolmanagementsystem.dao.data.repository.StudentRepository;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.AdmitStudentRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.EnrollForCourseRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.UpdatedStudentProfileRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.response.AdmitStudentResponse;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.response.EnrollCourseResponse;
import schoolmanagement.system.schoolmanagementsystem.exception.SchoolDoesExistException;
import schoolmanagement.system.schoolmanagementsystem.exception.StudentDoesNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;



    @Override
    public Student admitstudent(AdmitStudentRequest admitStudentRequest) {
//        if (studentRepository.findStudentByEmail(admitStudentRequest.getEmailAddress()).isPresent()) {
//            throw new StudentAlreadyExistException("Student with " + admitStudentRequest.getEmailAddress() + "already exist");
//        }
//        else
//        {
            Student newStudent = Student.builder()
                    .studentFirstName(admitStudentRequest.getStudentFirstName())
                    .studentLastName(admitStudentRequest.getStudentLastName())
                    .password(admitStudentRequest.getPassword())
                    .studentAge(admitStudentRequest.getStudentAge())
                    .email(admitStudentRequest.getEmailAddress())
                    .gender(admitStudentRequest.getGender())
                    .build();
//            newStudent.setId(newStudent.getId());
            return studentRepository.save(newStudent);

        }

    @Override
    public long size() {

        return studentRepository.count();
    }

    @Override
    public Student findStudentById(String id) {
        Optional<Student> student = studentRepository.findStudentById(id);
        if (student.isPresent()) {
            return studentRepository.findById(id).orElseThrow(
                    () -> new StudentDoesNotExistException(StudentDoesNotExistException.StudentDoesNotExist(id))
            );
        }
        return null;

    }




    @Override
    public Student findStudentByEmail(String email) {
        Optional<Student> student = studentRepository.findStudentByEmail(email);
        if (student.isPresent()) {
            return studentRepository.findStudentByEmail(email).orElseThrow(
                    () -> new SchoolDoesExistException(StudentDoesNotExistException.StudentDoestNotExist(email))
            );
        }
        return null;

    }

    @Override
    public Student findStudentByName(String studentFirstName) {
        Optional<Student> student = studentRepository.findStudentByStudentFirstName(studentFirstName);
        if (student.isPresent()) {
            return studentRepository.findStudentByStudentFirstName(studentFirstName).orElseThrow(
                    () -> new SchoolDoesExistException(StudentDoesNotExistException.StudentDoestNotExist(studentFirstName))
            );
        }
        return null;
    }
    @Override
    public List<Student> findAllStudent(int page, int limit) {
        List<Student> students = new ArrayList<>();
        if (page > 0) page -= 1;
        Pageable pageable = PageRequest.of(page, limit);
        Page<Student> students1 = studentRepository.findAll(pageable);
        List<Student> students2 = students1.getContent();
        for (Student student : students2) {
            Student student1 = new Student();
            BeanUtils.copyProperties(student, student1);
            students.add(student1);
        }
        return students;
    }





    @Override
    public String deleteStudentById(String id) {
        Optional<Student> foundStudent = studentRepository.findStudentById(id);
        if (foundStudent.isPresent()) {
            studentRepository.deleteById(id);
            return "Student is Id" + id + " successfully deleted";
        }

        throw new StudentDoesNotExistException(StudentDoesNotExistException.StudentDoestNotExist(id));



    }

    @Override
    public String deleteAll() {
        studentRepository.deleteAll();
        return "Succesfully deleted";

    }

    @Override
    public Student updateStudentProfile(UpdatedStudentProfileRequest updatedProfileRequest) {
        return updateStudent(updatedProfileRequest);

    }

    private Student updateStudent(UpdatedStudentProfileRequest updatedStudentProfileRequest) {
        Optional<Student> foundStudent = studentRepository.findStudentById(updatedStudentProfileRequest.getStudentId());
        if (updatedStudentProfileRequest.getStudentFirstName() != null) {
            foundStudent.get().setStudentFirstName(updatedStudentProfileRequest.getStudentFirstName());
        }

        if (updatedStudentProfileRequest.getStudentLastName() != null) {
            foundStudent.get().setStudentLastName(updatedStudentProfileRequest.getStudentLastName());
        }

        if (updatedStudentProfileRequest.getStudentAge() != null) {
            foundStudent.get().setStudentAge(updatedStudentProfileRequest.getStudentAge());
        }
        if (updatedStudentProfileRequest.getEmailAddress() != null) {
            foundStudent.get().setEmail(updatedStudentProfileRequest.getEmailAddress());
        }

        if (updatedStudentProfileRequest.getStudentGender() != null) {
            foundStudent.get().setGender(updatedStudentProfileRequest.getStudentGender());
        }
      return studentRepository.save(foundStudent.get());



    }




    @Override
    public List<Course> findAllCourses(int limit, int page, String studentId) {
        Optional<Student> foundStudent = studentRepository.findStudentById(studentId);
        if (foundStudent.isPresent()) {
            return courseService.findAllCourses(limit, page);
        }
        throw new StudentDoesNotExistException(StudentDoesNotExistException.StudentDoesNotExist(studentId));
    }



    @Override
    public EnrollCourseResponse studentCanEnrollForCourses(EnrollForCourseRequest enrollForCourseRequest) {
            Course enrolledCourse = courseService.enrollForCourse(enrollForCourseRequest);
            Optional<Student> foundStudent = studentRepository.findStudentById(enrollForCourseRequest.getStudentId());
            if (foundStudent.isPresent() && foundStudent.get().getCourses().contains(enrolledCourse)) {
                foundStudent.get().getCourses().add(enrolledCourse);
            }

            return EnrollCourseResponse
                    .builder()
                    .message("You have successfully register for this course")
                    .courseName(enrolledCourse.getCourseName())
                    .build();
        }

    }








