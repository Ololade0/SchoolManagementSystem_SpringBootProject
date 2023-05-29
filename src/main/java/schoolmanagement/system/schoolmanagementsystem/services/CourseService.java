package schoolmanagement.system.schoolmanagementsystem.services;

import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Course;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.CreateCourseRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.UpdateCourseRequest;

import java.util.List;

public interface CourseService {
    Course createCourse(CreateCourseRequest createCourseRequest);

    String deleteAll();

    long size();

    Course findCourseById(String id);

    Course findCourseByName(String courseName);

    Course findCourseByCode(String courseCode);

    Course findCourseByTitle(String courseTitle);

    List<Course> findAllCourses(int limit, int page);

    String deleteById(String courseId);


    Course updateCourseProfile(UpdateCourseRequest updateCourseRequest, String courseId);


    List<Course> findAllDisactivatedCourse();

    List<Course> findAllActivatedCourse();

    String activateCourse(String courseName);

    String disactivateCourse(String courseName);
}
