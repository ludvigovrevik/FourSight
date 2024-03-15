package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MCTSTest {
        String player1 = "Thomas";
        String player2 = "Ludde";
        MCTS Mcts = new MCTS();
        FirePaaRadEnv firePaaRad = new FirePaaRadEnv(player1, player2);


        @Test
        public void testWinningMoveVertical() {
            firePaaRad.putPiece(0);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(1);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(0);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(1);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(0);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(1);
            firePaaRad.switchPlayer();
            GameState initState = new GameState(firePaaRad);
            assertEquals(0, Mcts.runSimulation(initState));
        }

        @Test
        public void testWinningMoveHorisontal() {
            firePaaRad.putPiece(0);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(1);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(0);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(1);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(0);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(1);
            firePaaRad.switchPlayer();
            GameState initState = new GameState(firePaaRad);
            assertEquals(0, Mcts.runSimulation(initState));
        }
    
        @Test
        void testWinningMoveDiagonal() {
            firePaaRad.putPiece(0);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(1);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(1);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(2);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(2);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(3);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(2);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(3);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(3);
            firePaaRad.switchPlayer();
            firePaaRad.putPiece(0);
            firePaaRad.switchPlayer();
            assertEquals(true, firePaaRad.hasLegalMoves());
            GameState state = new GameState(firePaaRad);
            assertEquals(3, Mcts.runSimulation(state));
            int action = Mcts.runSimulation(state);
            assertEquals(null, firePaaRad.getResult());
            firePaaRad.putPiece(action);
            assertEquals(true, firePaaRad.isWinner());
            assertEquals(player1, firePaaRad.getResult().toString());
        }
}
