import java.util.Scanner;

public class main
{
    public static void main(String[] args)
    {
      Board myBoard=new Board(8);
      myBoard.createBoard();
      myBoard.printBoard(2);

      Scanner scanner = new Scanner(System.in);

      System.out.println();
      System.out.println("Welcome to Backgammon!");

      System.out.print("Enter the name of player 1: ");
      String player1 = scanner.nextLine();

      System.out.print("Enter the name of player 2: ");
      String player2 = scanner.nextLine();

      Game backgammonGame = new Game(player1, player2);
      backgammonGame.playGame();
    }



}
