package com.example.projectthree;

public class InventoryItem {
    // Private fields for encapsulation
    private final int id;
    private String name;
    private int quantity;

    /**
     * Constructor to initialize InventoryItem with id, name, and quantity.
     * Ensures id and quantity are non-negative, and name is not null or empty.
     *
     * @param id The unique identifier for the item.
     * @param name The name of the item.
     * @param quantity The quantity of the item in stock.
     */
    public InventoryItem(int id, String name, int quantity) {
        if (id < 0) {
            throw new IllegalArgumentException("ID must be non-negative");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }

        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Getter and setter for name with validation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    // Getter and setter for quantity with validation
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }
        this.quantity = quantity;
    }

    /**
     * Override of toString for easy debugging and display.
     *
     * @return String representation of the InventoryItem object.
     */
    @Override
    public String toString() {
        return "InventoryItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
