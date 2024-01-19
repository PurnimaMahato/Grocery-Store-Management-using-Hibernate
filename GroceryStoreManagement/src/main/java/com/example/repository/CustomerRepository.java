// Importing necessary packages
package com.example.repository;

import com.example.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

// Repository class to manage operations related to the 'customers' table
public class CustomerRepository {

    // EntityManager for handling JPA operations
    private final EntityManager entityManager;

    // Constructor to inject the EntityManager
    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Method to save a new customer
    public void save(Customer customer) {
        // Initiating a transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            // Persisting the customer entity
            entityManager.persist(customer);
            // Committing the transaction
            transaction.commit();
        } catch (Exception e) {
            // Handling exceptions and rolling back the transaction if needed
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    // Method to find a customer by their ID
    public Customer findById(long id) {
        return entityManager.find(Customer.class, id);
    }

    // Method to retrieve all customers from the 'customers' table
    public List<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    // Method to update an existing customer
    public void update(Customer customer) {
        // Initiating a transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            // Merging changes to the customer entity
            entityManager.merge(customer);
            // Committing the transaction
            transaction.commit();
        } catch (Exception e) {
            // Handling exceptions and rolling back the transaction if needed
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    // Method to delete an existing customer
    public void delete(Customer customer) {
        // Initiating a transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            // Removing the customer entity
            entityManager.remove(customer);
            // Committing the transaction
            transaction.commit();
        } catch (Exception e) {
            // Handling exceptions and rolling back the transaction if needed
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }
}
