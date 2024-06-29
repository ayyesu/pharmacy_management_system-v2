package org.pharmacy.pharmacymgtsys.ui;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.pharmacy.pharmacymgtsys.dao.DrugDAO;
import org.pharmacy.pharmacymgtsys.model.Drug;

/**
 * This is the Add Drug Frame.
 * It represents the UI to add drugs
 *
 * @author Daniel, jonathan, Hannah, felix, Martin, Bright
 * @version 1.0
 */
public class AddDrugFrame extends Application {
    private DrugDAO drugDAO = new DrugDAO();

    public AddDrugFrame() {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Drug");

        // Create and style the main container
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: #f0f4f7;");
        vbox.setAlignment(Pos.CENTER);

        // Create and style the grid layout for the form
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        // Create and style the form fields and labels
        Label nameLabel = new Label("Name:");
        nameLabel.setFont(Font.font("Verdana", 14));
        nameLabel.setTextFill(Color.web("#333"));
        TextField nameField = new TextField();
        nameField.setFont(Font.font("Verdana", 14));
        nameField.setPromptText("Enter drug name");

        Label descLabel = new Label("Description:");
        descLabel.setFont(Font.font("Verdana", 14));
        descLabel.setTextFill(Color.web("#333"));
        TextField descField = new TextField();
        descField.setFont(Font.font("Verdana", 14));
        descField.setPromptText("Enter drug description");

        Label priceLabel = new Label("Price(GH₵):");
        priceLabel.setFont(Font.font("Verdana", 14));
        priceLabel.setTextFill(Color.web("#333"));
        TextField priceField = new TextField();
        priceField.setFont(Font.font("Verdana", 14));
        priceField.setPromptText("Enter drug price");

        Label stockLabel = new Label("Stock:");
        stockLabel.setFont(Font.font("Verdana", 14));
        stockLabel.setTextFill(Color.web("#333"));
        TextField stockField = new TextField();
        stockField.setFont(Font.font("Verdana", 14));
        stockField.setPromptText("Enter drug stock quantity");

        // Create and style the add button
        Button addButton = new Button("Add");
        addButton.setFont(Font.font("Verdana", 14));
        addButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 8 15 8 15;");

        // Add form fields and button to the grid
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(descLabel, 0, 1);
        grid.add(descField, 1, 1);
        grid.add(priceLabel, 0, 2);
        grid.add(priceField, 1, 2);
        grid.add(stockLabel, 0, 3);
        grid.add(stockField, 1, 3);
        grid.add(addButton, 1, 4);

        // Add grid to the VBox
        vbox.getChildren().add(grid);

        // Set button action
        addButton.setOnAction(e -> {
            try {
                Drug drug = new Drug();
                drug.setDrugName(nameField.getText());
                drug.setDescription(descField.getText());
                drug.setPrice(Double.parseDouble(priceField.getText()));
                drug.setStock(Integer.parseInt(stockField.getText()));
                drugDAO.addDrug(drug);
                showAlert(AlertType.INFORMATION, "Success", "Drug added successfully!");
                primaryStage.close();
            } catch (NumberFormatException ex) {
                showAlert(AlertType.ERROR, "Input Error", "Error parsing number: " + ex.getMessage());
            } catch (SQLException ex) {
                showAlert(AlertType.ERROR, "Database Error", "Error adding drug: " + ex.getMessage());
            } catch (Exception ex) {
                showAlert(AlertType.ERROR, "Unknown Error", "An unknown error occurred: " + ex.getMessage());
            }
        });

        // Create and set the scene
        Scene scene = new Scene(vbox, 450, 350);
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
