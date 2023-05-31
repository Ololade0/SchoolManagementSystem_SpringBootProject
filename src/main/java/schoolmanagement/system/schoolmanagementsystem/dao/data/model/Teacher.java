package schoolmanagement.system.schoolmanagementsystem.dao.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.enums.Gender;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
@NoArgsConstructor
@Document("Teacher")
public class Teacher {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String email;
//    private String password;
//    private School school;
    @DBRef
    private Set<Role> roles;
//
//    @DBRef
//    private List<Course> courses;
}
