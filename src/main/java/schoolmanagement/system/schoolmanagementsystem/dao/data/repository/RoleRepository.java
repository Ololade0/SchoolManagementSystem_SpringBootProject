package schoolmanagement.system.schoolmanagementsystem.dao.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import schoolmanagement.system.schoolmanagementsystem.dao.data.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

}
