// Importing necessary packages
package com.example.repository;

import com.example.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

// Repository class for managing Category entities
public class CategoryRepository {

    // Entity manager for interacting with the persistence context
    private final EntityManager entityManager;

    // Constructor to initialize the repository with an entity manager
    public CategoryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Method to save a new category to the database
    public void save(Category category) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(category);
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

    // Method to find a category by its ID
    public Category findById(long id) {
        return entityManager.find(Category.class, id);
    }

    // Method to retrieve all categories from the database
    public List<Category> findAll() {
        return entityManager.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    // Method to update the information of an existing category
    public void update(Category category) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.merge(category);
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

    // Method to delete a category from the database
    public void delete(Category category) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.remove(category);
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
}
