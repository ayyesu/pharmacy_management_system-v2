package org.pharmacy.pharmacymgtsys;

import javafx.application.Application;
import javafx.stage.Stage;
import org.pharmacy.pharmacymgtsys.auth.LoginFrame;

public class Main extends Application {

    public void start(Stage primaryStage) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
