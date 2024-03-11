package project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;


public class FrontPageController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void playAgainstAI(ActionEvent event) throws Exception {
        String buttonClicked = ((Button) event.getSource()).getId();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InsertName.fxml"));
        Parent nextSceneRoot = loader.load();

        InsertNameController controller = loader.getController();
        controller.buttonClicked(buttonClicked);

        Scene nextScene = new Scene(nextSceneRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getParent().getScene().getWindow();
        stage.setScene(nextScene);
        stage.show();
    }

    @FXML
    public void oneVsOne(ActionEvent event) throws Exception {
        String buttonClicked = ((Button) event.getSource()).getId();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InsertName.fxml"));
        Parent nextSceneRoot = loader.load();

        InsertNameController controller = loader.getController();
        controller.buttonClicked(buttonClicked);

        Scene nextScene = new Scene(nextSceneRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getParent().getScene().getWindow();
        stage.setScene(nextScene);
        stage.show();
    }
}
