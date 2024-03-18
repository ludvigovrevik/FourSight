package project;

public class Piece implements Cloneable {
    private String color;

    public Piece(String color) {
        this.color = color;
    }

    // for deep copying
    public Piece(Piece pieceToCopy) {
        this.color = pieceToCopy.color;
    }

    
    public String getColor() {
        return color;
    }
    
    @Override
    public String toString() {
        return color;
    }
    
    // for deep copying
    @Override
    public Piece clone() {
        try {
            return (Piece) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}