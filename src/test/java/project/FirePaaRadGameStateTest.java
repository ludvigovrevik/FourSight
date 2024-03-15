package project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FirePaaRadGameStateTest {
        String player1 = "Thomas";
        String player2 = "Ludde";
        FirePaaRadEnv firePaaRad = new FirePaaRadEnv(player1, player2);
        MCTS Mcts = new MCTS();

        @Test
        public void testConstructor() {
            assertEquals("Thomas", firePaaRad.getCurrentPlayer().toString());
            assertEquals("Ludde", firePaaRad.getOtherPlayer().toString());
        }
    
        @Test
        void testGameState() {
            firePaaRad.putPiece(0);
            firePaaRad.putPiece(0);
            firePaaRad.putPiece(1);
            firePaaRad.putPiece(1);
            firePaaRad.putPiece(2);
            firePaaRad.putPiece(2);
            GameState initState = new GameState(firePaaRad);
            assertEquals(firePaaRad.getCurrentPlayer(), initState.getCurrentPlayer());
            assertEquals(firePaaRad.getOtherPlayer(), initState.getOtherPlayer());
            assertEquals(false, initState.isWinner());
            assertEquals(3, Mcts.runSimulation(initState));
            int action = Mcts.runSimulation(initState);
            firePaaRad.putPiece(action);
            assertEquals(true, firePaaRad.isWinner());
            assertEquals(player1, initState.applyAction(action).getWinner().toString());
        }
}
            
            