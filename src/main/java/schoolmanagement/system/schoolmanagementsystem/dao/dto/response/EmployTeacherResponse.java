package schoolmanagement.system.schoolmanagementsystem.dao.dto.response;

import lombok.*;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.Gender;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployTeacherResponse {
    private String teacherId;
    private String schoolName;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String email;
}
