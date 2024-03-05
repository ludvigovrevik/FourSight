package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FirePaaRadApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Example App");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("FirePaaRadApp.fxml"))));
        primaryStage.show();
    }

}