package co.escuelaing.edu.ieti.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cars")
public class Car {
    @Id
    private String id;
    private String make;
    private String model;
    private int year;
    private double rentalPricePerHour;
    private double rentalPricePerDay;


    // getters y setters
    public void setId(String id) {
        this.id = id;
    }
}

