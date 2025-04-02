//given to me by Andre

public boolean checkWin(int row, int col) {
    String symbol = board[row][col];
    if (symbol == null) return false;

    return checkDirection(row, col, 1, 0, symbol) ||  // Horizontal →
           checkDirection(row, col, 0, 1, symbol) ||  // Vertical ↓
           checkDirection(row, col, 1, 1, symbol) ||  // Diagonal ↘
           checkDirection(row, col, 1, -1, symbol);   // Diagonal ↙
}