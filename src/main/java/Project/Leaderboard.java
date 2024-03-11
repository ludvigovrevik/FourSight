package project;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {
    List<Winner> unsortedWinners = new ArrayList<>();


    public Leaderboard() {
        importLeaderboard();
    }

    public void importLeaderboard() {
        String filePath = "./game_results.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] lineSplit = line.split("\\s+"); 
                if (lineSplit.length >= 4) { 
                    String name = lineSplit[1];
                    int score;
                    try {
                        score = Integer.parseInt(lineSplit[3]);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid score format for " + Arrays.toString(lineSplit));
                        continue;
                    }
                    Winner newWinner = new Winner(name);
                    newWinner.setWins(score);
                    unsortedWinners.add(newWinner);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGame(String name) {
        boolean found = false;
        for (Winner w : unsortedWinners) {
            if (w.getName().equals(name)) {
                w.setWins(w.getWins() + 1);
                found = true;
                break;
            }
        }
        if (!found) {
            Winner newWinner = new Winner(name, 1); 
            unsortedWinners.add(newWinner);
        }
        List<Winner> sortedWinners = sortWinners(new ArrayList<>(unsortedWinners)); 
        printResults(sortedWinners);
    }

    public List<Winner> sortWinners(List<Winner> winners) {
        Collections.sort(winners, Comparator.comparingInt(Winner::getWins).reversed());
        return winners;
    }

    public void printResults(List<Winner> sortedWinners) {
        try (PrintWriter out = new PrintWriter(new FileWriter("./game_results.txt", false))) {
            for (Winner winner : sortedWinners) {
                out.println("Winner: " + winner.getName() + " Wins: " + winner.getWins());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to save the game result.");
            e.printStackTrace();
        }
    }
}
