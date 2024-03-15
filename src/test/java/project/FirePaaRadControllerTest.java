package project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FirePaaRadControllerTest {
        String player1 = "Thomas";
        String player2 = "Ludde";
        FirePaaRadController controller = new FirePaaRadController();

        @Test
        public void testConstructor() {
            FirePaaRadEnv FirePaaRadEnv = new FirePaaRadEnv(player1, player2);
            assertEquals("Thomas", FirePaaRadEnv.getCurrentPlayer().toString());
            assertEquals("Ludde", FirePaaRadEnv.getOtherPlayer().toString());
        }
    
        @Test
        void testFirePaaRaadController() {
            controller.playAI("player1");
            
    
        }
    }

