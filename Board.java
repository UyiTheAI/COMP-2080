/*
 * Sara Mosquera - 101512887
 * Luna Ortega - 101477171
 * Andre Alvares- 101512582
 * Judene Brown - 101503637
 * Omoruyi Oredia - 101496942
 */
public class Board {
    static final int size = 9;
    public char[][] board;

    // Constructor initializes the board
    public Board() {
        board = new char[size][size];
        initializeBoard();
    }

    // Fill the board with '.' to indicate empty cells
    public void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '.';
            }
        }
    }

    // Displays the board with row numbers and column letters
    public void displayBoard() {
        System.out.print("  ");
        for (char colLabel = 'a'; colLabel < 'a' + size; colLabel++) {
            System.out.print(colLabel + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");  // Print row number
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");  // Print cell value
            }
            System.out.println();
        }
    }

    // Places a move on the board if the position is valid and empty
    public boolean makeMove(String position, char sym) {
        int col = position.charAt(0) - 'a';  // Convert letter to column index
        int row = Character.getNumericValue(position.charAt(1)) - 1;  // Convert number to row index

        if (row >= 0 && row < size && col >= 0 && col < size && board[row][col] == '.') {
            board[row][col] = sym;
            return true;
        }
        return false;
    }

    // Reverts a move by clearing the given position
    public void unmakeMove(String position) {
        int col = position.charAt(0) - 'a';
        int row = Character.getNumericValue(position.charAt(1)) - 1;

        if (row >= 0 && row < size && col >= 0 && col < size) {
            board[row][col] = '.'; 
        }
    }

    // Checks if the given player has won the game
    public boolean checkWin(char sym) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (checkDirection(i, j, sym, 1, 0) ||  // vertical
                    checkDirection(i, j, sym, 0, 1) ||  // horizontal
                    checkDirection(i, j, sym, 1, 1) ||  // diagonal \
                    checkDirection(i, j, sym, 1, -1)) { // diagonal /
                    return true;
                }
            }
        }
        return false;
    }

    // Helper method to check for 5 in a row in a specific direction
    private boolean checkDirection(int row, int col, char sym, int dRow, int dCol) {
        int count = 0;
        for (int k = 0; k < 5; k++) { 
            int newRow = row + k * dRow;
            int newCol = col + k * dCol;

            if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size && board[newRow][newCol] == sym) {
                count++;
            } else {
                break;
            }
        }
        return count == 5;
    }

    // Checks if the board is full (i.e., a draw)
    public boolean isDraw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '.') return false;  // Empty spot found, not a draw
            }
        }
        return true; 
    }
}