import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Convention :
// Player 1 = White Numbers go from 1-24 (bottom right to top left)
// Player 2 = Red Numbers go from 24-1 (top left to bottom right)

// Index = across the board 0-23
// Position = within lane 0-4
public class main
{
    public static void main(String[] args)
    {
      Board myBoard=new Board(8);
      myBoard.createBoard();
      myBoard.printBoard(1);

      Scanner scanner = new Scanner(System.in);

      Checker a;
      List<Integer> arr = new ArrayList<>();
      arr=myBoard.findFreeCheckers(2);
      for(int i=0;i<arr.size();i++)
      {
          System.out.println(arr.get(i));
      }

        myBoard.highlightCheckersandPrint(arr);
        myBoard.printBoard(1);

      // Comment for development
      /*System.out.println();
      System.out.println("Welcome to Backgammon!");

      System.out.print("Enter the name of player 1: ");
      String player1 = scanner.nextLine();

      System.out.print("Enter the name of player 2: ");
      String player2 = scanner.nextLine();

      Game backgammonGame = new Game(player1, player2);
      backgammonGame.playGame();*/

    }



}
