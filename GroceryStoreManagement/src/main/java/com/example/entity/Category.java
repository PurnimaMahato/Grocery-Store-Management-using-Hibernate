// Category.java
package com.example.entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    // One-to-Many relationship with GroceryItem entities
    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    private List<GroceryItem> groceryItems;

    // Default constructor
    public Category() {
        super();
    }

    // Parameterized constructor without 'id' and 'groceryItems'
    public Category(String name) {
        this.name = name;
    }

    // Getter and setter methods for 'id'
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // Getter and setter methods for 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter methods for 'groceryItems'
    public List<GroceryItem> getGroceryItems() {
        return groceryItems;
    }

    public void setGroceryItems(List<GroceryItem> groceryItems) {
        this.groceryItems = groceryItems;
    }

    // toString method for a readable representation of the Category object
    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", groceryItems=" + groceryItems + "]";
    }
}
