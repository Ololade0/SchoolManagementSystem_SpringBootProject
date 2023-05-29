package schoolmanagement.system.schoolmanagementsystem.dao.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document("student")
public class Student {
    @Id
    private String id;
    private String studentFirstName;
    private String studentLastName;
    private String gender;
    private String studentAge;
    private String email;
    private String password;
    @DBRef
    private List<Course> courses = new ArrayList<>();


}
