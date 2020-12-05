package main;

import controller.MainScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

public class Main extends Application {


    /**
     * @void starts the program
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_screen.fxml"));
        MainScreen controller = new MainScreen();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    /**
     * @void adds sample parts and products
     */
    public static void main(String[] args) {
        Part part1 = new InHouse(1, "Bracket", 1.99, 20, 1,35, 1702);
        Part part2 = new InHouse(2, "board", 2.99, 7, 1,35, 1703);
        Part part3 = new Outsourced(3, "Bridge", 3.99, 18, 1,35, "James Inc.");
        Part part4 = new Outsourced(4, "Bramble", 4.99, 2, 1,35, "Hammer Industries");
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);


        Product product1 = new Product(1, "car",200,2, 1, 34);
        Product product2 = new Product(2, "train",17,3, 1, 88);
        Product product3 = new Product(3, "boat",190,34, 1, 23);
        Product product4 = new Product(4, "plane",345,56, 1, 100);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);

        launch(args);
    }
}
