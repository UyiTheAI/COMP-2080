/*
 * Sara Mosquera - 101512887
 * Luna Ortega - 101477171
 * Andre Alvares- 101512582
 * Judene Brown - 101503637
 * Omoruyi Oredia - 101496942
 */
import java.util.ArrayList;
import java.util.List;
public class Board {

    public static final int SIZE = 9;
    public char[][] board;

    // Constructor initializes the board
    public Board() {
        board = new char[SIZE][SIZE];
        initializeBoard();
    }

    // Fill the board with '.' to indicate empty cells
    public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '.';
            }
        }
    }

    // Displays the board with row numbers and column letters

    public void displayBoard() {
        System.out.print("  ");
        for (char colLabel = 'a'; colLabel < 'a' + SIZE; colLabel++) {
            System.out.print(colLabel + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");  // Print row number
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");  // Print cell value
            }
            System.out.println();
        }
    }

    // Makes move in board
    public void makeMove(Pawn pawn) {
        board[pawn.getMove().row][pawn.getMove().col] = pawn.getSymbol();
    }
    
    // Undo a move in the board.
    public void undoMove(Pawn pawn) {
        board[pawn.getMove().row][pawn.getMove().col] = '.';
    }

    // Checks if someone won.
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

    // Function used to cound amount of the same symbol in the board. (Max 5)
    private boolean checkDirection(int row, int col, char symbol, int dRow, int dCol) {
        int count = 0;
        for (int k = 0; k < 5; k++) { 
            int newRow = row + k * dRow;
            int newCol = col + k * dCol;

            if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE && board[newRow][newCol] == sym) {
                count++;
            } else {
                break;
            }
        }
        return count == 5;
    }

    public List<Move> getAvailableMoves() {
            List<Move> moves = new ArrayList<>();
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == '.')
                        moves.add(new Move(i, j));
                }
            }
            return moves;
    }

    //  Checks if someone won or if there is a draw
    public boolean isGameOver() {
        return checkWin('X') || checkWin('O') || getAvailableMoves().isEmpty();
    }



    // Checks if the board is full (i.e., a draw)
    public boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '.') return false;  // Empty spot found, not a draw
            }
        }
        return true; 
    }

    // Checks if a specific cell is empty
    public boolean isCellEmpty(String cell) {
        if (cell == null || cell.length() != 2) return false;
    
        char colChar = Character.toLowerCase(cell.charAt(0));
        int col = colChar - 'a';
    
        char rowChar = cell.charAt(1);
        if (!Character.isDigit(rowChar)) return false;
    
        int row = Character.getNumericValue(rowChar) - 1;
    
        if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
            return board[row][col] == '.';
        }
    
        return false;
    }

}