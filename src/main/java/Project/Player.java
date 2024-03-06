package project;

public class Player implements FirePaaRadInterface{
    private Piece piece;
    private String name;

    public Player(int i, String name) {
        if (i == 1) {
            piece = new Piece("R");    
        } else if (i == 2) {
            piece = new Piece("Y");   
        }
        this.name = name;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String getColor() {
        return piece.getColor();
    }

    @Override
    public String toString() {
        return name;
    }
}
