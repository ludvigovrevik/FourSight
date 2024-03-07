package project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class FirePaaRadController implements Initializable {
    private String p1, p2;

    @FXML
    private Label result;

    @FXML
    private TextField player1, player2;

    @FXML
    private Text p1Text, p2Text;

    @FXML
    private Button aiButton;

    @FXML
    private GridPane buttonGrid;

    private FirePaaRadEnv firePaaRad;

    private MCTS mcts;

    private GameState state;

    public void initGame() {
        disableButtons();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGame();

    }

    @FXML
    public void playAI() {
        this.p1 = player1.getText();
        this.p2 = "AI";
        firePaaRad = new FirePaaRadEnv(p1, p2);
        mcts = new MCTS();
        resetButtonsText();
    }

    @FXML
    public void enableGame() {
        this.p1 = player1.getText();
        this.p2 = player2.getText();
        firePaaRad = new FirePaaRadEnv(p1, p2);
        resetButtonsText();
    }

    @FXML
    public void resetGame() {
        resetButtonsText();
        this.result.setText("New game started");
        firePaaRad = new FirePaaRadEnv(p1, p2);
    }

    @FXML
    public void disableButtons() {
        for (Node node : buttonGrid.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setDisable(true);
            }
        }
    }

    public void resetButtonsText() {
        for (Node node : buttonGrid.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setText(".");
                button.setDisable(false);
            }
        }
    }

    @FXML
    public void putPiece(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();
        int column = Character.getNumericValue(buttonId.charAt(6)); // This gets Y

        if (firePaaRad.isLegalMove(column)) {
            // GameState tempState = new GameState(firePaaRad);
            // tempState.applyAction(column);
            firePaaRad.putPiece(column); // put piece and update the board accordingly - change the gui
            
            
            // clickedButton.setText(firePaaRad.getCurrentPlayer().getColor());
            // this.result.setText(firePaaRad.getCurrentPlayer().toString() + " has won!");
                
            disableButtons();
        } else {
            firePaaRad.putPiece(mcts.runSimulation(state));
            clickedButton.setText(firePaaRad.getCurrentPlayer().getColor());

            if (!firePaaRad.hasLegalMoves()) {
                disableButtons();
                this.result.setText("It's a tie!");
            }

            GameState state = new GameState(firePaaRad);
            int bestAction = mcts.runSimulation(state);
            firePaaRad.putPiece(bestAction);

            if (!firePaaRad.hasLegalMoves()) {
                disableButtons();
                this.result.setText("It's a tie!");
            }
            }
        }
    {
        this.result.setText("Invalid move buddy");
    }
}