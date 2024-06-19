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

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Purchase History");
        VBox vbox = new VBox(10.0);
        vbox.setPadding(new Insets(10.0));
        TableView<Purchase> tableView = new TableView();
        TableColumn<Purchase, Integer> idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory((cellData) -> {
            return (new SimpleIntegerProperty(((Purchase)cellData.getValue()).getPurchaseId())).asObject();
        });
        TableColumn<Purchase, String> drugNameColumn = new TableColumn("Drug Name");
        drugNameColumn.setCellValueFactory((cellData) -> {
            return new SimpleStringProperty(((Purchase)cellData.getValue()).getDrugName());
        });
        TableColumn<Purchase, String> customerNameColumn = new TableColumn("Customer Name");
        customerNameColumn.setCellValueFactory((cellData) -> {
            return new SimpleStringProperty(((Purchase)cellData.getValue()).getCustomerName());
        });
        TableColumn<Purchase, Integer> quantityColumn = new TableColumn("Quantity");
        quantityColumn.setCellValueFactory((cellData) -> {
            return (new SimpleIntegerProperty(((Purchase)cellData.getValue()).getQuantity())).asObject();
        });
        TableColumn<Purchase, Double> totalPriceColumn = new TableColumn("Total Price");
        totalPriceColumn.setCellValueFactory((cellData) -> {
            return (new SimpleDoubleProperty(((Purchase)cellData.getValue()).getTotalPrice())).asObject();
        });
        TableColumn<Purchase, String> dateColumn = new TableColumn("Date");
        dateColumn.setCellValueFactory((cellData) -> {
            return new SimpleStringProperty(((Purchase)cellData.getValue()).getPurchaseDate().toString());
        });
        tableView.getColumns().addAll(new TableColumn[]{idColumn, drugNameColumn, customerNameColumn, quantityColumn, totalPriceColumn, dateColumn});

        try {
            List<Purchase> purchases = this.purchaseDAO.getAllPurchases();
            tableView.getItems().addAll(purchases);
        } catch (SQLException var12) {
            SQLException e = var12;
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR, "Error loading purchase history: " + e.getMessage(), new ButtonType[0]);
            alert.showAndWait();
        }

        vbox.getChildren().add(tableView);
        Scene scene = new Scene(vbox, 800.0, 600.0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

