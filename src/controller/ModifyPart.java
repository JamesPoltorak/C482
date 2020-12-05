package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPart implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private RadioButton inHouseButton;

    @FXML
    private RadioButton outsourcedButton;

    @FXML
    private ToggleGroup partSourceGroup;

    @FXML
    private Label CompanyName;

    @FXML
    private TextField partIDField;

    @FXML
    private TextField partNameField;

    @FXML
    private TextField partInventoryField;

    @FXML
    private TextField partPriceField;

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
     * recieves the information from the selected part on the main screen and fills in the associated information on this screen
     */
    public void sendPart(Part part){
        partIDField.setText(String.valueOf(part.getId()));
        partNameField.setText(String.valueOf(part.getName()));
        partInventoryField.setText(String.valueOf(part.getStock()));
        partPriceField.setText(String.valueOf(part.getPrice()));
        maxPartField.setText(String.valueOf(part.getMax()));
        minPartField.setText(String.valueOf(part.getMin()));

        if(part instanceof InHouse){
            partMachineIDField.setVisible(true);
            partMachineID.setVisible(true);
            CompanyName.setVisible(false);
            companyNameField.setVisible(false);
            inHouseButton.setSelected(true);
            partMachineIDField.setText(String.valueOf(((InHouse) part).getMachineID()));
        }
        else{
            partMachineIDField.setVisible(false);
            partMachineID.setVisible(false);
            CompanyName.setVisible(true);
            companyNameField.setVisible(true);
            outsourcedButton.setSelected(true);
            companyNameField.setText(((Outsourced) part).getCompanyName());
        }
    }

    /**
     * sets information if the part is inhouse
     */
    @FXML
    void inHouse(ActionEvent event) {
        inHouseButton.setSelected(true);
        outsourcedButton.setSelected(false);
        partMachineIDField.setVisible(true);
        partMachineID.setVisible(true);
        CompanyName.setVisible(false);
        companyNameField.setVisible(false);
    }

    /**
     * sets information if the part is outsourced
     */
    @FXML
    void outsourced(ActionEvent event) {
        inHouseButton.setSelected(false);
        outsourcedButton.setSelected(true);
        partMachineIDField.setVisible(false);
        partMachineID.setVisible(false);
        CompanyName.setVisible(true);
        companyNameField.setVisible(true);
    }

    /**
     * cancels the part and returns to the main screen
     */
    @FXML
    void partCancel(ActionEvent event) throws IOException {
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
     * saves the part and returns the main screen
     */
    @FXML
    void partSave(ActionEvent event) {
        partID = Integer.parseInt(partIDField.getText());
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
                Inventory.updatePart(MainScreen.index,new InHouse(partID,partNameField.getText(),Double.parseDouble(partPriceField.getText()),Integer.parseInt(partInventoryField.getText()),Integer.parseInt(minPartField.getText()),Integer.parseInt(maxPartField.getText()),Integer.parseInt(partMachineIDField.getText())));
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
                Inventory.updatePart(MainScreen.index, new Outsourced(partID,partNameField.getText(),Double.parseDouble(partPriceField.getText()),Integer.parseInt(partInventoryField.getText()),Integer.parseInt(minPartField.getText()),Integer.parseInt(maxPartField.getText()),companyNameField.getText()));
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
        } catch(NumberFormatException | IOException e){}

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
