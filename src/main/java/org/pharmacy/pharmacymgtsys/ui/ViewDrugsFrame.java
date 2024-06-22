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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.pharmacy.pharmacymgtsys.dao.DrugDAO;
import org.pharmacy.pharmacymgtsys.model.Drug;

public class ViewDrugsFrame extends Application {
    private DrugDAO drugDAO = new DrugDAO();

    public ViewDrugsFrame() {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("View Drugs");

        // Create and style the main container
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f0f4f7;");

        // Create and style the TableView
        TableView<Drug> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("-fx-background-color: white; -fx-text-background-color: #333;");

        // Create and style the table columns
        TableColumn<Drug, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDrugId()).asObject());

        TableColumn<Drug, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDrugName()));

        TableColumn<Drug, String> descColumn = new TableColumn<>("Description");
        descColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

        TableColumn<Drug, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        TableColumn<Drug, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());

        table.getColumns().addAll(idColumn, nameColumn, descColumn, priceColumn, stockColumn);

        // Fetch and add the data to the table
        try {
            List<Drug> drugs = drugDAO.getAllDrugs();
            table.getItems().addAll(drugs);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR, "Error retrieving drugs: " + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }

        // Add the table to the BorderPane
        root.setCenter(table);

        // Create and set the scene
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("file:src/main/resources/css/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
