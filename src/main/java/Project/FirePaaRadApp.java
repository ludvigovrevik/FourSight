package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FirePaaRadApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Fire på rad");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/project/FrontPage.fxml"))));
        
        Image applicationIcon = new Image(getClass().getResourceAsStream("/project/icon.png"));
        primaryStage.getIcons().add(applicationIcon);
        primaryStage.show();
    }

}