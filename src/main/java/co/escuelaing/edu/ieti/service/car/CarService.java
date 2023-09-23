package co.escuelaing.edu.ieti.service.car;

import co.escuelaing.edu.ieti.repository.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Car save(Car car);

    Optional<Car> findById(String id);

    List<Car> getAllCars();

    void deleteById(String id);

    void update(String id, Car car);
}
