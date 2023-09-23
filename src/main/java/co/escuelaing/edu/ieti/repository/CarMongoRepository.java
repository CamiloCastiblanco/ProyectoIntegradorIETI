package co.escuelaing.edu.ieti.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface CarMongoRepository extends MongoRepository<Car, String> {

}