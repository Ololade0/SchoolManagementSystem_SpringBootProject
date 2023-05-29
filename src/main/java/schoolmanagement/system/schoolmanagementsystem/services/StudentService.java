package schoolmanagement.system.schoolmanagementsystem.services;


import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Student;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.AdmitStudentRequest;
import schoolmanagement.system.schoolmanagementsystem.dao.dto.request.UpdatedStudentProfileRequest;

import java.util.List;

public interface StudentService {

    Student admitstudent(AdmitStudentRequest admitStudentRequest);

    long size();

    Student findStudentById(String id);

    String deleteAll();


    Student findStudentByEmail(String email);

    Student findStudentByName(String studentFirstName);

    String deleteStudentById(String id);

    Student updateStudentProfile(UpdatedStudentProfileRequest updatedProfileRequest);

    List<Student> findAllStudent(int page, int limit);
}
