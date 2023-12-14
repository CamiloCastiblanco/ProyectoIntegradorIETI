package co.escuelaing.edu.ieti.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import co.escuelaing.edu.ieti.repository.User;
import java.util.Optional;

@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {
    Optional<User> findUserByEmail(String email);
}
