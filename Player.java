public class Player {

    private String name;
    private char symbol;

    public Player(String name, char sym) {
        this.name = name;
        this.symbol = sym;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}