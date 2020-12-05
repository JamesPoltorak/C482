package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import model.*;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainScreen implements Initializable {

    Stage stage;
    Parent scene;

    public static int index;

    @FXML
    private Label main_label;

    @FXML
    private Button exit_button;

    @FXML
    private TextArea partSearchBox;

    @FXML
    private Button search_part_button;

    @FXML
    private Button add_part_button;

    @FXML
    private Button modify_part_button;

    @FXML
    private Button delete_part_button;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> part_ID_column;

    @FXML
    private TableColumn<Part, String> part_name_column;

    @FXML
    private TableColumn<Part, Integer> part_inventory_column;

    @FXML
    private TableColumn<Part, Double> part_price_column;

    @FXML
    private TextArea productSearchBox;

    @FXML
    private Button search_product_button;

    @FXML
    private Button add_product_button;

    @FXML
    private Button modify_product_button;

    @FXML
    private Button delete_product_button;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, Integer> product_ID_column;

    @FXML
    private TableColumn<Product, String> product_name_column;

    @FXML
    private TableColumn<Product, Integer> product_inventory_column;

    @FXML
    private TableColumn<Product, Double> product_price_column;

    Inventory inventory = new Inventory();

    /**
     * opens the add part screen
     */
    @FXML
    void addPartButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/add_part.fxml"));
        loader.load();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * opens the add product screen
     */
    @FXML
    void addProductButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/add_product.fxml"));
        loader.load();
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void clearText(MouseEvent event) {

    }

    /**
     * deletes the selected part
     */
    @FXML
    void deletePartButton(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Part?");
        alert.setHeaderText("Delete Part?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
            Inventory.deletePart(selectedPart);
        } else {
            return;
        }
    }

    /**
     * deletes the selected product
     */
    @FXML
    void deleteProductButton(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Product?");
        alert.setHeaderText("Delete Product?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

            if(selectedProduct.getAllAssociatedParts().isEmpty()) {
                Inventory.deleteProduct(selectedProduct);
            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error");
                alert1.setHeaderText("Error: please remove all associated parts");
                alert1.showAndWait();
                return;
            }

            //Inventory.deleteProduct(selectedProduct);
        } else {
            return;
        }
    }

    @FXML
    void exitProgram(ActionEvent event) {

    }

    /**
     * exits the program
     */
    @FXML
    void exitProgramButton(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Program?");
        alert.setHeaderText("Are you sure you wish to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            System.exit(0);
        }
        else {
            return;
        }
    }

    /**
     * Modify the selected part
     */
    @FXML
    void modifyPartButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modify_part.fxml"));
        loader.load();

        ModifyPart modPartController = loader.getController();
        modPartController.sendPart(partsTable.getSelectionModel().getSelectedItem());
        index = Inventory.getAllParts().indexOf(partsTable.getSelectionModel().getSelectedItem());


        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Modify the selected product
     */
    @FXML
    void modifyProductButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modify_product.fxml"));
        loader.load();

        ModifyProduct modProductController = loader.getController();
        modProductController.sendProduct(productsTable.getSelectionModel().getSelectedItem());
        index = Inventory.getAllProducts().indexOf(productsTable.getSelectionModel().getSelectedItem());

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * searches for parts
     */
    @FXML
    void searchPartButton(MouseEvent event) {
        if(isNumeric(partSearchBox.getText()) == true) {
            partsTable.setItems(inventory.lookupPart(Integer.parseInt(partSearchBox.getText())));
        }
        else {
            partsTable.setItems(inventory.lookupPart(partSearchBox.getText()));
        }

    }

    /**
     * Determines if a string is a number or not
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Searches for a product
     */
    @FXML
    void searchProductButton(MouseEvent event) {
        if(isNumeric(productSearchBox.getText()) == true){
            productsTable.setItems(inventory.lookupProduct(Integer.parseInt(productSearchBox.getText())));
        }
        else {
            productsTable.setItems(inventory.lookupProduct(productSearchBox.getText()));
        }
    }

    /**
     * sets initial information for the tables
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partsTable.setItems(Inventory.getAllParts());
        part_ID_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        part_name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        part_inventory_column.setCellValueFactory(new PropertyValueFactory<>("stock"));
        part_price_column.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTable.setItems(Inventory.getAllProducts());
        product_ID_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        product_name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        product_inventory_column.setCellValueFactory(new PropertyValueFactory<>("stock"));
        product_price_column.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}

