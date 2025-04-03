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
                String aiMove = generateAIMove(board, aiSymbol);
                if (!board.makeMove(aiMove, aiSymbol)) {
                    System.out.println("AI failed to make a valid move. This should not happen.");
                    break; // or handle the error appropriately
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
   
   private static String generateAIMove(Board board, char aiSymbol) {
        // Basic AI: try to win, block, or pick a random empty spot
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char colChar = (char) ('a' + j);
                String move = colChar + String.valueOf(i + 1);

                if (board.makeMove(move, aiSymbol)) {
                    if (board.checkWin(aiSymbol)) {
                        board.makeMove(move, '.'); // Undo the move for testing
                        return move; // Winning move
                    }
                    board.makeMove(move, '.'); // Undo the move
                }
            }
        }

        // Block player's winning move
        char playerSymbol = (aiSymbol == 'B') ? 'W' : 'B';
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char colChar = (char) ('a' + j);
                String move = colChar + String.valueOf(i + 1);
                if (board.makeMove(move, playerSymbol)) {
                    if (board.checkWin(playerSymbol)) {
                        board.makeMove(move, '.'); // Undo the move for testing
                        return move; // Block the player
                    }
                    board.makeMove(move, '.'); // Undo the move
                }
            }
        }

        // Pick a random empty spot
        while (true) {
            int row = (int) (Math.random() * 9);
            int col = (int) (Math.random() * 9);
            char colChar = (char) ('a' + col);
            String move = colChar + String.valueOf(row + 1);
            if (board.makeMove(move, aiSymbol)) {
                board.makeMove(move, '.'); // Undo the move for testing
                return move;
            }
        }
    }
}