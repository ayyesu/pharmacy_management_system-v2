package org.pharmacy.pharmacymgtsys.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFrame extends Application {
    public MainFrame() {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacy Management System");

        // Main container with padding and spacing
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(30));
        vbox.setStyle("-fx-background-color: #f0f4f7;");

        // Heading label with styling
        Label heading = new Label("Pharmacy Management System");
        heading.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #333; -fx-alignment: center;");

        // Creating buttons with uniform styling
        Button addDrugButton = createStyledButton("Add Drug");
        Button viewDrugsButton = createStyledButton("View Drugs");
        Button searchDrugButton = createStyledButton("Search Drug");
        Button purchaseDrugButton = createStyledButton("Purchase Drug");
        Button purchaseHistoryButton = createStyledButton("View Purchase History");

        // Adding buttons to the VBox
        vbox.getChildren().addAll(heading, addDrugButton, viewDrugsButton, searchDrugButton, purchaseDrugButton, purchaseHistoryButton);

        // Adding event handlers for buttons
        addDrugButton.setOnAction(e -> (new AddDrugFrame()).start(new Stage()));
        viewDrugsButton.setOnAction(e -> (new ViewDrugsFrame()).start(new Stage()));
        searchDrugButton.setOnAction(e -> (new SearchDrugFrame()).start(new Stage()));
        purchaseDrugButton.setOnAction(e -> (new PurchaseFrame()).start(new Stage()));
        purchaseHistoryButton.setOnAction(e -> (new PurchaseHistoryFrame()).start(new Stage()));

        // Setting the scene and showing the primary stage
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to create styled buttons
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #0056b3; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;"));
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
