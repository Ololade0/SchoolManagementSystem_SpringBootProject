package schoolmanagement.system.schoolmanagementsystem.dao.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
@Document("School")
@NoArgsConstructor
public class School {
    @Id
    private java.lang.String id;
    private java.lang.String schoolName;
    private java.lang.String schoolLocation;
    private java.lang.String email;
    @DBRef
    private List<Student> students = new ArrayList<>();

    @DBRef
    private List<Course> courses = new ArrayList<>();

    @DBRef
    private List<Teacher> teachers;

    @DBRef
    private Set<Role> roleHashSet;

    public School(java.lang.String id, java.lang.String schoolName, java.lang.String schoolLocation, java.lang.String email,

                  RoleType roleType) {
        this.id = id;
        this.schoolName = schoolName;
        this.schoolLocation = schoolLocation;
        this.email = email;
        if (roleHashSet == null) {
            roleHashSet = new HashSet<>();
            roleHashSet.add(new Role(roleType));


        }

    }
}
