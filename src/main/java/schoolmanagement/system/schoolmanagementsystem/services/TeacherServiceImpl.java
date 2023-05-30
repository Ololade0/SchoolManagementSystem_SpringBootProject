package schoolmanagement.system.schoolmanagementsystem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Teacher;
import schoolmanagement.system.schoolmanagementsystem.dao.data.repository.TeacherRepository;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepository teacherRepository;
    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher findTeacherById(String id) {
        return null;
    }

    @Override
    public Teacher findTeacherByName(String firstName) {
        return null;
    }
}
