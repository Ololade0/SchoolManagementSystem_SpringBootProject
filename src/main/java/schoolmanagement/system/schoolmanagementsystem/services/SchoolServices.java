package schoolmanagement.system.schoolmanagementsystem.services;

import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Course;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.School;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Student;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.*;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.response.*;

import java.util.List;

public interface SchoolServices  {
    RegisterSchoolResponse registerSchool(RegisterSchoolRequest registerSchoolRequest) ;


    long size();

    School findSchoolById(java.lang.String id);

    List<School> findAllSchool(int page, int limit);

    java.lang.String deleteById(java.lang.String id);

    java.lang.String deleteAllSchools();

    School updateSchoolProfile(UpdateSchoolProfileRequest updateSchoolProfileRequest);

    AdmitStudentResponse admitStudent(AdmitStudentRequest admitStudentRequest);

    long sizeOfStudents();

    Student findStudentById(java.lang.String schoolId, java.lang.String studentId);

    Student findStudentByName(java.lang.String schoolId, java.lang.String firstName);

    java.lang.String deleteAllStudents();

   List<Student>findAllStudents(int limit, int page, java.lang.String schoolId);

    java.lang.String deleteStudentById(java.lang.String schoolId, java.lang.String studentId);

    UpdateStudentProfileResponse updateStudentProfile(UpdatedStudentProfileRequest updatedStudentProfileRequest);

    CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest);

    Course findCourseById(java.lang.String courseId, java.lang.String schoolId);

    Course findCourseByName(java.lang.String courseName, java.lang.String schoolId);

    Course findCourseByCourseCode(java.lang.String courseCode, java.lang.String schoolId);

    Course findCourseByCourseTitle(java.lang.String courseTitle, java.lang.String schoolId);

    List<Course> findAllCourses(int limit, int page, java.lang.String schoolId);

    java.lang.String deleteAllCourses();

    long sizeOfCourses();

    java.lang.String deleteCourseById(java.lang.String schoolId, java.lang.String courseId);


    UpdateCourseResponse updateCourseProfile(UpdateCourseRequest updateCourseRequest);


    java.lang.String disactivateCourse(java.lang.String courseName, java.lang.String schoolId);

    java.lang.String activateCourse(java.lang.String courseName, java.lang.String schoolId);

    List<Course> findAllActivatedCourse(java.lang.String schoolId);

    List<Course> findAllDisactivatedCourse(java.lang.String schoolId);

    EmployTeacherResponse  employNewTeacherToSchool(EmployTeacherRequest employTeacherRequest, java.lang.String schoolId);

//    Teacher employTeacher(Teacher teacher);
}
