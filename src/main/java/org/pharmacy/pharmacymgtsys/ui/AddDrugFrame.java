package org.pharmacy.pharmacymgtsys.ui;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.pharmacy.pharmacymgtsys.dao.DrugDAO;
import org.pharmacy.pharmacymgtsys.model.Drug;

public class AddDrugFrame extends Application {
    private DrugDAO drugDAO = new DrugDAO();

    public AddDrugFrame() {
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Drug");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10.0));
        grid.setHgap(10.0);
        grid.setVgap(10.0);
        Label nameLabel = new Label("Name:");
        grid.add(nameLabel, 0, 0);
        TextField nameField = new TextField();
        grid.add(nameField, 1, 0);
        Label descLabel = new Label("Description:");
        grid.add(descLabel, 0, 1);
        TextField descField = new TextField();
        grid.add(descField, 1, 1);
        Label priceLabel = new Label("Price:");
        grid.add(priceLabel, 0, 2);
        TextField priceField = new TextField();
        grid.add(priceField, 1, 2);
        Label stockLabel = new Label("Stock:");
        grid.add(stockLabel, 0, 3);
        TextField stockField = new TextField();
        grid.add(stockField, 1, 3);
        Button addButton = new Button("Add");
        grid.add(addButton, 1, 4);
        addButton.setOnAction((e) -> {
            Alert alert;
            try {
                Drug drug = new Drug();
                drug.setDrugName(nameField.getText());
                drug.setDescription(descField.getText());
                drug.setPrice(Double.parseDouble(priceField.getText()));
                drug.setStock(Integer.parseInt(stockField.getText()));
                this.drugDAO.addDrug(drug);
                alert = new Alert(AlertType.INFORMATION, "Drug added successfully!", new ButtonType[0]);
                alert.showAndWait();
                primaryStage.close();
            } catch (NumberFormatException var9) {
                NumberFormatException exx = var9;
                alert = new Alert(AlertType.ERROR, "Error parsing number: " + exx.getMessage(), new ButtonType[0]);
                alert.showAndWait();
                exx.printStackTrace();
            } catch (SQLException var10) {
                SQLException ex = var10;
                alert = new Alert(AlertType.ERROR, "Error adding drug: " + ex.getMessage(), new ButtonType[0]);
                alert.showAndWait();
                ex.printStackTrace();
            } catch (Exception var11) {
                Exception exxxx = var11;
                alert = new Alert(AlertType.ERROR, "Unknown error: " + exxxx.getMessage(), new ButtonType[0]);
                alert.showAndWait();
                exxxx.printStackTrace();
            }

        });
        Scene scene = new Scene(grid, 400.0, 300.0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

