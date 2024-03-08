package project;

import java.util.Scanner;

public class FirePaaRadEnv implements Cloneable, FirePaaRadInterface {
    private Piece[][] board;
    private Player p1, p2;
    private Player currentPlayer, otherPlayer;

    public FirePaaRadEnv(String player1, String player2) {
        this.board = new Piece[6][7];
        this.p1 = new Player(1, player1);
        this.p2 = new Player(2, player2);
        this.currentPlayer = p1;
        this.otherPlayer = p2;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Player getOtherPlayer() {
        return otherPlayer;
    }

    public boolean isLegalMove(int column) {
        for (int row = 5; row >= 0; row--) {
            // Check if the top piece of this column is null (i.e., column is not full)
            if (board[row][column] == null) {
                return true;
            }
        }
        return false;
    }

    public Piece[][] copyBoard() {
        Piece[][] newBoard = new Piece[6][7];

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (this.board[row][col] != null) {
                    newBoard[row][col] = new Piece(this.board[row][col]);
                } else {
                    newBoard[row][col] = null;
                }
            }
        }
        return newBoard;
    }

    @Override
    public Player getResult() {
        if (isWinner()) {
            return this.currentPlayer;
        } else {
            return null;
        }
    }

    @Override
    public boolean isWinner() {
        if (checkRightDiagonal() || checkLeftDiagonal() || checkVertical() || checkHorizontal()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
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

    @Override
    public boolean checkLeftDiagonal() {
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

    @Override
    public boolean checkRightDiagonal() {
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


    @Override
    public boolean checkVertical() {
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

    public int putPiece(int column) {
        for (int row = 5; row >= 0; row--) {
            if (this.board[row][column] == null) {
                this.board[row][column] = this.currentPlayer.getPiece();
                return row;
            }
        }
        throw new IllegalArgumentException("Feil i put piece metoden, fungerte ikke å legge brikke");
    }

    public boolean hasLegalMoves() {
        for (int collumn = 0; collumn < 7; collumn++) {
            if (board[0][collumn] == null) {
                return true;
            }
        }
        return false;
    }

    public void switchPlayer() {
        Player temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
    }

    public void PrintBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == null) {
                    System.out.print("X" + " ");
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
            env.putPiece(column - 1);
            env.switchPlayer();
            if (env.isWinner()) {
                env.PrintBoard();
                env.getCurrentPlayer();
                scanner.close();
                break;
            }
        }
    }
}
