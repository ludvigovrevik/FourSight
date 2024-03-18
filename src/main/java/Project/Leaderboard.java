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
    String filePath;

    public Leaderboard() {
        this.filePath = "./game_results.txt";
        importLeaderboard();
    }

    public Leaderboard(String filePath) {
        this.filePath = filePath;
        importLeaderboard();
    }

    public List<Winner> getWinners() {
        return unsortedWinners;
    }

    public void importLeaderboard() {
        try {
            Files.readAllLines(Paths.get(this.filePath))
            .stream()
            .map(line -> line.split("\\s+"))
            .filter(lineSplit -> lineSplit.length >= 4)
            .forEach(lineSplit -> {
                try {
                    String name = lineSplit[1];
                    int score = Integer.parseInt(lineSplit[3]); 
                    unsortedWinners.add(new Winner(name, score)); 
                } catch (NumberFormatException e) {
                    System.err.println("Invalid score format for " + Arrays.toString(lineSplit));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getText() {
    String text = "";
    try {
        List<String> lines = Files.readAllLines(Paths.get(this.filePath));
        for (String line : lines) {
            text += line;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return text;    
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
        try (PrintWriter out = new PrintWriter(new FileWriter(this.filePath, false))) {
            for (Winner winner : sortedWinners) {
                out.println("Winner: " + winner.getName() + " Wins: " + winner.getWins());
            }
        } catch (IOException e) {
            System.out.println("Could not print results to file");
            e.printStackTrace();
        }
    }
}
