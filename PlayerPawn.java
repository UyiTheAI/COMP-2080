public class PlayerPawn extends Pawn{

    public PlayerPawn(String name, char symbol) {
        super(name, symbol);
    }

    public boolean setMovePosition(String movePlayer, int sizeBoard, boolean isBoardEmpty){
        if (movePlayer == null || movePlayer.length() != 2) return false;
        char colChar = Character.toLowerCase(movePlayer.charAt(0));
    int col = colChar - 'a';

    char rowChar = movePlayer.charAt(1);
    if (!Character.isDigit(rowChar)) return false;

    int row = Character.getNumericValue(rowChar) - 1;

    if ((row >= 0 && row < sizeBoard) && (col >= 0 && col < sizeBoard) && isBoardEmpty) {
        move = new Move(row, col); // Assuming 'move' is a class-level variable
        return true;
    }

    return false;
    }

    public String getName(){ return name;}
    public char getSymbol() { return symbol;}
    public Move getMove(){ return move;}

}