package schoolmanagement.system.schoolmanagementsystem.services;

import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Teacher;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.EmployTeacherRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.UpdatedTeacherProfileRequest;

import java.util.List;

public interface TeacherService {
    Teacher addTeacher(EmployTeacherRequest employTeacherRequest);

    Teacher findTeacherById(java.lang.String id);

    Teacher findTeacherByName(java.lang.String firstName);

    List<Teacher> findAllTeachers(int limit, int page);

    java.lang.String deleteAll();

    long size();



    java.lang.String deleteTeachersById(java.lang.String id);

    Teacher updateTeachersProfile(UpdatedTeacherProfileRequest updatedProfileRequest);
}
