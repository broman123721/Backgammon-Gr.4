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
      boolean quitGame1 = false;
      boolean nextGame = false;
      boolean gameOn = true;
      boolean endMatch = false;

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

          String choice = getGameChoice(scanner);

          if("P".equals(choice))
          {

            System.out.println("Enter the match length:");
            int matchLength = Integer.parseInt(scanner.nextLine());

              if(!nextGame)
              {
                System.out.println("Let's start the first game!");
                nextGame = true;
              }
              else {
                System.out.println("Let's start the next game!");
              }

              Game backgammonGame = new Game(player1, player2, matchLength, quitGame1);

            while(gameOn)
            {
              if(player1.getScore() < matchLength && player2.getScore() < matchLength)
              {
                myBoard.createBoard();
                backgammonGame.playGame();
                if (backgammonGame.getQuitGame()) gameOn = false;

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
          else if("T".equals(choice)) //I think this is redundant might turn to it to the quit function
          {
            System.out.println("Starting the testing game file...");
            Game backgammonGame = new Game(player1, player2, 100, quitGame1);
          }

        }

        System.out.println(" ");
        System.out.println("The match is over thanks for playing! :)");

    }
  private static String getGameChoice(Scanner scanner) {
    String choice;
    while (true) {
      System.out.println("Would you like to play or test?!");
      System.out.println("Enter P for play and T for test");
      choice = scanner.nextLine().toUpperCase();
      if ("P".equals(choice) || "T".equals(choice)) {
        return choice;
      } else {
        System.out.println("Invalid entry. Please enter P or T.");
      }
    }
  }

}




