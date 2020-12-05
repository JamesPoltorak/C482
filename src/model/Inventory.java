package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    public static int incrementer = 4;

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * adds a part to the list
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**
     * returns all parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * deletes an existing part
     */
    public static void deletePart(Part existingPart){
        allParts.remove(existingPart);
    }

    /**
     * adds a product to the list
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     * returns all products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * deletes an existing product
     */
    public static void deleteProduct(Product existingProduct){
        allProducts.remove(existingProduct);
    }

    /**
     * creates a unique ID for each part
     */
    public static int partIDCount(){
        incrementer = incrementer + 1;
        return incrementer;

    }

    /**
     * creates a unique id for each product
     */
    public static int productIDCount(){
        incrementer = incrementer + 1;
        return incrementer;

    }
    /**
     * updates a part with new information
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index,selectedPart);
    }

    /**
     * updates a product with new information
     */
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }

    /**
     * searches for a specific part based of integer
     */
    public ObservableList<Part> lookupPart(Integer partID){
        ObservableList<Part> partList = FXCollections.observableArrayList();
        for (Part partsList : allParts){
            if(partsList.getId() == partID){
                partList.add(partsList);
            }
        }
        return partList;
    }

    /**
     * searches for a part based off string
     */
    public ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsList = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase().trim())) {
                partsList.add(part);
            }
        }
        return partsList;
    }

    /**
     * searches for a product based of integer
     */
    public ObservableList<Product> lookupProduct(Integer productID){
        ObservableList<Product> productsList = FXCollections.observableArrayList();
        for (Product product : allProducts){
            if(product.getId() == productID){
                productsList.add(product);
            }
        }
        return productsList;
    }

    /**
     * searches for a product based of string
     */
    public ObservableList<Product> lookupProduct(String partName) {
        ObservableList<Product> productsList = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(partName.toLowerCase().trim())) {
                productsList.add(product);
            }
        }
        return productsList;
    }

}
