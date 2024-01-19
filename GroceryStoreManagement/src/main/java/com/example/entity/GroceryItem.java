// Importing necessary packages
package com.example.entity;

// Importing JPA annotations
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Declaring the entity for the 'grocery_items' table
@Entity
@Table(name = "grocery_items")
public class GroceryItem {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    // Name of the grocery item
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    // Price of the grocery item
    @Column(name = "price", nullable = false)
    private double price;

    // Many-to-One relationship with Category entity
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryEntity;

    // Many-to-Many relationship with Store entity using a join table
    @ManyToMany
    @JoinTable(
            name = "store_grocery_items",
            joinColumns = @JoinColumn(name = "grocery_item_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id")
    )
    private Set<Store> stores = new HashSet<>();

    // Explicit column for store_id
    @Column(name = "store_id", nullable = false)
    private Long storeId;

    // Many-to-One relationship with Customer entity
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customerEntity;


    // Default constructor
    public GroceryItem() {
        super();
    }

    // Parameterized constructor without 'id'
    public GroceryItem(String name, double price, Category categoryEntity, Set<Store> stores, Long storeId, Customer customerEntity) {
        this.name = name;
        this.price = price;
        this.categoryEntity = categoryEntity;
        this.stores = stores;
        this.storeId = storeId;
        this.customerEntity = customerEntity;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(Category categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Customer getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(Customer customerEntity) {
        this.customerEntity = customerEntity;
    }

    // Overriding toString method for a readable representation of the GroceryItem object
    @Override
    public String toString() {
        return "GroceryItem [id=" + id + ", name=" + name + ", price=" + price +
                ", categoryEntity=" + categoryEntity.getName() +
                ", stores=" + stores +
                ", storeId=" + storeId +
                ", customerEntity=" + customerEntity +
                "]";
    }
}
