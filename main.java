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
    public static void main(String[] args) throws InvalidEntryException {
      int player =2;


      Board myBoard=new Board(8);

      Player player1=new Player();
      Player player2=new Player();
      boolean nextGame = false;
      boolean gameOn = true;
      boolean endMatch = false;
     //Removed test code to make sure player input features work correctly.

        while(!endMatch)
        {
          Scanner scanner = new Scanner(System.in);
          System.out.println();

          System.out.println("Welcome to Backgammon!");

          System.out.print("Enter the name of player 1: ");
          player1.setName(scanner.nextLine());
          player1.setScore(0);
          System.out.print("Enter the name of player 2: ");
          player2.setName(scanner.nextLine());
          player2.setScore(0);
          System.out.println("Would you like to play or test?!");

          System.out.println("Enter P for play and T for test");
          String choice = scanner.nextLine().toUpperCase();
          if("P".equals(choice))
          {

            System.out.print("Enter the match length:");
            int matchLength = Integer.parseInt(scanner.nextLine());

              if(!nextGame)
              {
                System.out.println("Let's start the first game!");
                nextGame = true;
              }
              else {
                System.out.println("Let's start the next game!");
              }

              Game backgammonGame = new Game(player1, player2, matchLength);

            while(gameOn)
            {
              if(player1.getScore() < matchLength || player2.getScore() < matchLength)
              {
                myBoard.createBoard();
                backgammonGame.playGame();
              }
              else if(player1.getScore() >= matchLength || player1.getScore() > player2.getScore())
              {
                System.out.println(player1.getName()+" Won!");
                gameOn = false;
              }
              else if(player2.getScore() >= matchLength || player1.getScore() < player2.getScore())
              {
                System.out.println(player2.getName()+" Won!");
                gameOn = false;
              }
            }
            endMatch = true;
          }
          else if("T".equals(choice))
          {
            Game backgammonGame = new Game(player1, player2, 100);
            backgammonGame.testGame();
          }

        }

        System.out.println(" ");
        System.out.println("The match is over thanks for playing! :)");

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




