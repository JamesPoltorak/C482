package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price = 0.0;
    private int stock = 0;
    private int min;
    private int max;
    private double cost;

    public Product(int id, String name, double price, int stock, int min, int max){
        setId(id);
        setName(name);
        setPrice(price);
        setStock(stock);
        setMin(min);
        setMax(max);
    }

    /**
     * return the name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * sets the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * sets the stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * set the min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * set the max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * adds associated parts
     */
    public void addAssociatedPart(Part partToAdd) {associatedParts.add(partToAdd);}

    /**
     * removes associated parts
     */
    public void removeAssociatedPart(Part partToRemove) {associatedParts.remove(partToRemove);
    }

    /**
     * returns ID
     */
    public int getId() {
        return id;
    }

    /**
     * set the ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * returns associated parts list size
     */
    public int getPartsListSize() {return associatedParts.size();}

    /**
     * returns the associated parts list
     */
    public ObservableList<Part> getAllAssociatedParts() {return associatedParts;}

    /**
     * sets the assocaited parts list
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts1) {
        associatedParts = associatedParts1;
    }

}
