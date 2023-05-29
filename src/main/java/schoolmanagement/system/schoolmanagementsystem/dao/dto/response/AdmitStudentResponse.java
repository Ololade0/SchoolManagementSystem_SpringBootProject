package schoolmanagement.system.schoolmanagementsystem.dao.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdmitStudentResponse {
    private String schoolName;
    private String firstName;
    private String message;
    private String studentId;
}
