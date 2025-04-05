public class PlayerPawn extends Pawn{
    private String name;
    private char symbol;
    public Move move;

    public PlayerPawn(String name, char symbol) {
        super(symbol);
        this.name = name;
    }

    public boolean setMovePosition(String movePlayer, int sizeBoard, boolean isBoardEmpty){
        int col = movePlayer.charAt(0) - 'a'; // Convert column letter to index
        int row = Character.getNumericValue(movePlayer.charAt(1)) - 1; // Convert row number to index
        
        if ((row >= 0 && row < sizeBoard) && (col >= 0) && (col < sizeBoard) && isBoardEmpty) {
            move = new Move(row, col);
            return true;
        }
        return false;
    }

    public String getName(){ return name;}
    public char getSymbol() { return symbol;}
    public Move getMove(){ return move;}

}