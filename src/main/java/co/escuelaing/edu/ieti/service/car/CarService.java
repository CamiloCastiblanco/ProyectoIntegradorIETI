package co.escuelaing.edu.ieti.service.car;

import co.escuelaing.edu.ieti.repository.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Car save(Car car);

    Optional<Car> findById(String id);

    List<Car> all();

    void deleteById(String id);

    Car update(Car car, String id);
}
