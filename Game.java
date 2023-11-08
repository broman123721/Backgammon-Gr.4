import javax.swing.*;
import java.util.List;
import java.util.Scanner;

class Game {

    private String player1;
    private String player2;

    public Game(String player1, String player2) {
        this.player1 = player1; //White
        this.player2 = player2; //Red
    }

    private boolean QuitOrRoll() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to roll the dice? (yes/no): ");
        String choice = scanner.nextLine().toLowerCase();

        if ("yes".equals(choice)) {
            return true;
        } else if ("no".equals(choice)) {
            return false;
        } //Maybe we should make an addition here or to the code later in case an invalid entry is put in?
          //Exception class might be better we can discuss this on Thursday.
        return false;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Let's roll the dice to determine the starting player...");

        int[] diceValues1 = Die.rollDice();

        int total1 = diceValues1[0] + diceValues1[1];

        int[] diceValues2 = Die.rollDice();
        int total2 = diceValues2[0] + diceValues2[1];

        if (total1 > total2) {
            System.out.println(player1 + " goes first!");
        } else {
            System.out.println(player2 + " goes first!");
        }

        while (true) {
            if (!QuitOrRoll()) {
                Scanner QuitOrContinue = new Scanner(System.in);
                System.out.println("Would you like to quit the game? (yes/no):");
                String choice = QuitOrContinue.nextLine().toLowerCase();

                if ("yes".equals(choice)) {
                    System.out.println("Thanks for playing! Goodbye!! :)");
                    break;
                } else if ("no".equals(choice)) continue;

            } else {
                System.out.println("Rolling the dice...");
                int[] diceValues = Die.rollDice();
                int total = 0;


                if(diceValues[0] == diceValues[1]){
                    System.out.println("You rolled a double!");
                    System.out.println("Die 1: " + diceValues[0]);
                    System.out.println("Die 2: " + diceValues[1]);
                    System.out.println("Die 3: " + diceValues[2]);
                    System.out.println("Die 4: " + diceValues[3]);
                }
                else{
                    System.out.println("Die 1: " + diceValues[0]);
                    System.out.println("Die 2: " + diceValues[1]);
                }

                total = diceValues[0] + diceValues[1] + diceValues[2] +diceValues[3];
                System.out.println("Total: " + total);
            }
        }
    }
    public int promptUserPick(List<Integer> movableCheckers,int playerMoving)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        for(int i =0;i<movableCheckers.size();i++)
        {
            if(playerMoving==1)
            {
                System.out.println("Enter "+(i+1)+" to move Checker at position "+(movableCheckers.get(i)+1));
            }
            if(playerMoving==2)
            {
                System.out.println("Enter "+(i+1)+" to move Checker at position "+(24-(movableCheckers.get(i))));
            }

        }
        System.out.println();
        String choice = scanner.nextLine();
        int choice_int =Integer.parseInt(choice)-1; // remove offset from prompt
        if((choice_int>=0)&&(choice_int<movableCheckers.size()))
        {
            return movableCheckers.get(choice_int);
        }
        else
        {
            return -1;
        }

    }
}



