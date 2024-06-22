package org.pharmacy.pharmacymgtsys.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFrame extends Application {
    public MainFrame() {
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacy Management System");
        VBox vbox = new VBox(10.0);
        vbox.setPadding(new Insets(20.0));
        vbox.setStyle("-fx-background-color: #f0f0f0;"); // Set background color

        // Add a heading
        Label heading = new Label("Pharmacy Management System");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button addDrugButton = new Button("Add Drug");
        Button viewDrugsButton = new Button("View Drugs");
        Button searchDrugButton = new Button("Search Drug");
        Button purchaseDrugButton = new Button("Purchase Drug");
        Button purchaseHistoryButton = new Button("View Purchase History");

        // Add some space between the heading and the buttons
        vbox.getChildren().addAll(heading, new Label(""), addDrugButton, viewDrugsButton, searchDrugButton, purchaseDrugButton, purchaseHistoryButton);

        // Set button styles
        addDrugButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        viewDrugsButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        searchDrugButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        purchaseDrugButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        purchaseHistoryButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        addDrugButton.setOnAction((e) -> {
            (new AddDrugFrame()).start(new Stage());
        });
        viewDrugsButton.setOnAction((e) -> {
            (new ViewDrugsFrame()).start(new Stage());
        });
        searchDrugButton.setOnAction((e) -> {
            (new SearchDrugFrame()).start(new Stage());
        });
        purchaseDrugButton.setOnAction((e) -> {
            (new PurchaseFrame()).start(new Stage());
        });
        purchaseHistoryButton.setOnAction((e) -> {
            (new PurchaseHistoryFrame()).start(new Stage());
        });

        Scene scene = new Scene(vbox, 800.0, 600.0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}