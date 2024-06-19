package org.pharmacy.pharmacymgtsys.ui;

import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.pharmacy.pharmacymgtsys.dao.DrugDAO;
import org.pharmacy.pharmacymgtsys.model.Drug;


public class ViewDrugsFrame extends Application {
    private DrugDAO drugDAO = new DrugDAO();

    public ViewDrugsFrame() {
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("View Drugs");
        TableView<Drug> table = new TableView();
        TableColumn<Drug, Integer> idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory((cellData) -> {
            return (new SimpleIntegerProperty(((Drug)cellData.getValue()).getDrugId())).asObject();
        });
        TableColumn<Drug, String> nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory((cellData) -> {
            return new SimpleStringProperty(((Drug)cellData.getValue()).getDrugName());
        });
        TableColumn<Drug, String> descColumn = new TableColumn("Description");
        descColumn.setCellValueFactory((cellData) -> {
            return new SimpleStringProperty(((Drug)cellData.getValue()).getDescription());
        });
        TableColumn<Drug, Double> priceColumn = new TableColumn("Price");
        priceColumn.setCellValueFactory((cellData) -> {
            return (new SimpleDoubleProperty(((Drug)cellData.getValue()).getPrice())).asObject();
        });
        TableColumn<Drug, Integer> stockColumn = new TableColumn("Stock");
        stockColumn.setCellValueFactory((cellData) -> {
            return (new SimpleIntegerProperty(((Drug)cellData.getValue()).getStock())).asObject();
        });
        table.getColumns().addAll(new TableColumn[]{idColumn, nameColumn, descColumn, priceColumn, stockColumn});

        try {
            List<Drug> drugs = this.drugDAO.getAllDrugs();
            table.getItems().addAll(drugs);
        } catch (SQLException var10) {
            SQLException e = var10;
            Alert alert = new Alert(AlertType.ERROR, "Error retrieving drugs: " + e.getMessage(), new ButtonType[0]);
            alert.showAndWait();
        }

        BorderPane root = new BorderPane();
        root.setCenter(table);
        Scene scene = new Scene(root, 600.0, 400.0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

