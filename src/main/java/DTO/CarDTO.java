
package DTO;

import entities.Car;

public class CarDTO {
    
    private Long id;
    private String year;
    private String make;
    private String model;
    private double price;

    public CarDTO(Car car) {
        this.id = car.getId();
        this.year = car.getYear();
        this.make = car.getMake();
        this.model = car.getModel();
        this.price = car.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

   
    
    
    
}
