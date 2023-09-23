package co.escuelaing.edu.ieti.service.car;
import co.escuelaing.edu.ieti.repository.Car;
import co.escuelaing.edu.ieti.repository.CarMongoRepository;
import co.escuelaing.edu.ieti.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceMongoDb implements CarService {
    private final CarMongoRepository carMongoRepository;

    @Autowired
    public CarServiceMongoDb(CarMongoRepository carMongoRepository) {
        this.carMongoRepository = carMongoRepository;
    }

    public List<Car> getAllCars() {
        return carMongoRepository.findAll();
    }

    public Optional<Car> findById(String id) {
        return carMongoRepository.findById(id);
    }

    public Car save(Car car) {
        return carMongoRepository.save(car);
    }

    public void update(String id, Car updatedCar) {
        if (carMongoRepository.existsById(id)) {
            updatedCar.setId(id);
            carMongoRepository.save(updatedCar);
        }
    }

    public void deleteById(String id) {
        carMongoRepository.deleteById(id);
    }
}
