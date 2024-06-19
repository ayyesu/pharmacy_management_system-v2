package org.pharmacy.pharmacymgtsys.auth;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.pharmacy.pharmacymgtsys.dao.UserDAO;
import org.pharmacy.pharmacymgtsys.ui.WelcomeScreenFrame;

public class LoginFrame extends Application {
    private TextField usernameField;
    private PasswordField passwordField;

    public LoginFrame() {
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacy Management System - Login");
        Image backgroundImage = new Image("file:src/main/resources/img/Pharmacy.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(400.0);
        backgroundImageView.setFitHeight(400.0);
        StackPane root = new StackPane();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10.0);
        grid.setVgap(10.0);
        grid.setPadding(new Insets(25.0, 25.0, 25.0, 25.0));
        Label title = new Label("Login to your account");
        title.setFont(Font.font("Arial", 20.0));
        title.setTextFill(Color.BLACK);
        grid.add(title, 0, 0, 2, 1);
        Label usernameLabel = new Label("Username:");
        usernameLabel.setTextFill(Color.BLACK);
        grid.add(usernameLabel, 0, 1);
        this.usernameField = new TextField();
        grid.add(this.usernameField, 1, 1);
        Label passwordLabel = new Label("Password:");
        passwordLabel.setTextFill(Color.BLACK);
        grid.add(passwordLabel, 0, 2);
        this.passwordField = new PasswordField();
        grid.add(this.passwordField, 1, 2);
        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 3);
        loginButton.setOnAction((e) -> {
            String username = this.usernameField.getText();
            String password = this.passwordField.getText();
            UserDAO userDAO = new UserDAO();

            try {
                Alert alertx;
                if (userDAO.validateUser(username, password)) {
                    alertx = new Alert(AlertType.INFORMATION, "Login successful!", new ButtonType[0]);
                    alertx.showAndWait();
                    WelcomeScreenFrame welcomeScreen = new WelcomeScreenFrame();
                    welcomeScreen.start(new Stage());
                    primaryStage.close();
                } else {
                    alertx = new Alert(AlertType.ERROR, "Invalid username or password.", new ButtonType[0]);
                    alertx.showAndWait();
                }
            } catch (SQLException var8) {
                SQLException ex = var8;
                ex.printStackTrace();
                Alert alert = new Alert(AlertType.ERROR, "Database error.", new ButtonType[0]);
                alert.showAndWait();
            }

        });
        root.getChildren().addAll(new Node[]{backgroundImageView, grid});
        Scene scene = new Scene(root, 400.0, 400.0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
