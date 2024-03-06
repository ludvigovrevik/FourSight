package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FirePaaRadController {
    @FXML
    Label result;

    private FirePaaRadEnv firePaaRad;

    public void initGame() {
        firePaaRad = new FirePaaRadEnv("Ludde", "Thomas");
    }

    @FXML
    public void spill() {
        try {
            initGame();
            this.result.setText("New game started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void putPiece(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();
        // Assuming your button IDs follow a consistent naming convention,
        // like "buttonXY" where X is the row and Y is the column.
        int row = Character.getNumericValue(buttonId.charAt(6)); // This gets X
        int column = Character.getNumericValue(buttonId.charAt(7)); // This gets Y
        if (firePaaRad.isLegalMove(row, column)) {
            firePaaRad.putPiece(row, column);
            // Update the button text to reflect the move
            clickedButton.setText(firePaaRad.getCurrentPlayer().getColor());
        }
        else {
            this.result.setText("Invalid move buddy");
        }
    }
}