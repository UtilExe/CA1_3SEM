package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "Car.deleteAllRows", query = "DELETE from Car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name="year", nullable = false)
    private String year;
    @Column(name="make", nullable = false)
    private String make;
    @Column(name="model", nullable = false)
    private String model;
    @Column(name="price", nullable = false)
    private double price;
    @Column(name="created")
    @Temporal(TemporalType.DATE)
    private Date created;
    @Column(name="owner", nullable = false)
    private String owner;

    public Car() {
    }

    public Car(String year, String make, String model, double price, Date created, String owner) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.price = price;
        this.created = created;
        this.owner = owner;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   
    
        
}

