package co.escuelaing.edu.ieti.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cars")
public class Car {
    @Id
    private String id;
    private String make;

    public Car(String make, String model, String id, int year, double rentalPricePerDay, double rentalPricePerHour) {
        this();
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.rentalPricePerDay = rentalPricePerDay;
        this.rentalPricePerHour = rentalPricePerHour;
    }
    public void update(CarDTO carDTO) {
        id = carDTO.getId();
        make = carDTO.getMake();
        model = carDTO.getModel();
        year = carDTO.getYear();
        rentalPricePerHour = carDTO.getRentalPricePerHour();
        rentalPricePerDay = carDTO.getRentalPricePerDay();
    }

    public Car() {

    }

    public String getId() {
        return id;
    }

    private String model;
    private int year;
    private double rentalPricePerHour;

    public Car(CarDTO dto) {
        this(dto.getMake(), dto.getModel(), dto.getId(), dto.getYear(), dto.getRentalPricePerDay(), dto.getRentalPricePerHour());
    }
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public double getRentalPricePerHour() {
        return rentalPricePerHour;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(double rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public void setRentalPricePerHour(double rentalPricePerHour) {
        this.rentalPricePerHour = rentalPricePerHour;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    private double rentalPricePerDay;


    public void setId(String id) {
        this.id = id;
    }
}

