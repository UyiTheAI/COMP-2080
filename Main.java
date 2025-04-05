/*
First Name: Sara
Last Name: Mosquera
Student ID: 101512887


First Name: Luna
Last Name: Ortega
Student ID: 101477171

First Name: Andre
Last Name: Alvares
Student ID: 101512582

First Name: Judene
Last Name: Brown
Student ID: 101503637

First Name: Omoruyi
Last Name: Oredia
Student ID: 101496942

*/

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Board board = new Board();
        System.out.println("===============================");
        System.out.println("Welcome to Gomoku!");
        System.out.println("===============================");
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
        System.out.print("Choose your symbol ('B' for Black(X) or 'W' for White(O)): ");
        char playerSymbol = scanner.nextLine().toUpperCase().charAt(0);

        if (playerSymbol == 'B') {
            playerSymbol = 'X';
        } else if (playerSymbol == 'W') {
            playerSymbol = 'O';
        } else {
            System.out.println("Invalid symbol. Defaulting to 'B'.");
            playerSymbol = 'X';
        }

        char aiSymbol = (playerSymbol == 'X') ? 'O' : 'X';
        PlayerPawn player = new PlayerPawn(playerName, playerSymbol);
        AIPawn aiPlayer = new AIPawn(aiSymbol);

        boolean isPlayerTurn = playerSymbol == 'X'; // Black goes first

        while (true) {
            clearScreen();
            board.displayBoard();
            Pawn currentPlayer = isPlayerTurn ? player : aiPlayer; // Defines who goes first
            System.out.println("===============================");
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
            System.out.println("===============================");

            if (isPlayerTurn) {
                System.out.println("For making a move you need to use letters and numbers.");
                System.out.println("From A-I for columns, and from 1-9 for rows.");
                System.out.print("Enter your move (e.g., 'e5'): ");
                String move = scanner.nextLine();

                if (!player.setMovePosition(move, board.SIZE, board.isCellEmpty(move))) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
                board.makeMove(player);
            } else {
                System.out.println("AI is thinking...");
                Move best = aiPlayer.getBestMove(board, player);
                aiPlayer.move = best;
                board.makeMove(aiPlayer);
                System.out.println("AI played " + best.toString());
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
        System.out.print("Choose Player 1 symbol ('B' for Black(X) or 'W' for White(O)): ");
        char player1Symbol = scanner.nextLine().toUpperCase().charAt(0);

        if (player1Symbol == 'B') {
            player1Symbol = 'X';
        } else if (player1Symbol == 'W') {
            player1Symbol = 'O';
        } else {
            System.out.println("Invalid symbol. Defaulting to 'B'.");
            player1Symbol = 'X';
        }

        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();
        char player2Symbol = (player1Symbol == 'X') ? 'O' : 'X';
        PlayerPawn player1 = new PlayerPawn(player1Name, player1Symbol);
        PlayerPawn player2 = new PlayerPawn(player2Name, player2Symbol);
        boolean isPlayer1Turn = player1Symbol == 'X'; // Black goes first

        while (true) {
            clearScreen();
            board.displayBoard();
            PlayerPawn currentPlayer = isPlayer1Turn ? player1 : player2;
            System.out.println("===============================");
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
            System.out.println("===============================");
            System.out.println("For making a move you need to use letters and numbers.");
            System.out.println("From A-I for columns, and from 1-9 for rows.");
            System.out.print("Enter your move (e.g., 'e5'): ");
            String move = scanner.nextLine();

            if (!currentPlayer.setMovePosition(move, board.SIZE, board.isCellEmpty(move))) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            board.makeMove(currentPlayer);

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
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        }
        
}