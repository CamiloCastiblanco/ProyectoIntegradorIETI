package co.escuelaing.edu.ieti.service.car;
import co.escuelaing.edu.ieti.repository.Car;
import co.escuelaing.edu.ieti.repository.CarMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceMongoDb implements CarService {
    @Autowired
    private CarMongoRepository carMongoRepository;   
    

    public List<Car> all() {
        return carMongoRepository.findAll();
    }

    public Optional<Car> findById(String id) {
        return carMongoRepository.findById(id);
    }

    public Car save(Car car) {
        return carMongoRepository.save(car);
    }    

    public void deleteById(String id) {
        carMongoRepository.deleteById(id);
    }

    @Override
    public Car update(Car car, String id) {
        if (carMongoRepository.existsById(id)) {
            car.setId(id);
            carMongoRepository.save(car);
        }
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
