package project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FirePaaRadEnvTest {
        String player1 = "Thomas";
        String player2 = "Ludde";

        @Test
        public void testConstructor() {
            FirePaaRadEnv FirePaaRad = new FirePaaRadEnv(player1, player2);
            assertEquals("Thomas", FirePaaRad.getCurrentPlayer().toString());
            assertEquals("Ludde", FirePaaRad.getOtherPlayer().toString());
            assertArrayEquals(new Piece[6][7], FirePaaRad.copyBoard());
        }
    
        @Test
        void testFirePaaRaadEnv() {
            FirePaaRadEnv FirePaaRad = new FirePaaRadEnv(player1, player2);
            FirePaaRad.putPiece(0);
            FirePaaRad.switchPlayer();
            FirePaaRad.putPiece(0);
            FirePaaRad.switchPlayer();
            FirePaaRad.putPiece(1);
            FirePaaRad.switchPlayer();
            FirePaaRad.putPiece(1);
            FirePaaRad.switchPlayer();
            assertEquals(5, FirePaaRad.putPiece(3));
            FirePaaRad.switchPlayer();
            assertEquals(5, FirePaaRad.putPiece(4));
            FirePaaRad.switchPlayer();
            assertEquals(4, FirePaaRad.putPiece(3));
            FirePaaRad.switchPlayer();
            assertEquals(3, FirePaaRad.putPiece(3));
            FirePaaRad.switchPlayer();
            assertEquals(2, FirePaaRad.putPiece(3));
            FirePaaRad.switchPlayer();
            assertEquals(1, FirePaaRad.putPiece(3));
            FirePaaRad.switchPlayer();
            assertEquals(0, FirePaaRad.putPiece(3));
            FirePaaRad.switchPlayer();
            assertEquals(4, FirePaaRad.putPiece(4));
            FirePaaRad.switchPlayer();
            assertEquals(3, FirePaaRad.putPiece(4));
            FirePaaRad.switchPlayer();
            assertEquals(2, FirePaaRad.putPiece(4));
            FirePaaRad.switchPlayer();
            assertEquals(1, FirePaaRad.putPiece(4));
            FirePaaRad.switchPlayer();
            assertEquals(0, FirePaaRad.putPiece(4));
            FirePaaRad.switchPlayer();
            assertEquals(false, FirePaaRad.isLegalMove(4));
            assertEquals(false, FirePaaRad.isLegalMove(3));
            assertEquals(true, FirePaaRad.isLegalMove(0));
            assertEquals(true, FirePaaRad.isLegalMove(1));
            assertEquals(true, FirePaaRad.isLegalMove(2));
            assertEquals(true, FirePaaRad.isLegalMove(5));
            assertEquals(false, FirePaaRad.isWinner());
            assertEquals(5, FirePaaRad.putPiece(2));
            assertEquals(true, FirePaaRad.isWinner());
            assertEquals(player1, FirePaaRad.getResult().toString());
        }
    }



