package schoolmanagement.system.schoolmanagementsystem.services;

import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher addTeacher(Teacher teacher);

    Teacher findTeacherById(String id);

    Teacher findTeacherByName(String firstName);

    List<Teacher> findAllTeachers(int limit, int page);
}
