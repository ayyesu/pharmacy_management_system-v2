package org.pharmacy.pharmacymgtsys.ui;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;
import org.pharmacy.pharmacymgtsys.dao.DrugDAO;
import org.pharmacy.pharmacymgtsys.model.Drug;

public class SearchDrugFrame extends Application {
    private DrugDAO drugDAO = new DrugDAO();

    public SearchDrugFrame() {
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Search Drug");
        VBox vbox = new VBox(10.0);
        vbox.setPadding(new Insets(10.0));
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10.0));
        grid.setHgap(10.0);
        grid.setVgap(10.0);
        Label nameLabel = new Label("Drug Name:");
        grid.add(nameLabel, 0, 0);
        TextField nameField = new TextField();
        grid.add(nameField, 1, 0);
        Button searchButton = new Button("Search");
        grid.add(searchButton, 1, 1);
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        searchButton.setOnAction((e) -> {
            String drugName = nameField.getText();

            Alert alert;
            try {
                Drug drug = this.drugDAO.getDrugByName(drugName);
                if (drug != null) {
                    String var10001 = drug.getDrugName();
                    resultArea.setText("Name: " + var10001 + "\nDescription: " + drug.getDescription() + "\nPrice: " + drug.getPrice() + "\nStock: " + drug.getStock());
                } else {
                    alert = new Alert(AlertType.ERROR, "Couldn't retrieve drug, please check drug name for validity", new ButtonType[0]);
                    alert.showAndWait();
                }
            } catch (SQLException var7) {
                SQLException exx = var7;
                alert = new Alert(AlertType.ERROR, "Error searching for drug: " + exx.getMessage(), new ButtonType[0]);
                alert.showAndWait();
            }

        });
        vbox.getChildren().addAll(new Node[]{grid, resultArea});
        Scene scene = new Scene(vbox, 400.0, 300.0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

