// Importing necessary packages
package com.example.repository;

import com.example.entity.Store;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

// Repository class for managing Store entities
public class StoreRepository {

    // Entity manager for interacting with the persistence context
    private final EntityManager entityManager;

    // Constructor to initialize the repository with an entity manager
    public StoreRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Method to save a new store to the database
    public void save(Store store) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(store);
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction in case of an exception
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    // Method to find a store by its ID
    public Store findById(long id) {
        return entityManager.find(Store.class, id);
    }

    // Method to retrieve all stores from the database
    public List<Store> findAll() {
        return entityManager.createQuery("SELECT s FROM Store s", Store.class).getResultList();
    }

    // Method to update the information of an existing store
    public void update(Store store) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.merge(store);
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction in case of an exception
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    // Method to delete a store from the database
    public void delete(Store store) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.remove(store);
            transaction.commit();
        } catch (Exception e) {
            // Rollback the transaction in case of an exception
            if( transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }
}
