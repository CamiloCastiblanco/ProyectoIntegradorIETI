package co.escuelaing.edu.ieti.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CarNotFoundException extends ResponseStatusException {
    public CarNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "car with ID: " + id + " not found");
    }
}