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

    private void assignPlayer() {
        System.out.print("Player 1 please enter your name: ");
        String name1 = scanner.next();
        System.out.print("choose a symbol + or - ");
        String symbol1 = scanner.next();

        String symbol2 = symbol1.equals("+") ? "-" : "+";

        System.out.print("Player 2, enter your name: ");
        String name2 = scanner.next();

        player1 = new Player(name1, symbol1);
        player2 = new Player(name2, symbol2);

        System.out.println(player1.getName() + "is" + player1.getSymbol() + "and" + player2.getName() + "is" + player2.getSymbol());
    }
 public void startGomoku(){
        while(true){
            board.displayBoard();
            boolean player1Turn;
            Player currentPlayer = player1Turn? player1 : player2;
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

            /*isPlayer1Turn*/ = !/*isPlayer1Turn*/; // Switch turns
        }
        scanner.close();
    }
}
