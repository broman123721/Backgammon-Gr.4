import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board
{
    private Checker[][] Board_Checker2darr = new Checker[24][5];
    private Checker[] beamWhite_Checkerarr=new Checker[5];
    private Checker[] beamRed_Checkerarr=new Checker[5];
    private int stake_int;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public Board(int stake_int)
    {
        this.stake_int=stake_int;
    }

    public void fillPoints(int index, int numCheckers,String Color) // index = pointNumber | numCheckers = number of Checkers
    {
        for(int i=0;i <numCheckers;i++)
        {
            if(Color.equals(ANSI_WHITE)) //Player 1
            {
                // Create Checker and set its color, index (1-24) and position up the stack
                this.Board_Checker2darr[index-1][i]=new Checker(Color,index-1,i);
            }
            if(Color.equals(ANSI_RED))
            {
                // Create Checker and set its color, index (24-1) and position up the stack
                this.Board_Checker2darr[index-1][i]=new Checker(Color,index-1,i);
            }

        }
    }
    public void createBoard()
    {
        fillPoints(1,2,ANSI_RED);
        fillPoints(6,5,ANSI_WHITE);
        fillPoints(8,3,ANSI_WHITE);
        fillPoints(12,5,ANSI_RED);
        fillPoints(13,5,ANSI_WHITE);
        fillPoints(17,3,ANSI_RED);
        fillPoints(19,5,ANSI_RED);
        fillPoints(24,2,ANSI_WHITE);
    }



    public void printBoard(int playerTurn) //depending on playerturn the numbers will be printed 1 = Red 2 = White
    {
        System.out.println();
        // Print point number Top
        switch (playerTurn)
        {

            case(1):
                for(int i=12;i<=23;i++)
                {
                    System.out.printf("%-6d",i+1);
                    if(i==17)
                    {
                        System.out.printf("%-15s",ANSI_WHITE+"XXX"+ANSI_RESET);
                    }

                }
                break;
            case(2):
                for(int i=11;i>=0;i--)
                {
                    System.out.printf("%-6d",i+1);
                    if(i==6)
                    {
                        System.out.printf("%-15s",ANSI_WHITE+"XXX"+ANSI_RESET);
                    }

                }
                break;

        }
        System.out.println();
        //Print top
        for(int u=0;u<=4;u++)
        {
            for(int i=12;i<=23;i++)
            {
                if(this.Board_Checker2darr[i][u]!=null)
                {
                    System.out.printf("%-15s","["+this.Board_Checker2darr[i][u].getColor_str()+ "o"+ANSI_RESET+"]");
                }
                else
                {
                    System.out.printf("%-15s",ANSI_WHITE+"[ ] "+ANSI_RESET);
                }
                if(i==17)
                {
                    System.out.printf("%-15s",ANSI_WHITE+"XXX"+ANSI_RESET);
                }

             }
            System.out.println();
        }
        System.out.println();
        System.out.printf("%-24s","on Beam:");
        switch(playerTurn)
        {
            case(1):
                for(int v=0;v<=4;v++)
                {
                    if(this.beamWhite_Checkerarr[v]==null)
                    {
                        System.out.printf("%-15s",ANSI_WHITE+"[ ]"+ANSI_RESET);
                    }
                    else
                    {
                        System.out.printf("%-15s","["+this.beamWhite_Checkerarr[v].getColor_str()+ "o"+ANSI_RESET+"]");
                    }
                }
                break;
            case(2):
                for(int v=0;v<=4;v++)
                {
                    if(this.beamRed_Checkerarr[v]==null)
                    {
                        System.out.printf("%-15s",ANSI_WHITE+"[ ]"+ANSI_RESET);
                    }
                    else
                    {
                        System.out.printf("%-15s","["+this.beamRed_Checkerarr[v].getColor_str()+ "o"+ANSI_RESET+"]");
                    }
                }
                break;
        }
        System.out.println();
        System.out.println();

        //Print bottom
        for(int u=4;u>=0;u--)
        {
            for(int i=11;i>=0;i--)
            {
                if(this.Board_Checker2darr[i][u]!=null)
                {
                    System.out.printf("%-15s","["+this.Board_Checker2darr[i][u].getColor_str()+ "o"+ANSI_RESET+"]");
                }
                else
                {
                    System.out.printf("%-15s",ANSI_WHITE+"[ ] "+ANSI_RESET);
                }
                if(i==6)
                {
                    System.out.printf("%-15s",ANSI_WHITE+"XXX"+ANSI_RESET);
                }

            }
            System.out.println();
        }
        // Print point number Bottom
        switch (playerTurn)
        {
            case(1):
                for(int i=11;i>=0;i--)
                {
                    System.out.printf("%-6d",i+1);
                    if(i==6)
                    {
                        System.out.printf("%-15s",ANSI_WHITE+"XXX"+ANSI_RESET);
                    }

                }
                break;
            case(2):
                for(int i=12;i<=23;i++)
                {
                    System.out.printf("%-6d",i+1);
                    if(i==17)
                    {
                        System.out.printf("%-15s",ANSI_WHITE+"XXX"+ANSI_RESET);
                    }

                }
                break;
        }
        System.out.println();
    }
    public Checker returnTop(int index) // Returns Top value of selected index (0-23)
    {
        Checker ret=new Checker("default",-100,-100);

        for(int i=0;i<=4;i++)
        {
            if(Board_Checker2darr[index][0]==null)
            {
                ret = new Checker("Blank",index,-1); // if empty return checker with pos =-1
                break;
            }
            if(Board_Checker2darr[index][i]==null)
            {
                break;
            }
            if(Board_Checker2darr[index][i]!=null)
            {
                ret = Board_Checker2darr[index][i];
            }
        }
        return ret;
    }
    public boolean isSpaceAvailable(int playerMoving,int index_destination)
    {
        Checker  help=returnTop(index_destination);
        boolean ret=false;

        switch(playerMoving)
        {
            case(1): // We are playing as White therefore we are concerned about spots with two or more Red Checkers and full points
                if((help.getColor_str().equals(ANSI_RED)&&(help.getPosition_int()>=1))||(help.getPosition_int()>=4))
                {
                    ret = false;
                }
                else
                    ret= true;

                break;

            case(2): // We are playing as Red therefore we are concerned about spots with two or more White Checkers and full points
                if((help.getColor_str().equals(ANSI_WHITE)&&(help.getPosition_int()>=1))||(help.getPosition_int()>=4))
                {
                    ret = false;
                }
                else
                    ret= true;

                break;
            default:
                ret=false;
                break;
        }
        return ret;

//
    }
    public List<Integer> findFreeCheckers(int playerMoving,int[] dice)
    {
        List<Integer> ret = new ArrayList<>();

        Checker  help=new Checker("default",-100,-100);
        int u=0;
        for(int i=0;i<=23;i++)
        {
            help=returnTop(i);
            if(help.getPosition_int()>=0) // At least one checker at the index
            {
                switch(playerMoving)
                {
                    case(1): // White moving
                        if(help.getColor_str().equals(ANSI_WHITE))
                        {
                            if(canMove(playerMoving,dice,i))
                            {
                                ret.add(u,i);
                                u++;
                                break;
                            }
                        }
                        else break;
                    case(2):  // Red moving
                        if(help.getColor_str().equals(ANSI_RED))
                        {
                            if (canMove(playerMoving,dice,i))
                            {
                                ret.add(u,i);
                                u++;
                                break;
                            }

                        }
                        else break;
                    default:
                }

            }
        }

    return ret;

    }
    boolean canMove(int playerMoving,int[] dice,int index) // calculates whether a checker at particular point can move with given dice
    {
        // all possible dice combinations are calculated and evaluated
        int combinedpoints; //saves the current dice combination
        boolean ret=false;
        for(int i=0;i<=3;i++) // runs through all move combinations possible depending on dice
        {
           combinedpoints=0; // holds the amount of points to move with current move

            for(int u=0;u<=i;u++) // for i=0 combinedpoints = dice[0] for i=1 combinedpoints = dice[0]+dice[1] ...
            {
                combinedpoints=combinedpoints+dice[u];
            }
            switch (playerMoving) // to distinguish between moving up and down the board
            {
                case(1): // player 1 moves from 23 to 0 hence index - combinedpoints
                    if((index-combinedpoints<=23)&&(index-combinedpoints>=0)) //check for moves that would go out the playingfield
                    {
                        if(isSpaceAvailable(playerMoving,index-combinedpoints))// checks if checker could move here
                        {
                            ret = true;
                            break;
                        }
                    }
                case(2): // player 2 moves from 0-23 hence index + combinedpoints
                    if((index+combinedpoints<=23)&&(index+combinedpoints>=0))
                    {
                        if(isSpaceAvailable(playerMoving,index+combinedpoints))
                        {
                            ret = true;
                            break;
                        }
                    }

                default:
                    break;
            }

        }
        return ret;
    }
    void highlightCheckersandPrint(List<Integer> movablecheckers)
    {
        Checker help=new Checker("default",-100,-100);
        String color;
        int index;
        int position;

        for(int i=0;i<movablecheckers.size();i++) // run through all movable checkers
        {
            help=returnTop(movablecheckers.get(i)); // extract top checker and find out whose it is
            color=help.getColor_str();
            position=help.getPosition_int();
            index=help.getIndex_int();
            this.Board_Checker2darr[index][position].setColor_str(ANSI_BLUE); //temporarily color top checker
            if(i==movablecheckers.size()-1) //print board depending on whose checkers are moved
            {
                if (color == ANSI_WHITE) // player 1
                {
                    this.printBoard(1);
                }
                if (color == ANSI_RED) // player 2
                {
                    this.printBoard(2);
                }

            }
        }
        for(int i=0;i<movablecheckers.size();i++) // restore color for top checker
        {
            help=returnTop(movablecheckers.get(i));
            color=help.getColor_str();
            position=help.getPosition_int();
            index=help.getIndex_int();
            this.Board_Checker2darr[index][position].setColor_str(color);
        }
    }
    List<Integer> calculateMoves(int pickedChecker, int[] dice,int playerMoving) // calculates all the places that checker can move to
    {
        List<Integer> destinations = new ArrayList<>(4);
        //TODO BEARING OFF if all in last quarter, move could be to destination -1 and in makeMove() have a if(destination==-1) --> bear off
        boolean bearingoffcondition=false;
        if(bearingoffcondition)
        {

        }
        else //normal moving - adaption from canMove() function
        {
            // all possible dice combinations are calculated and evaluated
            int combinedpoints; //saves the current dice combination
            int index=0;
            for(int i=0;i<=3;i++) // runs through all move combinations possible depending on dice
            {
                combinedpoints=0; // holds the amount of points to move with current move

                for(int u=0;u<=i;u++) // for i=0 combinedpoints = dice[0] for i=1 combinedpoints = dice[0]+dice[1] ...
                {
                    combinedpoints=combinedpoints+dice[u];
                }
                switch (playerMoving) // to distinguish between moving up and down the board
                {
                    case(1): // player 1 moves from 23 to 0 hence index - combinedpoints
                        if((pickedChecker-combinedpoints<=23)&&(pickedChecker-combinedpoints>=0)) //check for moves that would go out the playingfield
                        {
                            if(isSpaceAvailable(playerMoving,pickedChecker-combinedpoints))// checks if checker could move here
                            {
                                destinations.add(index,pickedChecker-combinedpoints);
                                index++;
                                break;
                            }
                        }
                    case(2): // player 2 moves from 0-23 hence index + combinedpoints
                        if((pickedChecker+combinedpoints<=23)&&(pickedChecker+combinedpoints>=0))
                        {
                            if(isSpaceAvailable(playerMoving,pickedChecker+combinedpoints))
                            {
                                destinations.add(index,pickedChecker+combinedpoints-1);
                                index++;
                                break;
                            }
                        }

                    default:
                        break;
                }

            }

        }

        return destinations;
    }

    private void moveChecker(int fromIndex, int toIndex, int playerMoving){
        Checker topChecker = returnTop(fromIndex);
        Checker destinationTopChecker = returnTop(toIndex);
        Checker newChecker = new Checker(topChecker.getColor_str(), toIndex, destinationTopChecker.getPosition_int() + 1);

        // Remove the checker from the current position
        Board_Checker2darr[fromIndex][topChecker.getPosition_int()] = null;

        // Place the checker in the new position
        Board_Checker2darr[toIndex][destinationTopChecker.getPosition_int()+1] = newChecker;

        // Print the updated board
        if (playerMoving == 1) {
            printBoard(1);
        } else if (playerMoving == 2) {
            printBoard(2);
        }
    }

   public void makeMove(int pickedChecker ,List<Integer> destinations, int playerMoving) {
           System.out.println("Out of the list above where would you like to place your checker?: ");
           Scanner scanner = new Scanner(System.in);
           int choice = scanner.nextInt();

           System.out.println("Destinations" + destinations);

       for(int i=0;i<destinations.size()+1;i++) {
           if (playerMoving == 2) {
               if (choice == 24 - destinations.get(i)) {
                   System.out.println("Invalid destination"); //Can create an exception class for this later
               } else {
                   moveChecker(pickedChecker, choice, playerMoving);
               }
           } else if (playerMoving == 1) {
               if (choice == (destinations.get(i))) {
                   System.out.println("Invalid destination"); //Can create an exception class for this later
               } else {
                   moveChecker(pickedChecker, choice, playerMoving);
               }
           }
       }

   }

}
