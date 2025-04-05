public class AIPawn extends Pawn {
    
    public AIPawn(char symbol) {
        super("AI",symbol);
    }

    public Move getBestMove(Board board, PlayerPawn playerPawn) {
        int bestValue = Integer.MIN_VALUE;
        Move bestMove = null;
        char aiSymbol = this.getSymbol();
        char playerSymbol = playerPawn.getSymbol();
    
        for (Move moveI : board.getAvailableMoves()) {
            // Simulate the AI move directly on the board
            board.board[moveI.row][moveI.col] = aiSymbol;
            int moveValue = minimax(board, 3, false, Integer.MIN_VALUE, Integer.MAX_VALUE, aiSymbol, playerSymbol);
            board.board[moveI.row][moveI.col] = '.';
            if (moveValue > bestValue) {
                bestValue = moveValue;
                bestMove = moveI;
            }
        }
        return bestMove;
    }

    private int minimax(Board board, int depth, boolean isMaximizing, int alpha, int beta, char aiSymbol, char playerSymbol) {
        if (depth == 0 || board.isGameOver()) {
            return evaluateBoard(board, aiSymbol, playerSymbol);
        }
        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (Move moveI : board.getAvailableMoves()) {
                // Simulate the AI move
                board.board[moveI.row][moveI.col] = aiSymbol;
                int eval = minimax(board, depth - 1, false, alpha, beta, aiSymbol, playerSymbol);
                board.board[moveI.row][moveI.col] = '.';  // Undo the move
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break;  // Beta cut-off
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Move moveI : board.getAvailableMoves()) {
                // Simulate the player's move
                board.board[moveI.row][moveI.col] = playerSymbol;
                int eval = minimax(board, depth - 1, true, alpha, beta, aiSymbol, playerSymbol);
                board.board[moveI.row][moveI.col] = '.';  // Undo the move
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) break;  // Alpha cut-off
            }
            return minEval;
        }
    }

    
    private int evaluateBoard(Board board, char aiSymbol, char playerSymbol) {
        int aiScore = evaluateSymbol(board, aiSymbol);
        int playerScore = evaluateSymbol(board, playerSymbol);
        return aiScore - playerScore;
    }
    private int evaluateSymbol(Board board, char symbol) { 
        int score = 0;
    
        int[][] directions = {
            {1, 0},  // Horizontal
            {0, 1},  // Vertical
            {1, 1},  // Diagonal \
            {1, -1}  // Diagonal /
        };
    
        char[][] b = board.board;
    
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                if (b[row][col] == symbol) {
                    for (int[] dir : directions) {
                        int count = 1;
                        int openEnds = 0;
    
                        int r = row + dir[0];
                        int c = col + dir[1];
                        while (r >= 0 && r < Board.SIZE && c >= 0 && c < Board.SIZE && b[r][c] == symbol) {
                            count++;
                            r += dir[0];
                            c += dir[1];
                        }
    
                        // Check one end
                        if (r >= 0 && r < Board.SIZE && c >= 0 && c < Board.SIZE && b[r][c] == '.') {
                            openEnds++;
                        }
    
                        // Go backward
                        r = row - dir[0];
                        c = col - dir[1];
                        while (r >= 0 && r < Board.SIZE && c >= 0 && c < Board.SIZE && b[r][c] == symbol) {
                            count++;
                            r -= dir[0];
                            c -= dir[1];
                        }
    
                        // Check the other end
                        if (r >= 0 && r < Board.SIZE && c >= 0 && c < Board.SIZE && b[r][c] == '.') {
                            openEnds++;
                        }
    
                        if (count >= 5) {
                            score += 100000;
                        } else if (count == 4 && openEnds == 2) {
                            score += 10000;
                        } else if (count == 4 && openEnds == 1) {
                            score += 5000;
                        } else if (count == 3 && openEnds == 2) {
                            score += 1000;
                        } else if (count == 3 && openEnds == 1) {
                            score += 500;
                        } else if (count == 2 && openEnds == 2) {
                            score += 100;
                        } else if (count == 2 && openEnds == 1) {
                            score += 50;
                        }
                    }
                }
            }
        }
    
        return score;
    }

    public String getName(){ return name;}
    public char getSymbol() { return symbol;}
    public Move getMove(){ return move;}
}
