/*
 * Sara Mosquera - 101512887
 * Luna Ortega - 101477171
 * Andre Alvares- 101512582
 * Judene Brown - 101503637
 * Omoruyi Oredia - 101496942
 */
import java.util.Scanner;

public class Gomoku {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();

        System.out.println("Welcome to Gomoku!");
        System.out.println("===============================");
        System.out.println("Choose game mode:" + 
            "\n1) Single Player (vs AI)" + 
            "\n2) Two Players" + 
            "\n===============================");

        int mode = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Select game mode
        if (mode == 1) {
            singlePlayerMode(scanner, board);
        } else if (mode == 2) {
            twoPlayerMode(scanner, board);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // Handles single player mode with AI
    private static void singlePlayerMode(Scanner scanner, Board board) {
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        System.out.print("Choose your symbol ('B' for Black or 'W' for White): ");
        char playerSym = scanner.nextLine().toUpperCase().charAt(0);

        // Validate player symbol
        if (playerSym != 'B' && playerSym != 'W') {
            System.out.println("Invalid symbol. Defaulting to 'B'.");
            playerSym = 'B';
        }

        char aiSymbol = (playerSym == 'B') ? 'W' : 'B';
        Player player = new Player(playerName, playerSym);
        Player aiPlayer = new Player("AI", aiSymbol);
        boolean PlayerTurn = playerSym == 'B'; // Black plays first

        while (true) {
            board.displayBoard();
            Player currentPlayer = PlayerTurn ? player : aiPlayer;

            System.out.println("=========================");
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
            System.out.println("=========================");

            if (PlayerTurn) {
                System.out.print("Enter your move (e.g., 'e5'): ");
                String move = scanner.nextLine();

                if (!board.makeMove(move, player.getSymbol())) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
            } else {
                System.out.println("AI is thinking...");
                String aiMove = minimax(board, aiSymbol, playerSym, 3);

                if (!board.makeMove(aiMove, aiSymbol)) {
                    System.out.println("AI failed to make a valid move. This should not happen.");
                    break;
                }

                System.out.println("AI played " + aiMove);
            }

            // Check game status
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

            PlayerTurn = !PlayerTurn;
        }
    }

    // Handles two player mode (human vs human)
    private static void twoPlayerMode(Scanner scanner, Board board) {
        System.out.println("===============================");
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();

        System.out.print("Choose Player 1 symbol ('B' for Black or 'W' for White): ");
        char player1Sym = scanner.nextLine().toUpperCase().charAt(0);

        if (player1Sym != 'B' && player1Sym != 'W') {
            System.out.println("Invalid symbol. Defaulting to 'B'.");
            player1Sym = 'B';
        }

        System.out.println("===============================");
        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();

        char player2Sym = (player1Sym == 'B') ? 'W' : 'B';
        Player player1 = new Player(player1Name, player1Sym);
        Player player2 = new Player(player2Name, player2Sym);
        boolean Player1Turn = true;

        while (true) {
            board.displayBoard();
            Player currentPlayer = Player1Turn ? player1 : player2;

            System.out.println("===============================");
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
            System.out.println("===============================");
            System.out.print("Enter your move (e.g., 'e5'): ");
            String move = scanner.nextLine();

            if (!board.makeMove(move, currentPlayer.getSymbol())) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            // Check win/draw condition
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

            Player1Turn = !Player1Turn;
        }
    }

    // minimax function with move generation
    private static String minimax(Board board, char aiSym, char playerSym, int depth) {
        int bestScore = Integer.MIN_VALUE;
        String bestMove = null;

        for (int row = 0; row < Board.size; row++) {
            for (int col = 0; col < Board.size; col++) {
                if (board.board[row][col] == '.') {
                    String move = (char) ('a' + col) + Integer.toString(row + 1);
                    board.makeMove(move, aiSym);
                    int score = minimaxPruning(board, depth - 1, false, aiSym, playerSym, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    board.unmakeMove(move);

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = move;
                    }
                }
            }
        }

        return bestMove;
    }

    // Minimax algorithm with alpha-beta pruning
    private static int minimaxPruning(Board board, int depth, boolean isMaximizing, char aiSym, char playerSym, int alpha, int beta) {
        if (board.checkWin(aiSym)) return 10;
        if (board.checkWin(playerSym)) return -10;
        if (board.isDraw() || depth == 0) return evaluateBoard(board, aiSym, playerSym);

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int row = 0; row < Board.size; row++) {
                for (int col = 0; col < Board.size; col++) {
                    if (board.board[row][col] == '.') {
                        String move = (char) ('a' + col) + Integer.toString(row + 1);
                        board.makeMove(move, aiSym);
                        int eval = minimaxPruning(board, depth - 1, false, aiSym, playerSym, alpha, beta);
                        board.unmakeMove(move);
                        maxEval = Math.max(maxEval, eval);
                        alpha = Math.max(alpha, eval);
                        if (beta <= alpha) return maxEval; // pruning
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int row = 0; row < Board.size; row++) {
                for (int col = 0; col < Board.size; col++) {
                    if (board.board[row][col] == '.') {
                        String move = (char) ('a' + col) + Integer.toString(row + 1);
                        board.makeMove(move, playerSym);
                        int eval = minimaxPruning(board, depth - 1, true, aiSym, playerSym, alpha, beta);
                        board.unmakeMove(move);
                        minEval = Math.min(minEval, eval);
                        beta = Math.min(beta, eval);
                        if (beta <= alpha) return minEval; // pruning
                    }
                }
            }
            return minEval;
        }
    }

    // Board evaluation: AI's score minus player's score
    private static int evaluateBoard(Board board, char aiSym, char playerSym) {
        int aiScore = evaluateSymbol(board, aiSym);
        int playerScore = evaluateSymbol(board, playerSym);
        return aiScore - playerScore;
    }

    private static int evaluateSymbol(Board board, char sym) {
        int score = 0;

        // Directions: right, down, diagonal right-down, diagonal left-down
        int[][] directions = {
            {1, 0},
            {0, 1},
            {1, 1},
            {1, -1}
        };

        char[][] b = board.board;

        for (int row = 0; row < Board.size; row++) {
            for (int col = 0; col < Board.size; col++) {
                if (b[row][col] == sym) {
                    for (int[] dir : directions) {
                        int count = 1;
                        int openEnds = 0;

                        // Forward direction
                        int r = row + dir[0];
                        int c = col + dir[1];
                        while (r >= 0 && r < Board.size && c >= 0 && c < Board.size && b[r][c] == sym) {
                            count++;
                            r += dir[0];
                            c += dir[1];
                        }
                        if (r >= 0 && r < Board.size && c >= 0 && c < Board.size && b[r][c] == '.') openEnds++;

                        // Backward direction
                        r = row - dir[0];
                        c = col - dir[1];
                        while (r >= 0 && r < Board.size && c >= 0 && c < Board.size && b[r][c] == sym) {
                            count++;
                            r -= dir[0];
                            c -= dir[1];
                        }
                        if (r >= 0 && r < Board.size && c >= 0 && c < Board.size && b[r][c] == '.') openEnds++;

                        // Heuristic scoring
                        if (count >= 5) score += 100000;
                        else if (count == 4 && openEnds == 2) score += 10000;
                        else if (count == 4 && openEnds == 1) score += 5000;
                        else if (count == 3 && openEnds == 2) score += 1000;
                        else if (count == 3 && openEnds == 1) score += 500;
                        else if (count == 2 && openEnds == 2) score += 100;
                        else if (count == 2 && openEnds == 1) score += 50;
                    }
                }
            }
        }

        return score;
    }
}
