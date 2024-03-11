package project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.Node;

public class InsertNameController implements Initializable {

    @FXML
    Text p1Text, p2Text;

    @FXML
    TextField player1, player2;

    private boolean playAI = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // knappe id du skal hide: Ok, player2
    public void buttonClicked(String buttonClicked) {
        if (buttonClicked.equals("playAI")) {
            p2Text.setVisible(false);
            p2Text.setManaged(false);
            player2.setVisible(false);
            player2.setManaged(false);
            p1Text.setLayoutY(133);
            player1.setLayoutY(137);
            playAI = true;
        }
    }

    @FXML
    public void enableGame(ActionEvent event) throws Exception {
        try { 
            if (player1.getText().isEmpty()) {
                // skriv inn at bruker må skrive inn spillernavn
                player1.setPromptText("Please enter a name");
                throw new IllegalArgumentException("Skriv inn brukernavn"); 
            }
            
            if (playAI == false) { 
                // if textField is emty - thorw new illegal.... (må inserte name)
                if (player2.getText().isEmpty()) {
                    player2.setPromptText("Please enter a name");
                    // skriv inn at bruker må skrive inn spillernavn
                    throw new IllegalArgumentException();
                }
                
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FirePaaRadApp.fxml"));
            Parent nextSceneRoot = loader.load();
            FirePaaRadController controller = loader.getController();
            controller.initialize(null, null);
            if (playAI) {
                controller.playAI(player1.getText());
            } else {
                controller.enableGame(player1.getText(), player2.getText());
            }
            Scene nextScene = new Scene(nextSceneRoot);
            Stage stage = (Stage) ((Node) event.getSource()).getParent().getScene().getWindow();
            stage.setScene(nextScene);
            stage.show();
        } catch (IllegalArgumentException e){
            System.out.println("dumbass");
        }     
    }

}
