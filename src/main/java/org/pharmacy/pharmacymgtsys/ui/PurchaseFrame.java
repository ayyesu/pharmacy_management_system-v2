package org.pharmacy.pharmacymgtsys.ui;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.pharmacy.pharmacymgtsys.dao.DrugDAO;
import org.pharmacy.pharmacymgtsys.dao.PurchaseDAO;
import org.pharmacy.pharmacymgtsys.model.Drug;
import org.pharmacy.pharmacymgtsys.model.Purchase;

public class PurchaseFrame extends Application {
    private DrugDAO drugDAO = new DrugDAO();
    private PurchaseDAO purchaseDAO = new PurchaseDAO();

    public PurchaseFrame() {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Purchase Drug");

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
        Label nameLabel = new Label("Drug Name:");
        nameLabel.setFont(Font.font("Verdana", 14));
        nameLabel.setTextFill(Color.web("#333"));
        ComboBox<String> drugComboBox = new ComboBox<>();
        drugComboBox.setPromptText("Select a drug");

        try {
            List<Drug> drugs = drugDAO.getAllDrugs();
            for (Drug drug : drugs) {
                drugComboBox.getItems().add(drug.getDrugName());
            }
        } catch (SQLException e) {
            showAlert(AlertType.ERROR, "Database Error", "Error loading drugs: " + e.getMessage());
        }

        Label qtyLabel = new Label("Quantity:");
        qtyLabel.setFont(Font.font("Verdana", 14));
        qtyLabel.setTextFill(Color.web("#333"));
        TextField qtyField = new TextField();
        qtyField.setFont(Font.font("Verdana", 14));
        qtyField.setPromptText("Enter quantity");

        Label customerNameLabel = new Label("Customer Name:");
        customerNameLabel.setFont(Font.font("Verdana", 14));
        customerNameLabel.setTextFill(Color.web("#333"));
        TextField customerNameField = new TextField();
        customerNameField.setFont(Font.font("Verdana", 14));
        customerNameField.setPromptText("Enter customer name");

        // Create and style the purchase button
        Button purchaseButton = new Button("Purchase");
        purchaseButton.setFont(Font.font("Verdana", 14));
        purchaseButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-padding: 8 15 8 15;");

        // Add form fields and button to the grid
        grid.add(nameLabel, 0, 0);
        grid.add(drugComboBox, 1, 0);
        grid.add(qtyLabel, 0, 1);
        grid.add(qtyField, 1, 1);
        grid.add(customerNameLabel, 0, 2);
        grid.add(customerNameField, 1, 2);
        grid.add(purchaseButton, 1, 3);

        // Add grid to the VBox
        vbox.getChildren().add(grid);

        // Set button action
        purchaseButton.setOnAction(e -> {
            String drugName = drugComboBox.getValue();
            String customerName = customerNameField.getText();
            int quantity;

            try {
                quantity = Integer.parseInt(qtyField.getText());
            } catch (NumberFormatException ex) {
                showAlert(AlertType.ERROR, "Input Error", "Invalid quantity.");
                return;
            }

            try {
                Drug drug = drugDAO.getDrugByName(drugName);
                if (drug != null && drug.getStock() >= quantity) {
                    drug.setStock(drug.getStock() - quantity);
                    drugDAO.updateDrug(drug);
                    Purchase purchase = new Purchase();
                    purchase.setDrugId(drug.getDrugId());
                    purchase.setCustomerName(customerName);
                    purchase.setPurchaseDate(LocalDateTime.now());
                    purchase.setQuantity(quantity);
                    purchase.setTotalPrice(drug.getPrice() * quantity);
                    purchaseDAO.addPurchase(purchase);
                    showAlert(AlertType.INFORMATION, "Success", "Purchase successful!");
                } else {
                    showAlert(AlertType.ERROR, "Stock Error", "Not enough stock available or drug not found.");
                }
            } catch (SQLException ex) {
                showAlert(AlertType.ERROR, "Database Error", "Error processing purchase: " + ex.getMessage());
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
