package org.pharmacy.pharmacymgtsys.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is the Welcome screen Frame.
 * It represents the UI to Welcome the user
 *
 * @author Daniel, jonathan, Hannah, felix, Martin, Bright
 * @version 1.0
 */
public class WelcomeScreenFrame extends Application {
    public WelcomeScreenFrame() {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome");

        // Background image
        Image backgroundImage = new Image("file:src/main/resources/img/Pharmacy.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);
        backgroundImageView.setOpacity(0.8); // Slight transparency for the background

        StackPane root = new StackPane();

        // VBox for content with alignment and padding
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(50));
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 15;");

        // Welcome label with styling
        Label welcomeLabel = new Label("Welcome to the Pharmacy Management System");
        welcomeLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-weight: bold;");

        // Additional information label
        Label infoLabel = new Label("Manage your pharmacy operations seamlessly and efficiently.");
        infoLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-font-weight: normal;");

        // Proceed button with styling
        Button proceedButton = new Button("Proceed");
        proceedButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        proceedButton.setOnMouseEntered(e -> proceedButton.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;"));
        proceedButton.setOnMouseExited(e -> proceedButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;"));
        proceedButton.setOnAction(e -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.start(new Stage());
            primaryStage.close();
        });

        // Adding elements to VBox
        vbox.getChildren().addAll(welcomeLabel, infoLabel, proceedButton);

        // Adding VBox and background image to root StackPane
        root.getChildren().addAll(backgroundImageView, vbox);

        // Setting the scene and showing the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
