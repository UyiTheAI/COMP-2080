public class Board {
    private char[][] board;
    private int size;

    public Board(int size) {
        this.size = size;
        this.board = new char[size][size];
        initializeBoard();
    }

    private boolean isCellEmpty(int row, int col){
        
        if ((col < 0 || col >= 9) &&
        (row < 0 || row >= 9)) {
            return false;
        }

        return board[row][col] == '.';
    }

    public boolean placeMark(int row, int col, PlayerColor pColor){
        String letters = "ABCDEFGHI";
        int tempCol = letters.indexOf(Character.toUpperCase(col));
        int tempRow = row - 1;

        if (isCellEmpty(tempRow, tempRow)) {
            if(pColor.equals("W")){
                board[tempRow][tempCol] = 'o';
            }else{
                board[tempRow][tempCol] = '+';
            }
            
        }
        return false;
    }
        
    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '.';
            }
        }
    }

    public void displayBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    
}
