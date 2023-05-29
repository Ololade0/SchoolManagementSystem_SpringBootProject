package schoolmanagement.system.schoolmanagementsystem.dao.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
@Document("School")
@NoArgsConstructor
public class School {
    @Id
    private String id;
    private String schoolName;
    private String schoolLocation;
    private String email;
    @DBRef
    private List<Student> students = new ArrayList<>();
@DBRef
    private List<Course> courses = new ArrayList<>();

}
