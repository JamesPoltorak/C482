package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProduct implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField productIDField;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productInventoryField;

    @FXML
    private TextField productPriceField;

    @FXML
    private TextField maxProductField;

    @FXML
    private TextField minProductField;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> all_parts_id;

    @FXML
    private TableColumn<Part, String> all_parts_name;

    @FXML
    private TableColumn<Part, Integer> all_parts_inventory;

    @FXML
    private TableColumn<Part, Double> all_parts_price;

    @FXML
    private TableView<Part> partsTable1;

    @FXML
    private TableColumn<Part, Integer> associated_part_id;

    @FXML
    private TableColumn<Part, String> associated_part_name;

    @FXML
    private TableColumn<Part, Integer> associated_part_inventory;

    @FXML
    private TableColumn<Part, Double> associated_part_price;

    @FXML
    private TextArea partSearchBox;

    @FXML
    private Button search_part_button;

    @FXML
    private Button add_part_to_product;

    @FXML
    private Button delete_part_from_product;

    @FXML
    private Button cancel_product_button;

    @FXML
    private Button save_product_button;

    Product product = new Product(0,"", 0.00, 0, 0, 0);
    private ObservableList<Part> associatedParts1 = FXCollections.observableArrayList();
    Inventory inventory = new Inventory();
    private int productID;

    /**
     * cancels the product and returns to the main screen
     */
    @FXML
    void cancel_product_action(ActionEvent event)  throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/main_screen.fxml"));
        MainScreen controller = new MainScreen();
        loader.setController(controller);
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
     * removes part from product
     */
    @FXML
    void part_from_product_action(ActionEvent event) {
        Part part = partsTable1.getSelectionModel().getSelectedItem();
        //associatedParts1.remove(part);
        //partsTable1.setItems(associatedParts1);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove Part?");
        alert.setHeaderText("Are you sure you wish to remove this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            associatedParts1.remove(part);
            partsTable1.setItems(associatedParts1);
        } else {
            return;
        }

    }

    /**
     * adds part to product
     */
    @FXML
    void part_to_product_action(ActionEvent event) {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        associatedParts1.add(part);
        partsTable1.setItems(associatedParts1);

    }

    /**
     * saves product and returns to main screen
     */
    @FXML
    void save_product_action(ActionEvent event) throws IOException{
        if(Inventory.getAllProducts().isEmpty()){
            productID = 1;
        } else {
            productID = Inventory.productIDCount();
        }
        try{
            if(productPriceField.getText().isEmpty() || productInventoryField.getText().isEmpty() || productNameField.getText().isEmpty() || maxProductField.getText().isEmpty() || minProductField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error: Missing information");
                alert.setContentText("Please fill all fields in completely.");
                alert.showAndWait();
                return;
            }
            if(Integer.parseInt(minProductField.getText()) >= Integer.parseInt(maxProductField.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Minimum must not be greater than or equal maximum.");
                alert.showAndWait();
                return;
            }
            if(Integer.parseInt(productInventoryField.getText()) < Integer.parseInt(minProductField.getText()) || Integer.parseInt(productInventoryField.getText()) > Integer.parseInt(maxProductField.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Inventory count must be between minimum and maximum.");
                alert.showAndWait();
                return;
            }

            ObservableList<Part> parts = partsTable1.getItems();

            Product addProduct = new Product(productID, productNameField.getText(), Double.parseDouble(productPriceField.getText()),Integer.parseInt(productInventoryField.getText()),Integer.parseInt(minProductField.getText()), Integer.parseInt(maxProductField.getText()));

            addProduct.setAssociatedParts(parts);

            Inventory.addProduct(addProduct);

            // Inventory.addProduct(new Product(productID, productNameField.getText(), Double.parseDouble(productPriceField.getText()),Integer.parseInt(productInventoryField.getText()),Integer.parseInt(minProductField.getText()), Integer.parseInt(maxProductField.getText())));

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/main_screen.fxml"));
            MainScreen controller = new MainScreen();
            loader.setController(controller);
            loader.load();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(NumberFormatException e){}

    }

    /**
     * searches the parts
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
     * searches the parts
     */
    @FXML
    void search_part_action(ActionEvent event) {
        if(isNumeric(partSearchBox.getText()) == true) {
            partsTable.setItems(inventory.lookupPart(Integer.parseInt(partSearchBox.getText())));
        }
        else {
            partsTable.setItems(inventory.lookupPart(partSearchBox.getText()));
        }

    }

    /**
     * checks to see whether a string is a number or not
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
     * initializes the fields for the tables
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partsTable.setItems(Inventory.getAllParts());
        all_parts_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        all_parts_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        all_parts_inventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        all_parts_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        partsTable1.setItems(associatedParts1);
        associated_part_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        associated_part_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        associated_part_inventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associated_part_price.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


}
