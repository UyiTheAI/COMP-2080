/*
 * Sara Mosquera - 101512887
 * Luna Ortega - 101477171
 * Andre Alvares- 101512582
 * Judene Brown - 101503637
 * Omoruyi Oredia - 101496942
 */
public class Player {
    private String name;
    // The symbol the player uses on the board (e.g., 'X' or 'O').
    private char symbol;
    // Constructor to initialize a player with a name and symbol.
    public Player(String name, char sym) {
        this.name = name;
        this.symbol = sym;
    }

    // Getter method to retrieve the player's name.
    public String getName() {
        return name;
    }

    // Getter method to retrieve the player's symbol.
    public char getSymbol() {
        return symbol;
    }
}
