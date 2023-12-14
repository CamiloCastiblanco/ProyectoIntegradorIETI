package co.escuelaing.edu.ieti.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMongoRepository extends MongoRepository<Car, String> {

}