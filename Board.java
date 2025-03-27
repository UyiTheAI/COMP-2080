public class Board {
    private char[][] board;
    private int size;

    public Board(int size) {
        this.size = size;
        this.board = new char[size][size];
        initializeBoard();
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
