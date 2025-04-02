class Game {
    private Board board;
    private Player player1, player2;
    private boolean player1Turn;
    
    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Turn = (player1.color == PlayerColor.B);
    }
    
    public void start() {
        board.displayBoard();
        while (true) {
            Player currentPlayer = player1Turn ? player1 : player2;
            System.out.println(currentPlayer.name + "'s turn.");
            currentPlayer.makeMove(board);
            board.displayBoard();
            
            if (checkWin()) {
                System.out.println(currentPlayer.name + " wins!");
                break;
            }
            
            player1Turn = !player1Turn;
        }
    }
    
    private boolean checkWin() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.isCellEmpty(i, j)) continue;
                if (checkDirection(i, j, 1, 0) || // Horizontal
                    checkDirection(i, j, 0, 1) || // Vertical
                    checkDirection(i, j, 1, 1) || // Diagonal \
                    checkDirection(i, j, 1, -1))  // Diagonal /
                    return true;
            }
        }
        return false;
    }
    
    private boolean checkDirection(int row, int col, int dRow, int dCol) {
        char symbol = board.getCell(row, col);
        int count = 1;
        for (int i = 1; i < 5; i++) {
            int r = row + i * dRow, c = col + i * dCol;
            if (r >= 0 && r < 9 && c >= 0 && c < 9 && board.getCell(r, c) == symbol) count++;
            else break;
        }
        for (int i = 1; i < 5; i++) {
            int r = row - i * dRow, c = col - i * dCol;
            if (r >= 0 && r < 9 && c >= 0 && c < 9 && board.getCell(r, c) == symbol) count++;
            else break;
        }
        return count >= 5;
    }
}
