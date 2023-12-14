package co.escuelaing.edu.ieti.controller;
import co.escuelaing.edu.ieti.exception.CarNotFoundException;
import co.escuelaing.edu.ieti.exception.UserNotFoundException;
import co.escuelaing.edu.ieti.repository.Car;
import co.escuelaing.edu.ieti.repository.CarDTO;

import co.escuelaing.edu.ieti.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/v1/cars")
public class CarController {
    @Autowired
    private  CarService carService;       
    

    @GetMapping("/")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.all();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable("id") String id) {
        Car carFound = carService.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        return ResponseEntity.ok(carFound);
    }

    @PostMapping("/")
    public ResponseEntity<Car> createCar(@RequestBody CarDTO carDTO) {
        Car carCreated = carService.save(new Car(carDTO));
        URI createdCarUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(carCreated.getId())
                .toUri();;
        return ResponseEntity.created(createdCarUri).body(carCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") String id, @RequestBody CarDTO carDTO) {
        Car carToUpdate = carService.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        Car carUpdated = carService.update(new Car(carDTO), id);
        carService.save(carToUpdate);
        return ResponseEntity.ok(carUpdated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        carService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        carService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
