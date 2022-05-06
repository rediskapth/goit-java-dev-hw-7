package ua.goit.model.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class CustomersDao {
    private Integer customerId;
    private String name;
    private String location;

    public CustomersDao(Integer customerId, String name, String location) {
        this.customerId = customerId;
        this.name = name;
        this.location = location;
    }

    public CustomersDao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "CustomersDao{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
