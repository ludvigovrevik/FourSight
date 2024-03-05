package project;

public class Piece implements FirePaaRadInterface {
    private String color;
    
    public Piece(String color) {
        this.color = color;       
    }

    @Override
    public String toString() {
        return color;
    }

    @Override
    public String getColor() {
        return color;
    }
}