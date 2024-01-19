package com.project.GroceryStoreManagement;

import com.example.entity.Category;
import com.example.entity.Customer;
import com.example.entity.GroceryItem;
import com.example.entity.Store;
import com.example.repository.CategoryRepository;
import com.example.repository.CustomerRepository;
import com.example.repository.GroceryItemRepository;
import com.example.repository.StoreRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class App {

    public static void main(String[] args) {
        System.out.println("_GROCERY MANAGEMENT SYSTEM__");
        // Creating an EntityManagerFactory for the persistence unit "GroceryStoreManagement"
        // This factory is responsible for creating EntityManager instances.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GroceryStoreManagement");

        // Creating an EntityManager instance to interact with the persistence context
        // EntityManager is used for database operations such as storing and retrieving entities
        EntityManager em = emf.createEntityManager();

        try {
            // Example usage of CategoryRepository
            CategoryRepository categoryRepository = new CategoryRepository(em);

            // Create a few categories
            Category fruitsCategory = new Category("Fruits");
            Category vegetablesCategory = new Category("Vegetables");
            Category dairyCategory = new Category("Dairy");
            //save categories
            categoryRepository.save(fruitsCategory);
            categoryRepository.save(vegetablesCategory);
            categoryRepository.save(dairyCategory);

            // Example usage of StoreRepository
            StoreRepository storeRepository = new StoreRepository(em);

            // Save a few stores with locations
            Store localGrocery = new Store("Local Grocery", "ChakBazar");
            Store organicMart = new Store("Organic Mart", "Boltala");
            // create a few stores with locations
            storeRepository.save(localGrocery);
            storeRepository.save(organicMart);

            // Example usage of CustomerRepository
            CustomerRepository customerRepository = new CustomerRepository(em);

            // Create a few customers with grocery items
            Customer customer1 = new Customer("Purnima Mahato", "purnimamahato@gmail.com", "1234567890");
            Customer customer2 = new Customer("Rahul Gorai", "rahulgorai@gmail.com", "9876543210");

            // Save customers
            customerRepository.save(customer1);
            customerRepository.save(customer2);

            // Example usage of GroceryItemRepository
            GroceryItemRepository groceryItemRepository = new GroceryItemRepository(em);

            // Create a few grocery items using the categories, stores, and customers we just saved
            GroceryItem appleItem = new GroceryItem("Apple", 50.0, fruitsCategory, new HashSet<>(Arrays.asList(localGrocery)), localGrocery.getId(), customer1);
            GroceryItem bananaItem = new GroceryItem("Banana", 35.0, fruitsCategory, new HashSet<>(Arrays.asList(localGrocery)), localGrocery.getId(), customer2);
            GroceryItem carrotItem = new GroceryItem("Carrot", 20.0, vegetablesCategory, new HashSet<>(Arrays.asList(localGrocery)), localGrocery.getId(), customer1);
            GroceryItem milkItem = new GroceryItem("Milk", 25.0, dairyCategory, new HashSet<>(Arrays.asList(organicMart)), organicMart.getId(), customer2);

            // Save grocery items
            groceryItemRepository.save(appleItem);
            groceryItemRepository.save(bananaItem);
            groceryItemRepository.save(carrotItem);
            groceryItemRepository.save(milkItem);

            // Fetch all categories
            List<Category> allCategories = categoryRepository.findAll();
            System.out.println("All Categories: " + allCategories);

            // Fetch all grocery items
            List<GroceryItem> allItems = groceryItemRepository.findAll();
            System.out.println("All GroceryItems: " + allItems);

            // Fetch all stores
            List<Store> allStores = storeRepository.findAll();
            System.out.println("All Stores: " + allStores);

            // Update a grocery item
            appleItem.setPrice(60.0);
            groceryItemRepository.update(appleItem);

            // Fetch all grocery items after update
            allItems = groceryItemRepository.findAll();
            System.out.println("All GroceryItems after update: " + allItems);

            // Delete a grocery item
            groceryItemRepository.delete(carrotItem);

            // Fetch all grocery items after delete
            allItems = groceryItemRepository.findAll();
            System.out.println("All GroceryItems after delete: " + allItems);

        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        } finally {
            //closing the objects
            em.close();
            emf.close();
        }
    }
}
