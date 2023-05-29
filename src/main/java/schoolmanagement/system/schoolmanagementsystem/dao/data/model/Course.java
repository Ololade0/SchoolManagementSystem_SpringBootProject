package schoolmanagement.system.schoolmanagementsystem.dao.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.CourseStatus;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document("course")
public class Course {
    @Id
    private String id;
    private String courseName;
    private String courseTitle;
    private String courseCode;
    private CourseStatus courseStatus;




}
