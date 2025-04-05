import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int SIZE = 9;
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

    public void makeMove(Pawn pawn) {
        board[pawn.getMove().row][pawn.getMove().col] = pawn.getSymbol();
    }
    
    public void undoMove(Pawn pawn) {
        board[pawn.getMove().row][pawn.getMove().col] = '.';
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

    public boolean isGameOver() {
        return checkWin('X') || checkWin('O') || getAvailableMoves().isEmpty();
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

    public boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '.') return false; // If there's an empty spot, it's not a draw
            }
        }
        return true; // No empty spots, it's a draw
    }

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