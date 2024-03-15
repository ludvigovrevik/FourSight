package project;

public class Player {
    private Piece piece;
    private String name;

    public Player(int i, String name) {
        if (i == 1) {
            piece = new Piece("R");    
        } else if (i == 2) {
            piece = new Piece("Y");   
        }
        if (name == null) {
            System.out.println("Name i Player() er " + name);
        }
        this.name = name;
    }

    public Piece getPiece() {
        return piece;
    }

    public String getColor() {
        return piece.getColor();
    }

    @Override
    public String toString() {
        return name;
    }
}
