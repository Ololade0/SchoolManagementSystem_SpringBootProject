package schoolmanagement.system.schoolmanagementsystem.dao.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    private java.lang.String id;
    private java.lang.String courseName;
    private java.lang.String courseTitle;
    private java.lang.String courseCode;
    private CourseStatus courseStatus;
    @DBRef
    private Teacher teacher;




}
