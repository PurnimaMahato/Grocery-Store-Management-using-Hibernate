// GroceryItemRepository.java
package com.example.repository;

import com.example.entity.GroceryItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class GroceryItemRepository {

    private final EntityManager entityManager;

    public GroceryItemRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(GroceryItem groceryItem) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(groceryItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    public GroceryItem findById(long id) {
        return entityManager.find(GroceryItem.class, id);
    }

    public List<GroceryItem> findAll() {
        return entityManager.createQuery("SELECT g FROM GroceryItem g", GroceryItem.class).getResultList();
    }

    public void update(GroceryItem groceryItem) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.merge(groceryItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    public void delete(GroceryItem groceryItem) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.remove(groceryItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }
}
