// Store.java
package com.example.entity;
import javax.persistence.*;
import java.util.List;

//Store entity representing a physical store in the system
@Entity
@Table(name = "stores")
public class Store {

	// Primary key for the store
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	// Name of the store, cannot be null, limited to 255 characters
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	// Location of the store, cannot be null, limited to 255 characters
	@Column(name = "location", nullable = false, length = 255)
	private String location;

	// Many-to-Many relationship between Store and GroceryItem entities
	// This relationship is mapped by the store_grocery_items join table
	@ManyToMany
	@JoinTable(
         name = "store_grocery_items",
         joinColumns = @JoinColumn(name = "store_id"),
         inverseJoinColumns = @JoinColumn(name = "grocery_item_id")
 )
	private List<GroceryItem> groceryItems;


    // Default constructor
    public Store() {
        super();
    }

    // Parameterized constructor without 'id' and 'groceryItems'
    public Store(String name, String location) {
        this.name = name;
        this.location = location;
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

    // Getter and setter methods for 'location'
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Getter and setter methods for 'groceryItems'
    public List<GroceryItem> getGroceryItems() {
        return groceryItems;
    }

    public void setGroceryItems(List<GroceryItem> groceryItems) {
        this.groceryItems = groceryItems;
    }

    // toString method for a readable representation of the Store object
    @Override
    public String toString() {
        return "Store [id=" + id + ", name=" + name + ", location=" + location +
                ", groceryItems=" + groceryItems +
                "]";
    }
}
