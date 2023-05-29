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

    School findSchoolById(String id);

    List<School> findAllSchool(int page, int limit);

    String deleteById(String id);

    String deleteAllSchools();

    School updateSchoolProfile(UpdateSchoolProfileRequest updateSchoolProfileRequest);

    AdmitStudentResponse admitStudent(AdmitStudentRequest admitStudentRequest);

    long sizeOfStudents();

    Student findStudentById(String schoolId, String studentId);

    Student findStudentByName(String schoolId, String firstName);

    String deleteAllStudents();

   List<Student>findAllStudents(int limit, int page, String schoolId);

    String deleteStudentById(String schoolId, String studentId);

    UpdateStudentProfileResponse updateStudentProfile(UpdatedStudentProfileRequest updatedStudentProfileRequest);

    CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest);

    Course findCourseById(String courseId, String schoolId);

    Course findCourseByName(String courseName, String schoolId);

    Course findCourseByCourseCode(String courseCode, String schoolId);

    Course findCourseByCourseTitle(String courseTitle, String schoolId);

    List<Course> findAllCourses(int limit, int page, String schoolId);

    String deleteAllCourses();

    long sizeOfCourses();

    String deleteCourseById(String schoolId, String courseId);


    UpdateCourseResponse updateCourseProfile(UpdateCourseRequest updateCourseRequest);


    String disactivateCourse(String courseName, String schoolId);

    String activateCourse(String courseName, String schoolId);

    List<Course> findAllActivatedCourse(String schoolId);

    List<Course> findAllDisactivatedCourse(String schoolId);
}
