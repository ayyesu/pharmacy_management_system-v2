package org.pharmacy.pharmacymgtsys.ui;

import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.pharmacy.pharmacymgtsys.dao.PurchaseDAO;
import org.pharmacy.pharmacymgtsys.model.Purchase;

public class PurchaseHistoryFrame extends Application {
    private PurchaseDAO purchaseDAO = new PurchaseDAO();

    public PurchaseHistoryFrame() {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Purchase History");

        // Create and style the main container
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: #f0f4f7;");

        // Create and style the TableView
        TableView<Purchase> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setStyle("-fx-background-color: white; -fx-text-background-color: #333;");

        // Create and style the table columns
        TableColumn<Purchase, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPurchaseId()).asObject());

        TableColumn<Purchase, String> drugNameColumn = new TableColumn<>("Drug Name");
        drugNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDrugName()));

        TableColumn<Purchase, String> customerNameColumn = new TableColumn<>("Customer Name");
        customerNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomerName()));

        TableColumn<Purchase, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());

        TableColumn<Purchase, Double> totalPriceColumn = new TableColumn<>("Total Price (GH₵)");
        totalPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalPrice()).asObject());

        TableColumn<Purchase, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPurchaseDate().toString()));

        tableView.getColumns().addAll(idColumn, drugNameColumn, customerNameColumn, quantityColumn, totalPriceColumn, dateColumn);

        // Fetch and add the data to the table
        try {
            List<Purchase> purchases = purchaseDAO.getAllPurchases();
            tableView.getItems().addAll(purchases);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR, "Error loading purchase history: " + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

        // Add the table to the VBox
        vbox.getChildren().add(tableView);

        // Create and set the scene
        Scene scene = new Scene(vbox, 900, 600);
        scene.getStylesheets().add("file:src/main/resources/css/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
