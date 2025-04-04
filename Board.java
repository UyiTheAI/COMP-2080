public class Board {

    static final int SIZE = 9;
    public char[][] board;


    public Board() {
        board = new char[SIZE][SIZE];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '.';
            }
        }
    }


    public void displayBoard() {
        // Print column labels
        System.out.print("  ");
        for (char colLabel = 'a'; colLabel < 'a' + SIZE; colLabel++) {
            System.out.print(colLabel + " ");
        }
        System.out.println();

        // Print rows with labels
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " "); // Row label
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean makeMove(String position, char symbol) {
        // Parse position (e.g., "e5")
        int col = position.charAt(0) - 'a'; // Convert column letter to index
        int row = Character.getNumericValue(position.charAt(1)) - 1; // Convert row number to index

        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == '.') {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }
    
    public void undoMove(String position) {
        int col = position.charAt(0) - 'a';
        int row = Character.getNumericValue(position.charAt(1)) - 1;

        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE) {
            board[row][col] = '.'; // Reset the position to empty
        }
    }

    public boolean checkWin(char symbol) {
        // Check rows, columns, and diagonals for five in a row
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkDirection(i, j, symbol, 1, 0) ||  // Horizontal
                    checkDirection(i, j, symbol, 0, 1) ||  // Vertical
                    checkDirection(i, j, symbol, 1, 1) ||  // Diagonal \
                    checkDirection(i, j, symbol, 1, -1)) { // Diagonal /
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDirection(int row, int col, char symbol, int dRow, int dCol) {
        int count = 0;
        for (int k = 0; k < 5; k++) { // Check for five consecutive symbols
            int newRow = row + k * dRow;
            int newCol = col + k * dCol;

            if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE && board[newRow][newCol] == symbol) {
                count++;
            } else {
                break;
            }
        }
        return count == 5;
    }

    public boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '.') return false; // If there's an empty spot, it's not a draw
            }
        }
        return true; // No empty spots, it's a draw
    }

}