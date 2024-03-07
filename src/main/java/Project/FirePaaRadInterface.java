package project;

public interface FirePaaRadInterface {
   public Player getResult();
   public boolean isWinner();
   public boolean checkHorizontal();
   public boolean checkLeftDiagonal();
   public boolean checkRightDiagonal();
   public boolean checkVertical();
}
