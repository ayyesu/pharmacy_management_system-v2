package org.pharmacy.pharmacymgtsys.ui;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
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

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Purchase Drug");
        VBox vbox = new VBox(10.0);
        vbox.setPadding(new Insets(10.0));
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10.0));
        grid.setHgap(10.0);
        grid.setVgap(10.0);
        Label nameLabel = new Label("Drug Name:");
        grid.add(nameLabel, 0, 0);
        ComboBox<String> drugComboBox = new ComboBox();
        grid.add(drugComboBox, 1, 0);

        try {
            List<Drug> drugs = this.drugDAO.getAllDrugs();
            Iterator var15 = drugs.iterator();

            while(var15.hasNext()) {
                Drug drug = (Drug)var15.next();
                drugComboBox.getItems().add(drug.getDrugName());
            }
        } catch (SQLException var12) {
            SQLException e = var12;
            Alert alert = new Alert(AlertType.ERROR, "Error loading drugs: " + e.getMessage(), new ButtonType[0]);
            alert.showAndWait();
        }

        Label qtyLabel = new Label("Quantity:");
        grid.add(qtyLabel, 0, 1);
        TextField qtyField = new TextField();
        grid.add(qtyField, 1, 1);
        Label customerNameLabel = new Label("Customer Name:");
        grid.add(customerNameLabel, 0, 2);
        TextField customerNameField = new TextField();
        grid.add(customerNameField, 1, 2);
        Button purchaseButton = new Button("Purchase");
        grid.add(purchaseButton, 1, 3);
        purchaseButton.setOnAction((ex) -> {
            String drugName = (String)drugComboBox.getValue();
            String customerName = customerNameField.getText();

            int quantity;
            Alert alert;
            try {
                quantity = Integer.parseInt(qtyField.getText());
            } catch (NumberFormatException var11) {
                alert = new Alert(AlertType.ERROR, "Invalid quantity.", new ButtonType[0]);
                alert.showAndWait();
                return;
            }

            try {
                Drug drug = this.drugDAO.getDrugByName(drugName);
                if (drug != null && drug.getStock() >= quantity) {
                    drug.setStock(drug.getStock() - quantity);
                    this.drugDAO.updateDrug(drug);
                    Purchase purchase = new Purchase();
                    purchase.setDrugId(drug.getDrugId());
                    purchase.setCustomerName(customerName);
                    purchase.setPurchaseDate(LocalDateTime.now());
                    purchase.setQuantity(quantity);
                    purchase.setTotalPrice(drug.getPrice() * (double)quantity);
                    this.purchaseDAO.addPurchase(purchase);
                    Alert alertx = new Alert(AlertType.INFORMATION, "Purchase successful!", new ButtonType[0]);
                    alertx.showAndWait();
                } else {
                    alert = new Alert(AlertType.ERROR, "Not enough stock available or drug not found.", new ButtonType[0]);
                    alert.showAndWait();
                }
            } catch (SQLException var12) {
                SQLException exx = var12;
                alert = new Alert(AlertType.ERROR, "Error processing purchase: " + exx.getMessage(), new ButtonType[0]);
                alert.showAndWait();
            }

        });
        vbox.getChildren().add(grid);
        Scene scene = new Scene(vbox, 400.0, 250.0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

