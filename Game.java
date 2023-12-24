import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Game {

    private Player player1;
    private Player player2;

    private int matchLength;
    private boolean quitGame;

    public Game(Player player1, Player player2, int matchLength, boolean quitGame) {
        this.player1 = player1; //White
        this.player2 = player2; //Red
        this.matchLength = matchLength; //Length of match
        this.quitGame = quitGame; //flag to end game
    }

    public boolean getQuitGame(){return quitGame;}

    public void playGame() {

        int currentplayer = -1;  // Current player's turn (-1 indicates uninitialized state)
        boolean dicedraw = true;  // Flag for determining the starting player with a dice draw
        int isdouble_int = 0;  // Double scenario counter (0: not in a double, 1: first round, 2: second round)
        int dice_copy = 0;  // Copy of dice value for the second round of a double
        boolean isdouble_bool = false;  // Flag for the second round of a double
        boolean gameisover = false;  // Flag indicating whether the game is over
        int customDie1 = 0;  // First custom die value
        int customDie2 = 0;  // Second custom die value
        boolean manualDiceEntry = false;  // Flag for manual dice entry
        boolean testCommandEntry = false;  // Flag for test command entry mode
        int currentCommandIndex = 0;  // Index for tracking current test command

            // Other variables for game state and logic
            int round = 0;
            int[] diceValues = new int[4];
            int doubleOwner = 0;
            DoublingCube doublingCube = new DoublingCube();
            String[] commands = null;

            // Initial dice draw to determine the starting player
            System.out.println("Let's roll the dice to determine the starting player...");
            while (dicedraw == true) {
                // Roll dice and display results
                diceValues = Die.rollDice();
                System.out.println("DICE " + player1.getName() + ":" + "[" + diceValues[0] + "]");
                int total1 = diceValues[0];

                System.out.println("DICE " + player2.getName() + ":" + "[" + diceValues[1] + "]");
                int total2 = diceValues[1];

                // Determine the starting player based on the dice draw
                if (total1 > total2) {
                    System.out.println(player1.getName() + " goes first!");
                    currentplayer = 1;
                    doubleOwner = 1;
                    dicedraw = false;
                }
                if (total1 < total2) {
                    System.out.println(player2.getName() + " goes first!");
                    currentplayer = 2;
                    doubleOwner = 2;
                    dicedraw = false;
                }
                if (total1 == total2) {
                    System.out.println("Draw! Lets roll again!");
                }
            }

            //Setup the game, create the Board and necessary variables
            Board myBoard = new Board(8);


            myBoard.createBoard();
            myBoard.printBoard(currentplayer);


            List<Integer> freeCheckers = new ArrayList<>();
            List<Integer> destination = new ArrayList<>();
            boolean movesPossible = true;
            int pipp1 = 0;
            int pipp2 = 0;
            int score1 = 0;
            int score2 = 0;
            int pickedChecker = -1;
            boolean endGame = false;

            // Main game loop
            while (!gameisover && !quitGame) {

                if (!endGame) { // Check for end game and user input
                    Scanner scanner = new Scanner(System.in);
                    //Gives user the choice to roll or to check the pip count(IDK what we're hinting because we basically do that already with our move functions)

                   try {
                       // Display player information and available commands
                       if (currentplayer == 1) {
                           System.out.println("Player 1: " + player1.getName());
                       }
                       if (currentplayer == 2) {
                           System.out.println("Player 2: " + player2.getName());
                       }

                       // Display available commands based on game state
                       if (doubleOwner == 1 && currentplayer == 1) {
                           // Commands for player 1 if in ownership of the double cube
                           System.out.println("- Type 'R' if you would like to roll the dice.");
                           System.out.println("- Type 'P' if you would like to view the pip count:");
                           System.out.println("- Type 'Q' if you would like to quit the game.");
                           System.out.println("- Type 'M' if you would like to display the match length and scores.");
                           System.out.println("- Type 'D' if you would like to use the doubling.");
                           System.out.println("- Type 'DC' if you would like to manually enter dice values.");
                           System.out.println("- Type 'T' if you would like to run a test file to process commands.");

                       } else if (doubleOwner == 2 && currentplayer == 2) {
                           // Commands for player 2 if in ownership of the double cube
                           System.out.println("- Type 'R' if you would like to roll the dice.");
                           System.out.println("- Type 'P' if you would like to view the pip count:");
                           System.out.println("- Type 'Q' if you would like to quit the game.");
                           System.out.println("- Type 'M' if you would like to display the match length and scores.");
                           System.out.println("- Type 'D' if you would like to use the doubling.");
                           System.out.println("- Type 'DC' if you would like to manually enter dice values.");
                           System.out.println("- Type 'T' if you would like to run a test file to process commands.");

                       } else {
                           // Commands for regular turns if player is not in ownership
                           System.out.println("- Type 'R' if you would like to roll the dice.");
                           System.out.println("- Type 'P' if you would like to view the pip count:");
                           System.out.println("- Type 'Q' if you would like to quit the game.");
                           System.out.println("- Type 'M' if you would like to display the match length and scores.");
                           System.out.println("- Type 'DC' if you would like to manually enter dice values.");
                           System.out.println("- Type 'T' if you would like to run a test file to process commands.");

                       }

                       String choice = null; //Initialise the choice variable

                       // Handle user input based on the current game state
                       if(!testCommandEntry){
                           choice = scanner.nextLine().toUpperCase(); // Get user input for regular turns

                       }
                       else {
                           while (currentCommandIndex < commands.length) {
                               choice = commands[currentCommandIndex];

                               // Process the command here (replace this with your actual logic)
                               System.out.println("Processing command from file: " + choice);

                               // Increment the index to move to the next command
                               currentCommandIndex++;

                               // Exit the loop to go back to the main loop for the next iteration
                               break;
                           }
                           if(currentCommandIndex >= commands.length) {
                               testCommandEntry = false;
                           }
                       }


                       if ("Q".equals(choice)) // Quit game if choice is "Q"
                       {
                           System.out.println("Thanks for playing! Goodbye!! :)");
                           quitGame = true;
                           break;
                       }

                       // Double feature request
                       else if("D".equals(choice) && (currentplayer == doubleOwner)){
                           // Display double request message based on current player
                           if (currentplayer == 1 && doubleOwner == 1) {
                               System.out.println("Player 1:" + player1.getName() + " has requested to double.");
                           }
                           else if (currentplayer == 2 && doubleOwner == 2) {
                               System.out.println("Player 2:" + player2.getName() + " has requested to double.");
                           }
                           else {
                               System.out.println("You cannot choose the double feature as you do not have ownership of the dice.");
                           }

                           if (currentplayer == 1) {
                               System.out.println("Player 2: " + player2.getName() + " if you would like to accept type 'A' if you would like to refuse type 'R'");
                           }
                           if (currentplayer == 2) {
                               System.out.println("Player 1: " + player1.getName() + " if you would like to accept type 'A' if you would like to refuse type 'R'");
                           }
                           String choice2 = scanner.nextLine().toUpperCase();

                           // Process the response and update double ownership
                           if("A".equals(choice2)) {
                               doublingCube.doubleCube();
                               if (doubleOwner == 1) {
                                   System.out.println("Ownership has switched to player 2");
                                   doubleOwner = 2;
                               }
                               else {
                                   System.out.println("Ownership has switched to player 1");
                                   doubleOwner = 1;
                               }
                           }

                           if("R".equals(choice2)){
                               System.out.println("Double proposal has been rejected.");
                           }
                       }

                       // Display match details
                       else if("M".equals(choice)){
                           System.out.println("Match Length: " + matchLength);
                           System.out.println("Player 1: " + player1.getName() + "'s score: " + player1.getScore());
                           System.out.println("Player 2: " + player2.getName() + "'s score: " + player2.getScore());
                           System.out.println("");
                       }

                       // Display pip count for the current player
                       else if ("P".equals(choice)) {
                           if (currentplayer == 1) {
                               pipp1 = myBoard.getPipCount(currentplayer);
                               System.out.println("Your Pip count is:" + pipp1);
                           }
                           if (currentplayer == 2) {
                               pipp2 = myBoard.getPipCount(currentplayer);
                               System.out.println("Your Pip count is:" + pipp2);
                           }
                       }

                       // Manually set dice values
                       else if("DC".equals(choice)){
                           System.out.println("Enter the first dice value:");
                           customDie1 = scanner.nextInt();

                           System.out.println("Enter the second dice value:");
                           customDie2 = scanner.nextInt();
                           if (customDie1 < 1 || customDie1 > 6 || customDie2 < 1 || customDie2 > 6) {
                               throw new InvalidEntryException("Invalid dice values. Please enter values between 1 and 6.");
                           }
                           manualDiceEntry = true;
                           diceValues = Die.diceEntry(customDie1, customDie2);
                       }

                       // Load and process commands from a test file
                       else if("T".equals(choice)){

                           currentCommandIndex = 0;
                           System.out.println("Please enter the name of the test file:");
                           String testFileName = scanner.nextLine();

                           try (BufferedReader fileReader = new BufferedReader(new FileReader(testFileName))) {
                               // Store each line from the file in a string array
                               commands = fileReader.lines().toArray(String[]::new);

                               // Process each command from the array
                               for (String command : commands) {
                                   System.out.println("Processing command from file: " + command);
                               }
                           } catch (IOException e) {
                               System.out.println("Error reading the test file: " + e.getMessage());
                           }
                           testCommandEntry = true;
                       }

                       // Display current player's move details
                       else if ("R".equals(choice)) {
                           if (currentplayer == 1) {
                               System.out.println(player1.getName() + "'s Move!");
                               pipp1 = myBoard.getPipCount(currentplayer);
                               System.out.println("Pipcount:" + pipp1);
                           }
                           if (currentplayer == 2) {
                               System.out.println(player2.getName() + "'s Move!");
                               pipp2 = myBoard.getPipCount(currentplayer);
                               System.out.println("Pipcount:" + pipp2);
                           }
                           if (round == 0) // Use dice thrown in inital round for first moves
                           {
                               System.out.println("Start with these Dice:");
                               round = round + 1;
                           } else {
                               System.out.println("Rolling the dice...");

                               if (!manualDiceEntry) {
                                   diceValues = Die.rollDice();
                               }
                               else {
                                   manualDiceEntry = false;
                               }


                               if (diceValues[0] == diceValues[1]) // double was thrown
                               {
                                   System.out.println("You have a double!");
                                   System.out.println("Play your two sets of dice sequentially");
                                   isdouble_int = 0; //while loop will be ran twice
                                   isdouble_bool = true; // needed so on second iteration of loop the dice can be rewritten to the original throw
                                   dice_copy = diceValues[0];
                               } else {
                                   isdouble_int = 1; // regular round only goes through loop once
                               }
                           }

                           int total = 0;

                           while (isdouble_int < 2) // when a double is rolled the loop is ran twice
                           {
                               if ((isdouble_bool == true) && (isdouble_int == 1)) //reset dice for second round of moves in case of double
                               {
                                   movesPossible = true;
                                   diceValues[0] = dice_copy;
                                   diceValues[1] = dice_copy;
                                   System.out.println("Second Round of Your Double");
                                   isdouble_bool = false;
                               }

                               while (movesPossible == true)// player has moves left to make
                               {
                                   freeCheckers.clear(); // clear calculated freeCheckers and destinations from last round
                                   destination.clear();

                                   for (int i = 0; i <= 1; i++) // Print Dice that are not 0 and total value
                                   {
                                       if (diceValues[i] != 0) {
                                           System.out.println("Die " + (i + 1) + ": " + diceValues[i]);
                                       }
                                   }
                                   total = diceValues[0] + diceValues[1];
                                   System.out.println();
                                   System.out.println("Total: " + total);
                                   System.out.println();

                                   if (myBoard.hasCheckerOnBeam(currentplayer)) // CheckeronBeam
                                   {
                                       System.out.println("You have a checker on the Beam");
                                       myBoard.printBoard(currentplayer);
                                       diceValues = myBoard.calculateMovesFromBeam(currentplayer, diceValues);
                                       if ((diceValues[0] == 0) && (diceValues[1] == 0)) {
                                           movesPossible = false; // end round -> no moves possible
                                           isdouble_int = 2; // cancel double

                                       }

                                   } else if (movesPossible == true) //free moves
                                   {
                                       freeCheckers = myBoard.findFreeCheckers(currentplayer, diceValues); // find free checkers
                                       if (myBoard.canBearOff(currentplayer)) {
                                           System.out.println("You can bear off!");
                                       }
                                       if (freeCheckers.size() == 0) {
                                           System.out.println("No Moves Possible!"); // no moves possible with this throw of dice
                                           movesPossible = false;
                                           break;
                                       }
                                       myBoard.highlightCheckersandPrint(freeCheckers, currentplayer); // highlight free checkers
                                       pickedChecker = this.promptUserPickChecker(freeCheckers, currentplayer); //ask user to pick a checker to move
                                       destination = myBoard.calculateMoves(pickedChecker, diceValues, currentplayer); // calculate the possible destinations for that checker
                                       int borneoffp2 = 0;
                                       int borneoffp1 = 0;

                                       for (int i = 0; i < destination.size(); i++) //show possible destinations of that checker to user
                                       {
                                           if (currentplayer == 1) {
                                               System.out.println("This Checker can move to: " + (destination.get(i) + 1));
                                           }
                                           if (currentplayer == 2) {
                                               System.out.println("This Checker can move to: " + (24 - destination.get(i)));
                                           }
                                       }

                                       diceValues = myBoard.promptUserPickDestination(pickedChecker, destination, currentplayer, diceValues,borneoffp1, borneoffp2 ); //ask user to pick destination and calculate new dice values


                                       freeCheckers = myBoard.findFreeCheckers(currentplayer, diceValues); //check if there are movable checkers left after the move with current dice combination
                                       if (freeCheckers.isEmpty()) //  if no moves possible -> End turn else start over
                                       {
                                           System.out.println("No Moves Possible!");
                                           movesPossible = false;
                                       }
                                       if (myBoard.checkForWin(currentplayer) == true) // End of game
                                       {
                                           System.out.println("Player: " + currentplayer + " won. End of Game!");
                                           isdouble_int = 2;
                                           movesPossible = false;
                                           endGame = true;
                                           int loser = 0;
                                           if (currentplayer == 1){
                                               score1+= 1*doublingCube.getValue();
                                               loser = 2;
                                           }
                                           else if (currentplayer == 2){
                                               score2 += 1*doublingCube.getValue();
                                               loser = 1;
                                           }//stake increase
                                           if (loser == 2){
                                               if(myBoard.checkForGammon(loser) && myBoard.canBearOff(2)){
                                                   score2 -= 2*doublingCube.getValue();
                                                   System.out.println("Player 2: " + player2.getName() + " You Lost! You have Gammoned! :(");
                                               } //stake decrease
                                               else if (myBoard.checkForGammon(loser) && !myBoard.canBearOff(2)) {
                                                   score2 -= 3*doublingCube.getValue();
                                                   System.out.println("Player 2: " + player2.getName() + " You Lost! You have BackGammoned! :(");
                                               }
                                               else score2 -= 1*doublingCube.getValue();
                                               System.out.println("Player 2:" + player2.getName() +" You have lost you lose a point");
                                           }
                                           if (loser == 1){
                                               if(myBoard.checkForGammon(loser) && myBoard.canBearOff(1)){
                                                   score1 -= 2*doublingCube.getValue();
                                                   System.out.println("Player 1: " + player1.getName() + " You Lost! You have Gammoned! :(");
                                               } //stake decrease
                                               else if (myBoard.checkForGammon(loser) && !myBoard.canBearOff(1)) {
                                                   score1 -= 3*doublingCube.getValue();
                                                   System.out.println("Player 1: " + player1.getName() + "You Lost! You have BackGammoned! :(");
                                               }
                                               else score1 -= 1*doublingCube.getValue();
                                               System.out.println("Player 1:" + player1.getName() +" You have lost you lose a point");
                                           }
                                           System.out.println("Score for player 1: " + score1 + " Score for player 2: " + score2);
                                           gameisover = true;
                                       }
                                   }

                               }
                               isdouble_int = isdouble_int + 1; //increase double loop counter
                           }
                           //At the end of the turn swap player and reset while loop variable
                           if (currentplayer == 1) {
                               currentplayer = 2;
                               movesPossible = true;

                           } else if (currentplayer == 2) {
                               currentplayer = 1;
                               movesPossible = true;
                           }
                       }
                       // Invalid entry handling
                       else {
                           throw new InvalidEntryException("Invalid entry. Please enter a valid command from the list below.");
                       }

                   } catch (InvalidEntryException e) {
                       System.out.println(e.getMessage());
                   }
                }

                // Update player scores at the end of the game
                player1.setScore(player1.getScore()+score1);
                player2.setScore(player2.getScore()+score2);

            }

    }


    public int promptUserPickChecker(List<Integer> movableCheckers, int playerMoving) {
        Scanner scanner = new Scanner(System.in);
        boolean successfulPick = false;
        System.out.println();
        int choice_int = 0;

        while (!successfulPick) {
            for (int i = 0; i < movableCheckers.size(); i++) {
                if (playerMoving == 1) {
                    System.out.println("Enter " + (i + 1) + " to move Checker at position " + (movableCheckers.get(i) + 1));
                }
                if (playerMoving == 2) {
                    System.out.println("Enter " + (i + 1) + " to move Checker at position " + (24 - (movableCheckers.get(i))));
                }
            }

            System.out.println();
            try {
                choice_int = scanner.nextInt() - 1; // remove offset from prompt

                if (choice_int >= 0 && choice_int < movableCheckers.size()) {
                    successfulPick = true;
                } else {
                    throw new InvalidEntryException("Invalid entry. Please enter a valid number from the list.");
                }
            } catch (InputMismatchException e) {
                // Handle the case where the user enters a non-integer
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            } catch (InvalidEntryException e) {
                System.out.println(e.getMessage());
            }
        }

        return movableCheckers.get(choice_int);
    }

//Created for GameTest class
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getMatchLength() {
        return matchLength;
    }

}




