abstract public class Pawn {
    protected String name;
    protected char symbol;
    protected Move move;

    public Pawn(char symbol) {
        this.symbol = symbol;
    }

    public String getName(){ return name;}
    public char getSymbol() { return symbol;}
    public Move getMove(){return move;}
}