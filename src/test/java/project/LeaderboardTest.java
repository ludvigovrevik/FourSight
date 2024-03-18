package project;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.*;

class LeaderboardTest {

    private static final Path testFile = Paths.get("./test_game_results.txt");
    private Leaderboard leaderboard;

    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(testFile);
        Files.createFile(testFile);
        leaderboard = new Leaderboard(testFile.toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(testFile);
    }

    @Test
    void testImportLeaderboard() {
        assertTrue(leaderboard.getWinners().isEmpty());
    }

    @Test
    void testSaveGameNewWinner() throws IOException {
        leaderboard.saveGame("Ludde");
        String content = Files.readString(testFile);
        assertTrue(content.contains("Ludde"));
    }

    @Test
    void testSaveGameExistingWinner() throws IOException {
        leaderboard.saveGame("Thomas");
        leaderboard.saveGame("Thomas");
        String content = Files.readString(testFile);
        assertTrue(content.contains("Thomas"));
        assertTrue(content.contains("Wins: 2"));
    }
}
