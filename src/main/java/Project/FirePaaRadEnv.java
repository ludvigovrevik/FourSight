package project;

import java.util.Scanner;

public class FirePaaRadEnv {
    private Piece[][] board;
    private Player p1, p2;
    private Player currentPlayer;

    public FirePaaRadEnv(String player1, String player2) {
        this.board = new Piece[6][7];
        this.p1 = new Player(1, player1);
        this.p2 = new Player(2, player2);
        this.currentPlayer = p1;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public boolean isLegalMove(int row, int column) {
        if (this.board[row][column] != null) {
            return false;
        }
        if (row != 5) {
            if (this.board[row + 1][column] == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isWinner() {
        if (checkRightDiagonal() || checkLeftDiagonal() || checkVertical() || checkHorizontal()) {
            return true;
        } else {
            return false;
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

    public boolean putPiece(int row, int column) {
        this.board[row][column] = this.currentPlayer.getPiece();
        if (currentPlayer.equals(p1)) {
            this.currentPlayer = p2;
        } else {
            this.currentPlayer = p1;
        }
        if (isWinner()) {
            System.out.printf("%s won", p1);
            return true;
        }
        return false;
    }

    public boolean hasLegalMoves() {
        for (int collumn = 0; collumn < 7; collumn++) {
            if (board[0][collumn] == null) {
                return true;
            }
        } return false;
    }

    public void PrintBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == null) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print(board[i][j].toString() + " ");
                }
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {

        FirePaaRadEnv env = new FirePaaRadEnv("Ludvig", "Thomas");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            env.PrintBoard();
            int row, column;

            while (true) {
                System.out.println("Input a valid integer for rows");
                if (scanner.hasNextInt()) {
                    row = scanner.nextInt();
                    break;
                } else {
                    System.out.println("That's not a valid number. Please enter an integer.");
                    scanner.next(); // Consume the invalid input
                }
            }

            while (true) {
                System.out.println("Please enter a valid integer for the column.");
                if (scanner.hasNextInt()) {
                    column = scanner.nextInt();
                    break; // Exit the loop once a valid integer is received
                } else {
                    System.out.println("That's not a valid number. Please enter an integer.");
                    scanner.next(); // Consume the invalid input
                }
            }
            if (env.putPiece(6 - row, column - 1)) {
                scanner.close();
                break;
            };
        }
    }
}