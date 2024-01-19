// Importing necessary packages
package com.example.entity;
import javax.persistence.*;


// Declaring the entity for the 'customers' table
@Entity
@Table(name = "customers")
public class Customer {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    // Name of the customer
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    // Email of the customer (unique constraint)
    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    // Phone number of the customer
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    // Default constructor
    public Customer() {
        super();
    }

    // Parameterized constructor without 'id'
    public Customer(String name, String email, String phoneNumber) {
        super();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter methods for all fields

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Overriding toString method for a readable representation of the Customer object
    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
    }

}
