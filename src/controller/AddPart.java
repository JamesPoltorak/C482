package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;

public class AddPart {

    Stage stage;
    Parent scene;

    @FXML
    private RadioButton inHouseButton;

    @FXML
    private ToggleGroup partSourceGroup;

    @FXML
    private RadioButton outsourcedButton;

    @FXML
    private TextField partIDField;

    @FXML
    private TextField partNameField;

    @FXML
    private TextField partInventoryField;

    @FXML
    private TextField partPriceField;

    @FXML
    private Label companyName;

    @FXML
    private TextField companyNameField;

    @FXML
    private TextField maxPartField;

    @FXML
    private TextField minPartField;

    @FXML
    private Label partMachineID;

    @FXML
    private TextField partMachineIDField;

    @FXML
    private Button partCancelButton;

    @FXML
    private Button partSaveButton;

    private int partID;

    /**
     * changes information for inhouse parts
     */
    @FXML
    void inHouse(ActionEvent event) {
        partMachineIDField.setVisible(true);
        partMachineID.setVisible(true);
        companyName.setVisible(false);
        companyNameField.setVisible(false);
    }

    /**
     * changes information for outsourced parts
     */
    @FXML
    void outsourced(ActionEvent event) {
        partMachineIDField.setVisible(false);
        partMachineID.setVisible(false);
        companyName.setVisible(true);
        companyNameField.setVisible(true);
    }

    /**
     * cancels the part and returns to main screen
     */
    @FXML
    void partCancel(ActionEvent event)  throws IOException {
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

    /**
     * saves the part and returns to main screen
     */
    @FXML
    void partSave(ActionEvent event) throws IOException {
        if(Inventory.getAllParts().isEmpty()){
            partID = 1;
        } else{
            partID = Inventory.partIDCount();
        }
        try{
            if(inHouseButton.isSelected()){
                if(partNameField.getText().isEmpty() || partPriceField.getText().isEmpty() || partInventoryField.getText().isEmpty() || minPartField.getText().isEmpty() || maxPartField.getText().isEmpty() || partMachineIDField.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error: Missing information");
                    alert.setContentText("Please fill all fields in completely.");
                    alert.showAndWait();
                    return;
                }
                if(Integer.parseInt(minPartField.getText()) >= Integer.parseInt(maxPartField.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Minimum must not be greater than or equal maximum.");
                    alert.showAndWait();
                    return;
                }
                if(Integer.parseInt(partInventoryField.getText()) < Integer.parseInt(minPartField.getText()) || Integer.parseInt(partInventoryField.getText()) > Integer.parseInt(maxPartField.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Inventory count must be between minimum and maximum.");
                    alert.showAndWait();
                    return;
                }
                Inventory.addPart(new InHouse(partID,partNameField.getText(),Double.parseDouble(partPriceField.getText()),Integer.parseInt(partInventoryField.getText()),Integer.parseInt(minPartField.getText()),Integer.parseInt(maxPartField.getText()),Integer.parseInt(partMachineIDField.getText())));
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
            else if(outsourcedButton.isSelected()){
                if(partNameField.getText().isEmpty() || partPriceField.getText().isEmpty() || partInventoryField.getText().isEmpty() || minPartField.getText().isEmpty() || maxPartField.getText().isEmpty() || companyNameField.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error: Missing information");
                    alert.setContentText("Please fill all fields in completely.");
                    alert.showAndWait();
                    return;
                }
                if(Integer.parseInt(minPartField.getText()) >= Integer.parseInt(maxPartField.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Minimum must not be greater than or equal maximum.");
                    alert.showAndWait();
                    return;
                }
                if(Integer.parseInt(partInventoryField.getText()) < Integer.parseInt(minPartField.getText()) || Integer.parseInt(partInventoryField.getText()) > Integer.parseInt(maxPartField.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Inventory count must be between minimum and maximum.");
                    alert.showAndWait();
                    return;
                }
                Inventory.addPart(new Outsourced(partID,partNameField.getText(),Double.parseDouble(partPriceField.getText()),Integer.parseInt(partInventoryField.getText()),Integer.parseInt(minPartField.getText()),Integer.parseInt(maxPartField.getText()),companyNameField.getText()));
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
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please select whether the product was Inhouse or Outsourced.");
                alert.showAndWait();
                return;
            }
        } catch(NumberFormatException e){}

    }

}

