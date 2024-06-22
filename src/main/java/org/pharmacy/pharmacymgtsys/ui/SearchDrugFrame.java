package org.pharmacy.pharmacymgtsys.ui;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.pharmacy.pharmacymgtsys.dao.DrugDAO;
import org.pharmacy.pharmacymgtsys.model.Drug;

public class SearchDrugFrame extends Application {
    private DrugDAO drugDAO = new DrugDAO();

    public SearchDrugFrame() {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Search Drug");

        // Create and style the main container
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: #f0f4f7;");
        vbox.setAlignment(Pos.CENTER);

        // Create and style the grid layout for the form
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Style the label
        Label nameLabel = new Label("Drug Name:");
        nameLabel.setFont(Font.font("Verdana", 14));
        nameLabel.setTextFill(Color.web("#333"));

        // Style the text field
        TextField nameField = new TextField();
        nameField.setFont(Font.font("Verdana", 14));
        nameField.setPromptText("Enter drug name");

        // Style the button
        Button searchButton = new Button("Search");
        searchButton.setFont(Font.font("Verdana", 14));
        searchButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-padding: 5 10 5 10;");

        // Style the result area
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setFont(Font.font("Verdana", 14));
        resultArea.setPromptText("Search results will appear here");
        resultArea.setStyle("-fx-control-inner-background: #eef;");

        // Add components to the grid
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(searchButton, 1, 1);

        // Set button action
        searchButton.setOnAction(e -> {
            String drugName = nameField.getText();
            try {
                Drug drug = drugDAO.getDrugByName(drugName);
                if (drug != null) {
                    resultArea.setText(
                            "Name: " + drug.getDrugName() + "\n" +
                                    "Description: " + drug.getDescription() + "\n" +
                                    "Price (GH₵): " + drug.getPrice() + "\n" +
                                    "Stock: " + drug.getStock()
                    );
                } else {
                    showAlert(AlertType.ERROR, "Drug not found", "Couldn't retrieve drug, please check drug name for validity.");
                }
            } catch (SQLException ex) {
                showAlert(AlertType.ERROR, "Database Error", "Error searching for drug: " + ex.getMessage());
            }
        });

        // Add grid and result area to the VBox
        vbox.getChildren().addAll(grid, resultArea);

        // Create and set the scene
        Scene scene = new Scene(vbox, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
