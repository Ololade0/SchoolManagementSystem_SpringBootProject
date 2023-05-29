package schoolmanagement.system.schoolmanagementsystem.dao.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterSchoolResponse {
    private String schoolId;
    private String message;
    private String schoolName;
    private String schoolLocation;
    private String courseId;
    private String email;




}


