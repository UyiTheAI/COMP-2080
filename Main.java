import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Gomoku!");
        System.out.print("Choose game mode (1: vs AI, 2: 2-player): ");
        int mode = scanner.nextInt();
        scanner.nextLine();
        
        Board board = new Board(9);
        
        Player player1, player2;
        
        if (mode == 1) {
            System.out.print("Enter your name: ");
            String playerName = scanner.nextLine();
            System.out.print("Choose your color (B/W): ");
            PlayerColor playerColor = PlayerColor.valueOf(scanner.next().toUpperCase());
            scanner.nextLine();
            
            PlayerColor aiColor = (playerColor == PlayerColor.B) ? PlayerColor.W : PlayerColor.B;
            player1 = new HumanPlayer(playerName, playerColor);
            player2 = new AIPlayer("AI", aiColor);
        } else {
            System.out.print("Player 1, enter your name: ");
            String name1 = scanner.nextLine();
            System.out.print("Player 1, choose your color (B/W): ");
            PlayerColor color1 = PlayerColor.valueOf(scanner.next().toUpperCase());
            PlayerColor color2 = (color1 == PlayerColor.B) ? PlayerColor.W : PlayerColor.B;
            scanner.nextLine();
            
            System.out.print("Player 2, enter your name: ");
            String name2 = scanner.nextLine();
            
            player1 = new HumanPlayer(name1, color1);
            player2 = new HumanPlayer(name2, color2);
        }
        
        boolean player1Turn = (player1.color == PlayerColor.B);
        board.displayBoard();
        
        while (true) {
            Player currentPlayer = player1Turn ? player1 : player2;
            System.out.println(currentPlayer.name + "'s turn.");
            currentPlayer.makeMove(board);
            board.displayBoard();
            player1Turn = !player1Turn;
        }
    }
}
