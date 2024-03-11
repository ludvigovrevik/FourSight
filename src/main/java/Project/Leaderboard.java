package project;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Leaderboard {
    List<Winner> unsorrtedWinners = new ArrayList<Winner>();
    Winner winner;
    public Leaderboard() { 
        this.winner = null;
    }

    public void saveGame(String name) {   
        for (Winner w : unsorrtedWinners) {
            if (w.getName().equals(name)) {
                this.winner = w;
                //break;
            }
        }
        if (this.winner != null) {
            this.winner.setWins(winner.getWins()+ 1);
        } else {
            Winner newWinner = new Winner(name);
            unsorrtedWinners.add(newWinner);
            winner = null;
        }
        printResults(sortWinners(unsorrtedWinners));
        System.out.println("Unsorted: " + unsorrtedWinners);
        System.out.println("Sorted: " + sortWinners(unsorrtedWinners));
    }

    public List<Winner> sortWinners(List<Winner> unsortedWinners) {
    Collections.sort(unsortedWinners, new Comparator<Winner>() {
        @Override
        public int compare(Winner w1, Winner w2) {
            return Integer.compare(w2.getWins(), w1.getWins()); 
        }
    });
    return unsortedWinners;
}
    
    public void printResults(List<Winner> sortedWinners) {
        try (PrintWriter out = new PrintWriter(new FileWriter("game_results.txt", false))) {
            for (Winner winner : sortedWinners) {
                out.println("Winner: " + winner.getName() + " Wins: " + winner.getWins()); 
            }
     
        } catch (IOException e) {
            System.out.println("An error occurred while trying to save the game result.");
            e.printStackTrace();
        }
        }
    

    



}

