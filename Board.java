public class Board {

    static final int size = 9;
    public char[][] board;


    public Board() {
        board = new char[size][size];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '.';
            }
        }
    }


    public void displayBoard() {
        System.out.print("  ");
        for (char colLabel = 'a'; colLabel < 'a' + size; colLabel++) {
            System.out.print(colLabel + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " "); 
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean makeMove(String position, char sym) {
        int col = position.charAt(0) - 'a';
        int row = Character.getNumericValue(position.charAt(1)) - 1;

        if (row >= 0 && row < size && col >= 0 && col < size && board[row][col] == '.') {
            board[row][col] = sym;
            return true;
        }
        return false;
    }
    
    public void unmakeMove(String position) {
        int col = position.charAt(0) - 'a';
        int row = Character.getNumericValue(position.charAt(1)) - 1;

        if (row >= 0 && row < size && col >= 0 && col < size) {
            board[row][col] = '.'; 
        }
    }

    public boolean checkWin(char sym) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (checkDirection(i, j, sym, 1, 0) ||
                    checkDirection(i, j, sym, 0, 1) || 
                    checkDirection(i, j, sym, 1, 1) ||
                    checkDirection(i, j, sym, 1, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

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

    public boolean isDraw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '.') return false;
            }
        }
        return true; 
    }

}