package org.pharmacy.pharmacymgtsys.auth;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.pharmacy.pharmacymgtsys.dao.UserDAO;
import org.pharmacy.pharmacymgtsys.ui.WelcomeScreenFrame;

/**
 * This represents the Login Frame.
 * It provides an interface for user validation.
 *
 * @author Daniel, jonathan, Hannah, felix, Martin, Bright
 * @version 1.0
 */
public class LoginFrame extends Application {
    /**
     * This represents username gotten from the user.
     */
    private TextField usernameField;
    /**
     * This represents password gotten from the user.
     */
    private PasswordField passwordField;

    public LoginFrame() {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacy Management System - Login");

        // Load and style the background image
        Image backgroundImage = new Image("file:src/main/resources/img/Pharmacy.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(400);
        backgroundImageView.setFitHeight(400);

        // Create an overlay to lower the contrast of the background image
        Rectangle overlay = new Rectangle(400, 400);
        overlay.setFill(Color.rgb(0, 0, 0, 0.5));

        StackPane root = new StackPane();

        // Create and style the form
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label title = new Label("Login to your account");
        title.setFont(Font.font("Verdana", 24));
        title.setTextFill(Color.WHITE);
        grid.add(title, 0, 0, 2, 1);

        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Verdana", 14));
        usernameLabel.setTextFill(Color.WHITE);
        grid.add(usernameLabel, 0, 1);

        this.usernameField = new TextField();
        grid.add(this.usernameField, 1, 1);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Verdana", 14));
        passwordLabel.setTextFill(Color.WHITE);
        grid.add(passwordLabel, 0, 2);

        this.passwordField = new PasswordField();
        grid.add(this.passwordField, 1, 2);

        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font("Verdana", 14));
        grid.add(loginButton, 1, 3);

        loginButton.setOnAction(e -> {
            String username = this.usernameField.getText();
            String password = this.passwordField.getText();
            UserDAO userDAO = new UserDAO();

            try {
                Alert alertx;
                if (userDAO.validateUser(username, password)) {
                    alertx = new Alert(AlertType.INFORMATION, "Login successful!", ButtonType.OK);
                    alertx.showAndWait();
                    WelcomeScreenFrame welcomeScreen = new WelcomeScreenFrame();
                    welcomeScreen.start(new Stage());
                    primaryStage.close();
                } else {
                    alertx = new Alert(AlertType.ERROR, "Invalid username or password.", ButtonType.OK);
                    alertx.showAndWait();
                }
            } catch (SQLException var8) {
                var8.printStackTrace();
                Alert alert = new Alert(AlertType.ERROR, "Database error.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        root.getChildren().addAll(backgroundImageView, overlay, grid);
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
