import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Convention : Board Array starts bottom right with 0 and is 23 in the top left
// Player 1 = White moves from top left to bottom right (down the array)
// Player 2 = Red moves from bottom right to top left (up the array)
// Both players try to move all their checker into their home base white array index[0-5] red array index [18-23]
// Careful though in terms of the game point descriptions both try to go from 24 to 1

// Index = across the board 0-23 (for simplicity- Player 2 will still be indexed 0-23 within the program and will be converted once displayed to the player)
// Position = within lane 0-4
public class main
{
    public static void main(String[] args)
    {
      int player =2;

      Board myBoard=new Board(8);
      myBoard.createBoard();


      Scanner scanner = new Scanner(System.in);


      // START TEST CODE
      Game backgammonGame = new Game("player1", "player2"); //new game with fixed playernames for easy debugging
      backgammonGame.playGame();
      /*
      List<Integer> freeCheckers = new ArrayList<>();
      List<Integer> destination = new ArrayList<>();
      int[] dice={4,4}; //sample dice to test calcmoves
      freeCheckers =myBoard.findFreeCheckers(player,dice); // freeCheckers contains all free checkerindices for player2 now (0 based)

      for(int i = 0; i< freeCheckers.size(); i++)
      {
          System.out.println("Free Checker at "+ freeCheckers.get(i));
      }

        myBoard.highlightCheckersandPrint(freeCheckers,player); // highlights checkers

        int pickedChecker=backgammonGame.promptUserPickChecker(freeCheckers,player); // ask user to pick checker

        System.out.println("picked array index. "+pickedChecker);
        System.out.println("picked checkernumber for player2: "+(24-pickedChecker));
        destination=myBoard.calculateMoves(pickedChecker,dice,player);

        for(int i=0;i<destination.size();i++) {

          System.out.println("This Checker can move to: " + (destination.get(i))); //maybe put this in a variable


        }
      //Damis Edits
      dice=myBoard.promptUserPickDestination(pickedChecker,destination,player,dice);
      destination.clear();;
      destination=myBoard.calculateMoves(pickedChecker,dice,player);
      for(int i=0;i<destination.size();i++)
      {

        System.out.println("This Checker can move to: " + (destination.get(i))); //maybe put this in a variable
      }
*/

    }
      //END TEST CODE

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




