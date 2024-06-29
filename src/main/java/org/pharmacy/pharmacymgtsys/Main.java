package org.pharmacy.pharmacymgtsys;

import javafx.application.Application;
import javafx.stage.Stage;
import org.pharmacy.pharmacymgtsys.auth.LoginFrame;


/**
 * This is the main class as well as the starter of the application.
 * It starts the application from thr LoginFrame
 *
 * @author Daniel, jonathan, Hannah, felix, Martin, Bright
 * @version 1.0
 */
public class Main extends Application {

    public void start(Stage primaryStage) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
