package project;

import java.util.ArrayList;
import java.util.List;

public class GameState implements Cloneable {
    private Piece[][] board;
    private Player currentPlayer, otherPlayer;

    public GameState(Player currentPlayer, Player otherPlayer) {
        this.board = new Piece[6][7];
        this.currentPlayer = currentPlayer;
        this.otherPlayer = otherPlayer;
    }

    public GameState(FirePaaRadEnv env) {
        this.board = new Piece[6][7];
        currentPlayer = env.getCurrentPlayer();
        otherPlayer = env.getOtherPlayer();
        this.board = env.copyBoard();
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Integer> legalActions() {
        List<Integer> legalMoves = new ArrayList<>();

        for (int col = 0; col < 7; col++) {
            // Check if the top piece of this column is null (i.e., column is not full)
            if (board[0][col] == null) {
                legalMoves.add(col); // Add column index as a legal move
            }
        }
        return legalMoves;
    }

    public GameState applyAction(int column) {
        for (int row = 5; row >= 0; row--) {
            if (this.board[row][column] == null) {
                this.board[row][column] = this.currentPlayer.getPiece();
                switchPlayer();
                return this;
            }
        }
        // not nessessarry, because when calling this function, we first check if it is
        // a legal one.
        return this;
    }

    public boolean isWinner() {
        if (checkRightDiagonal() || checkLeftDiagonal() || checkVertical() || checkHorizontal()) {
            return true;
        } else {
            return false;
        }
    }

    public Player getResult(Player currentPlayer) {
        if (isWinner()) {
            if (currentPlayer.equals(this.currentPlayer)) {
                return this.otherPlayer;
            } else if (currentPlayer.equals(this.otherPlayer)) {
                return this.currentPlayer;
                }
            }
            return null;
        }

    public Player getResult() {
        if (isWinner()) {
            return this.currentPlayer;
        } else {
            return null;
            }
        }

    public boolean checkHorizontal() {
        for (int row = 0; row < 6; row++) {
            int red = 0;
            int yellow = 0;
            for (int collumn = 0; collumn < 7; collumn++) {
                if (this.board[row][collumn] == null) {
                    red = 0;
                    yellow = 0;
                } else if (this.board[row][collumn].getColor().equals("R")) {
                    red++;
                    yellow = 0;
                } else if (this.board[row][collumn].getColor().equals("Y")) {
                    yellow++;
                    red = 0;
                }
                if (red == 4 || yellow == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLeftDiagonal() {
        for (int row = 0; row < 3; row++) {
            for (int col = 3; col < 7; col++) {
                if (board[row][col] != null &&
                        board[row][col].equals(board[row + 1][col - 1]) &&
                        board[row][col].equals(board[row + 2][col - 2]) &&
                        board[row][col].equals(board[row + 3][col - 3])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkRightDiagonal() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] != null &&
                        board[row][col].equals(board[row + 1][col + 1]) &&
                        board[row][col].equals(board[row + 2][col + 2]) &&
                        board[row][col].equals(board[row + 3][col + 3])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical() {
        for (int column = 0; column < 7; column++) {
            int yellow = 0;
            int red = 0;
            for (int row = 0; row < 6; row++) {
                if (this.board[row][column] == null) {
                    red = 0;
                    yellow = 0;
                } else if (this.board[row][column].getColor().equals("R")) {
                    red++;
                    yellow = 0;
                } else if (this.board[row][column].getColor().equals("Y")) {
                    yellow++;
                    red = 0;
                }
                if (red == 4 || yellow == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public void switchPlayer() {
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
    }

    public boolean isTerminal() {
        if (isWinner() || legalActions().size() == 0) {
            return true;
        }
        return false;
    }

    // Method to clone the GameState deeply if needed for simulation
    @Override
    public GameState clone() {
        try {
            GameState cloned = (GameState) super.clone(); // Shallow copy
            cloned.board = new Piece[6][7]; // Initialize a new board for the clone

            // Deep copy the board
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    if (this.board[i][j] != null) { // Only copy if there is a piece present
                        cloned.board[i][j] = this.board[i][j].clone(); // Assuming Piece also implements a proper clone
                                                                       // method
                    }
                }
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can ignore this if you're sure clone is supported
        }
    }
}