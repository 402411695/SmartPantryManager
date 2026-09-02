package com.smartpantrymanager;

public class PantryItem {
    //Stores the details of one pantry ingredient
    private int id;
    private String name;
    private double quantity;
    private String unit;
    private String expiryDate;

    //Constructor used to create pantry item
    public PantryItem(int id, String name, double quantity, String unit, String expiryDate) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.expiryDate = expiryDate;
    }

    //Getter for item ID
    public int getId() {
        return id;
    }

    //Getter for the ingredient name
    public String getName() {
        return name;
    }

    //Getter for the quantity
    public double getQuantity() {
        return quantity;
    }

    //Getter for the unit
    public String getUnit() {
        return unit;
    }

    //Getter for the expiry date
    public String getExpiryDate() {
        return expiryDate;
    }

    //Setter for the item ID
    public void setId(int id) {
        this.id = id;
    }

    //Setter for the ingredient name
    public void setName(String name) {
        this.name = name;
    }

    //Setter for the quantity
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    //Setter for the unit
    public void setUnit(String unit) {
        this.unit = unit;
    }

    //Setter for expiry date
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}

