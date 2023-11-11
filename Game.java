import javax.swing.*;
import java.util.ArrayList;
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
        int currentplayer=-1;
        boolean dicedraw=true;
        int[] diceValues=new int[4];

        System.out.println("Let's roll the dice to determine the starting player...");
        while(dicedraw==true)
        {
            diceValues = Die.rollDice();
            System.out.println("DICE Player 1:"+"["+diceValues[0]+"]"+"["+diceValues[1]+"]"+"="+(diceValues[0]+diceValues[1]));
            int total1 = diceValues[0] + diceValues[1];

            diceValues = Die.rollDice();
            System.out.println("DICE Player 2:"+"["+diceValues[0]+"]"+"["+diceValues[1]+"]"+"="+(diceValues[0]+diceValues[1]));
            int total2 = diceValues[0] + diceValues[1];

            if (total1 > total2)
            {
                System.out.println(player1 + " goes first!");
                currentplayer=1;
                dicedraw=false;
            }
            if(total1 < total2)
            {
                System.out.println(player2 + " goes first!");
                currentplayer=2;
                dicedraw=false;
            }
            if(total1==total2)
            {
                System.out.println("Draw! Lets roll again!");
            }
        }

        //Setup Game, Create Board and necessary variables
        Board myBoard=new Board(8);
        myBoard.createBoard();
        myBoard.printBoard(currentplayer);
        List<Integer> freeCheckers = new ArrayList<>();
        List<Integer> destination = new ArrayList<>();
        int pickedChecker = -1;
        int destinationindex = -1;
        while (true)
        {
            if (!QuitOrRoll())
            {
                Scanner QuitOrContinue = new Scanner(System.in);
                System.out.println("Would you like to quit the game? (yes/no):");
                String choice = QuitOrContinue.nextLine().toLowerCase();

                if ("yes".equals(choice)) {
                    System.out.println("Thanks for playing! Goodbye!! :)");
                    break;
                } else if ("no".equals(choice)) continue;

            }
            else
            {
                freeCheckers.clear();
                destination.clear();

                if(currentplayer==1)
                {
                    System.out.println(player1+"'s Move!");
                }
                if(currentplayer==2)
                {
                    System.out.println(player2+"'s Move!");
                }

                System.out.println("Rolling the dice...");
                diceValues = Die.rollDice();
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
                System.out.println();

                freeCheckers =myBoard.findFreeCheckers(currentplayer,diceValues);
                for(int i = 0; i< freeCheckers.size(); i++)
                {
                    if (currentplayer==1)
                    {
                        System.out.println("Movable Checker at "+ (freeCheckers.get(i)+1));
                    }
                    if (currentplayer==2)
                    {
                        System.out.println("Movable Checker at "+ (24-freeCheckers.get(i)));
                    }

                }
                myBoard.highlightCheckersandPrint(freeCheckers,currentplayer);
                pickedChecker=this.promptUserPick(freeCheckers,currentplayer);
                destination=myBoard.calculateMoves(pickedChecker,diceValues,currentplayer);

                for(int i=0;i<destination.size();i++)
                {
                    if(currentplayer==1)
                    {
                        System.out.println("This Checker can move to: " + (destination.get(i)+1)); //maybe put this in a variable
                    }
                    if(currentplayer==2)
                    {
                        System.out.println("This Checker can move to: " + (24-destination.get(i))); //maybe put this in a variable
                    }
                }
                destinationindex=myBoard.makeMove(pickedChecker,destination,currentplayer);




                if(currentplayer==1)
                {
                    currentplayer=2;

                }
                else if(currentplayer==2)
                {
                    currentplayer=1;
                }
            }
        }
    }
    public int promptUserPick(List<Integer> movableCheckers,int playerMoving)
    {
        Scanner scanner = new Scanner(System.in);
        boolean succesfullPick=false;
        System.out.println();
        int choice_int=0;
        while(succesfullPick==false)
        {
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

            choice_int =Integer.parseInt(choice)-1; // remove offset from prompt
            if((choice_int>=0)&&(choice_int<movableCheckers.size()))
            {
               succesfullPick=true;

            }
            else
            {
                System.out.println("Wrong input, try again!");
                succesfullPick=false;
            }
        }

        return movableCheckers.get(choice_int);
    }
}



