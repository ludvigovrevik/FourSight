package project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlayerTest {
        String player1 = "Thomas";
        String player2 = "Ludde";
        FirePaaRadEnv FirePaaRadEnv = new FirePaaRadEnv(player1, player2);
            
    
        @Test
        void testPlayerPieces() {
        assertEquals("Thomas", FirePaaRadEnv.getCurrentPlayer().toString());
        assertEquals("Ludde", FirePaaRadEnv.getOtherPlayer().toString());

        Player p1 = FirePaaRadEnv.getCurrentPlayer();
        Player p2 = FirePaaRadEnv.getOtherPlayer();

        assertEquals("R", p1.getColor().toString());
        assertEquals("Y", p2.getColor().toString());

        assertEquals("R", p1.getPiece().toString());
        assertEquals("Y", p2.getPiece().toString());

        assertEquals("R", p1.getPiece().toString());
        assertEquals("Y", p2.getPiece().toString());
        }
}
