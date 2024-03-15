package project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    String player1 = "Thomas";
        String player2 = "Ludde";

        @Test
        public void testConstructor() {
            FirePaaRadEnv FirePaaRadEnv = new FirePaaRadEnv(player1, player2);
            assertEquals("Thomas", FirePaaRadEnv.getCurrentPlayer().toString());
            assertEquals("Ludde", FirePaaRadEnv.getOtherPlayer().toString());
        }
    
        @Test
        void testPlayer() {
            
            
    
        }
}
