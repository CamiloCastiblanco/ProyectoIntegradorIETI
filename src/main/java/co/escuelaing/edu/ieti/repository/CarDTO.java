package co.escuelaing.edu.ieti.repository;

public class CarDTO {
    private String id;
    private String make;

    public String getId() {
        return id;
    }

    private String model;
    private int year;
    private double rentalPricePerHour;

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


    // getters y setters
    public void setId(String id) {
        this.id = id;
    }
}

