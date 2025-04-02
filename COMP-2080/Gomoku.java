import java.util.Scanner;

public class Gomoku {

    private Board board;
    private Player player1, player2;
    private Scanner scanner;

    public Gomoku() {
        board = new Board(9);
        scanner = new Scanner(System.in);
        assignPlayer();
    }

    private void assignPlayer() { //add user input valiadtion 
        String name1, name2, symbol1;

        while (true) {
            System.out.print("Player 1 please enter your name: "); //here too 
            name1 = scanner.nextLine().trim();
            if (!name1.isEmpty() && name1.matches("[a-zA-Z]+")) {
                break;
            }
            System.out.print("only letter from A-Z please");
        }
        //here as well
        while (true) {
            System.out.print(name1 + ", choose a symbol (+ or -): ");
            symbol1 = scanner.next().trim();
            if (symbol1.equals("+") || symbol1.equals("-")) {
                break;
            }
            System.out.println("Invalid symbol! Please enter either '+' or '-'.");
        }

        String symbol2 = symbol1.equals("+") ? "-" : "+";
        scanner.nextLine(); //

        while (true) {
            System.out.print("Player 2, enter your name: "); //asking for name once again 
            name2 = scanner.nextLine().trim();
            if (!name2.isEmpty() && name2.matches("[a-zA-Z]+")) {
                break;
            }
        }

        player1 = new Player(name1, symbol1);
        player2 = new Player(name2, symbol2);

        System.out.println(player1.getName() + "is" + player1.getSymbol() + "and" + player2.getName() + "is" + player2.getSymbol());
    }

    public void startGomoku() {
        while (true) {
            board.displayBoard();
            boolean player1Turn = false;
            Player currentPlayer = player1Turn ? player1 : player2;
            System.out.println(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + "), enter your move (row 1-9 and column A-I): ");

            // this part is stricly from chatgpt//
            int row = -1;
            char col = ' ';
            boolean validMove = false;

            while (!validMove) {
                try {
                    row = scanner.nextInt();
                    col = scanner.next().toUpperCase().charAt(0);
                    validMove = board.placeMark(row, col, currentPlayer.getSymbol());
                } catch (Exception e) {
                    System.out.println("Invalid input! Enter row (1-9) and column (A-I).");
                    scanner.nextLine(); // Clear invalid input
                }
            }

            if (board.wincondition(currentPlayer.getSymbol().charAt(0))) {
                board.displayBoard();
                System.out.println(currentPlayer.getName() + "won");
                break;
            }

            if (board.isFull()) { // Fixed method name
                board.displayBoard();
                System.out.println("It's a draw");
                break;
            }
            if (currentPlayer == player1) {
                currentPlayer = player2;

            } else {
                currentPlayer = player1;
            }

        }
        scanner.close();

    }
}
