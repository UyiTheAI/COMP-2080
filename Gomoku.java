gomoku game 

import java.util.Scanner;

public class Gomoku {
    private Board board;
    private Player player1, player2;
    private Scanner scanner;

    public Gomoku() {
        board = new Board(9);
        scanner = new Scanner(System.in); 
        Player(); 
    }

    private void Player() {
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
            Player currentPlayer = player1Turn? player1 : player2;

            int row= scanner.nextInt();
            char col = scanner.next().charAt(0);
            boolean vaildMove=false;


// this part is stricly from chatgpt//
            System.out.println(currentPlayer.getName() + " (" + currentPlayer.getSymbol() + "), enter your move (row and column): ");
            while (!validMove) {
                try {
                    row = scanner.nextInt();
                    colChar = scanner.next().toUpperCase().charAt(0);
                    validMove = board.placeMark(row, colChar, currentPlayer.getSymbol());
                } catch (Exception e) {
                    System.out.println("Invalid input! Enter row (1-9) and column (A-I).");
                    scanner.nextLine();
                }
                //this block^^
            }
        if (board.checkWin(currentPlayer.getsymbol)) {
            board.display();
            System.out.println( + currentPlayer.getname + " you won");
            break;
        }

        // Check draw
        if (board.isFull()) {
            board.display();
            System.out.println("It's a draw.");
            break;
        }

  isPlayer1Turn = !isPlayer1Turn; // Switch turns
    }
}
public static void main(String[] args) {
    new Gomoku(); // Start the game
}

 }