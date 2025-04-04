import java.util.Scanner;

public class Gomoku {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Board board = new Board();

        System.out.println("Welcome to Gomoku!");

        System.out.println("Choose game mode: \n1) Single Player (vs AI)\n2) Two Players");

        int mode = scanner.nextInt();

        scanner.nextLine(); 

        if (mode == 1) {
            singlePlayerMode(scanner, board);
        } else if (mode == 2) {
            twoPlayerMode(scanner, board);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private static void singlePlayerMode(Scanner scanner, Board board) {
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        System.out.print("Choose your symbol ('B' for Black or 'W' for White): ");
        char playerSymbol = scanner.nextLine().toUpperCase().charAt(0);

        if (playerSymbol != 'B' && playerSymbol != 'W') {
            System.out.println("Invalid symbol. Defaulting to 'B'.");
            playerSymbol = 'B';
        }

        char aiSymbol = (playerSymbol == 'B') ? 'W' : 'B';
        Player player = new Player(playerName, playerSymbol);
        Player aiPlayer = new Player("AI", aiSymbol);
        boolean isPlayerTurn = playerSymbol == 'B'; // Black goes first

        while (true) {
            board.displayBoard();
            Player currentPlayer = isPlayerTurn ? player : aiPlayer;
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");

            if (isPlayerTurn) {
                System.out.print("Enter your move (e.g., 'e5'): ");
                String move = scanner.nextLine();
                if (!board.makeMove(move, player.getSymbol())) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
            } else {
                System.out.println("AI is thinking...");
                String aiMove = minimax(board, aiSymbol, playerSymbol, 3);
                if (!board.makeMove(aiMove, aiSymbol)) {
                    System.out.println("AI failed to make a valid move. This should not happen.");
                    break; 
                }
                System.out.println("AI played " + aiMove);
            }

            if (board.checkWin(player.getSymbol())) {
                board.displayBoard();
                System.out.println(player.getName() + " wins!");
                break;
            } else if (board.checkWin(aiSymbol)) {
                board.displayBoard();
                System.out.println("AI wins!");
                break;
            } else if (board.isDraw()) {
                board.displayBoard();
                System.out.println("It's a draw!");
                break;
            }

            isPlayerTurn = !isPlayerTurn;
        }
    }

    private static void twoPlayerMode(Scanner scanner, Board board) {
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();
        System.out.print("Choose Player 1 symbol ('B' for Black or 'W' for White): ");
        char player1Symbol = scanner.nextLine().toUpperCase().charAt(0);
        if (player1Symbol != 'B' && player1Symbol != 'W') {
            System.out.println("Invalid symbol. Defaulting to 'B'.");
            player1Symbol = 'B';
        }

        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();
        char player2Symbol = (player1Symbol == 'B') ? 'W' : 'B';
        Player player1 = new Player(player1Name, player1Symbol);
        Player player2 = new Player(player2Name, player2Symbol);
        boolean isPlayer1Turn = true;

        while (true) {
            board.displayBoard();
            Player currentPlayer = isPlayer1Turn ? player1 : player2;
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
            System.out.print("Enter your move (e.g., 'e5'): ");
            String move = scanner.nextLine();
            if (!board.makeMove(move, currentPlayer.getSymbol())) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            if (board.checkWin(player1.getSymbol())) {
                board.displayBoard();
                System.out.println(player1.getName() + " wins!");
                break;
            } else if (board.checkWin(player2.getSymbol())) {
                board.displayBoard();
                System.out.println(player2.getName() + " wins!");
                break;
            } else if (board.isDraw()) {
                board.displayBoard();
                System.out.println("It's a draw!");
                break;
            }

            isPlayer1Turn = !isPlayer1Turn;
        }
    }
    
    private static String minimax(Board board, char aiSymbol, char playerSymbol, int depth) {
        int bestScore = Integer.MIN_VALUE;
        String bestMove = null;
    
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                if (board.board[row][col] == '.') {
                    String move = (char) ('a' + col) + Integer.toString(row + 1);
                    board.makeMove(move, aiSymbol);
                    int score = minimaxRecursive(board, depth - 1, false, aiSymbol, playerSymbol, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    board.undoMove(move);
    
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = move;
                    }
                }
            }
        }
        return bestMove;
    }
    
    private static int minimaxRecursive(Board board, int depth, boolean isMaximizing, char aiSymbol, char playerSymbol, int alpha, int beta) {
        if (board.checkWin(aiSymbol)) return 10;
        if (board.checkWin(playerSymbol)) return -10;
        if (board.isDraw() || depth == 0) return evaluateBoard(board, aiSymbol, playerSymbol);
    
        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int row = 0; row < Board.SIZE; row++) {
                for (int col = 0; col < Board.SIZE; col++) {
                    if (board.board[row][col] == '.') {
                        String move = (char) ('a' + col) + Integer.toString(row + 1);
                        board.makeMove(move, aiSymbol);
                        int eval = minimaxRecursive(board, depth - 1, false, aiSymbol, playerSymbol, alpha, beta);
                        board.undoMove(move);
                        maxEval = Math.max(maxEval, eval);
                        alpha = Math.max(alpha, eval);
                        if (beta <= alpha) return maxEval;
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int row = 0; row < Board.SIZE; row++) {
                for (int col = 0; col < Board.SIZE; col++) {
                    if (board.board[row][col] == '.') {
                        String move = (char) ('a' + col) + Integer.toString(row + 1);
                        board.makeMove(move, playerSymbol);
                        int eval = minimaxRecursive(board, depth - 1, true, aiSymbol, playerSymbol, alpha, beta);
                        board.undoMove(move);
                        minEval = Math.min(minEval, eval);
                        beta = Math.min(beta, eval);
                        if (beta <= alpha) return minEval;
                    }
                }
            }
            return minEval;
        }
    }
    
    
    private static int evaluateBoard(Board board, char aiSymbol, char playerSymbol) {
        int aiScore = evaluateSymbol(board, aiSymbol);
        int playerScore = evaluateSymbol(board, playerSymbol);
        return aiScore - playerScore;
    }
    private static int evaluateSymbol(Board board, char symbol) {
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
        
}