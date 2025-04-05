abstract public class Pawn {
    protected String name;
    protected char symbol;
    protected Move move;

    public Pawn(String name,char symbol) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getName(){ return name;}
    public char getSymbol() { return symbol;}
    public Move getMove(){return move;}
}