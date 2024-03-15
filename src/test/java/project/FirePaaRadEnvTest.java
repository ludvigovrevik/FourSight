package project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FirePaaRadEnvTest {
        String player1 = "Thomas";
        String player2 = "Ludde";

        @Test
        public void testConstructor() {
            FirePaaRadEnv env = new FirePaaRadEnv(player1, player2);
            assertEquals("Thomas", env.getCurrentPlayer().toString());
            assertEquals("Ludde", env.getOtherPlayer().toString());
            assertArrayEquals(new Piece[6][7], env.copyBoard());
        }
    
        @Test
        @DisplayName("Skjekk om det fungerer å bytte spiller")
        void testSwitchPlayer() {
            FirePaaRadEnv env = new FirePaaRadEnv(player1, player2);
            env.putPiece(0);
            env.switchPlayer();
            assertEquals("Ludde", env.getCurrentPlayer().toString());
        }

        @Test
        @DisplayName("Skjekk at den skjekker diagonal")
        void testDiagonal() {
                FirePaaRadEnv env = new FirePaaRadEnv(player1, player2);
                env.putPiece(0);
                env.switchPlayer();
                env.putPiece(1);
                env.switchPlayer(); 
                env.putPiece(1);
                env.switchPlayer();
                env.putPiece(2);
                env.switchPlayer(); 
                env.putPiece(2);
                env.switchPlayer();
                env.putPiece(3);
                env.switchPlayer(); 
                env.putPiece(2); 
                env.switchPlayer(); 
                env.putPiece(3); 
                env.switchPlayer(); 
                env.putPiece(3);  
                env.switchPlayer(); 
                env.putPiece(4);
                env.switchPlayer(); 
                assertFalse(env.checkLeftDiagonal());
                env.putPiece(3);
                assertTrue(env.checkLeftDiagonal());
                assertFalse(env.checkRightDiagonal());   
        }

        @Test
        @DisplayName("Skjekk at den skjekker vertikal")
        void testVertikal() {
            FirePaaRadEnv env = new FirePaaRadEnv(player1, player2);
            env.putPiece(0);
            env.switchPlayer();
            env.putPiece(1);
            env.switchPlayer(); 
            env.putPiece(0);
            env.switchPlayer();
            env.putPiece(1);
            env.switchPlayer(); 
            env.putPiece(0);
            env.switchPlayer();
            env.putPiece(1);
            env.switchPlayer(); 
            assertFalse(env.checkVertical());
            env.putPiece(0);
            assertTrue(env.checkVertical());
        }

        @Test
        @DisplayName("Skjekk at den skjekker horisontal")
        void testHorisontal() {
            FirePaaRadEnv env = new FirePaaRadEnv(player1, player2);
            env.putPiece(0);
            env.switchPlayer();
            env.putPiece(0);
            env.switchPlayer(); 
            env.putPiece(1);
            env.switchPlayer();
            env.putPiece(1);
            env.switchPlayer(); 
            env.putPiece(2);
            env.switchPlayer();
            env.putPiece(2);
            env.switchPlayer(); 
            assertFalse(env.checkHorizontal());
            env.putPiece(3);
            assertTrue(env.checkHorizontal());
        }

        @Test
        @DisplayName("Skjekk at riktig person vinner")
        void testGetResult() {
            FirePaaRadEnv env = new FirePaaRadEnv(player1, player2);
            env.putPiece(0);
            env.switchPlayer();
            env.putPiece(1);
            env.switchPlayer(); 
            env.putPiece(0);
            env.switchPlayer();
            env.putPiece(1);
            env.switchPlayer(); 
            env.putPiece(0);
            env.switchPlayer();
            env.putPiece(1);
            env.switchPlayer(); 
            assertNull(env.getResult());
            env.putPiece(0);
            assertEquals("Thomas", env.getResult().toString());
        }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // @Before
    // public void setUpStreams() {
    //     System.setOut(new PrintStream(outContent));
    // }

    // @After
    // public void restoreStreams() {
    //     System.setOut(originalOut);
    // }


        // @Test
        // @DisplayName("Skjekk printBoard") 
        // void testRestartGame() {
        //     String noBoard = "";
        //     String board1 = 
        //     "X X X X X X X\n" + 
        //     "X X X X X X X\n" + 
        //     "X X X X X X X\n" + 
        //     "X X X X X X X\n" + 
        //     "X X X X X X X\n" +
        //     "X X X X X X X\n";
        //     FirePaaRadEnv env = new FirePaaRadEnv(player1, player2);
        //     final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        //     System.setOut(new PrintStream(outContent));
        //     assertEquals(noBoard, outContent.toString());
        //     env.PrintBoard();
        //     String actualOutput = outContent.toString().replace("\r\n", "\n");
        //     assertEquals(board1, actualOutput);
        //     env.putPiece(0);
        //     env.switchPlayer();
        //     env.putPiece(1);
        //     env.switchPlayer(); 
        //     env.PrintBoard();
        // }

        


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



