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
    public static final String ANSI_GREEN = "\u001B[32m";
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
        List<Integer> destinations=new ArrayList<>();

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
                            destinations  = calculateMoves(i,dice,playerMoving);
                            if(!destinations.isEmpty())
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
                            destinations  = calculateMoves(i,dice,playerMoving);
                            int l=3;
                            if (!destinations.isEmpty())
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
    void highlightOneheckerandPrint(int index,int playerMoving)
    {
        Checker help=returnTop(index);
        int position=help.getPosition_int();
        this.Board_Checker2darr[index][position].setColor_str(ANSI_GREEN);
        printBoard(playerMoving);
        if(playerMoving==1)
        {
            this.Board_Checker2darr[index][position].setColor_str(ANSI_WHITE);
        }
        if(playerMoving==2)
        {
            this.Board_Checker2darr[index][position].setColor_str(ANSI_RED);
        }

    }
    void highlightCheckersandPrint(List<Integer> movablecheckers,int playerMoving)
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
               printBoard(playerMoving);
            }
        }

        for(int i=0;i<movablecheckers.size();i++) // restore color for top checker
        {
            help=returnTop(movablecheckers.get(i));
            position=help.getPosition_int();
            index=help.getIndex_int();
            if(playerMoving==1)
            {
                this.Board_Checker2darr[index][position].setColor_str(ANSI_WHITE);
            }
            if(playerMoving==2)
            {
                this.Board_Checker2darr[index][position].setColor_str(ANSI_RED);
            }
        }
    }
    List<Integer> calculateMoves(int pickedChecker, int[] dice,int playerMoving) // calculates all the places that checker can move to
    {
        int destination_p1=0;
        int destination_p2=0;
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
            int destination_index =0;

            if(dice[0]==dice[1]) // combinations dice[0], [0]+[1], [0]+[1]+[2], [0]+[1}+[2]+[3]
            {
                for(int i=0;i<=3;i++) // runs through all move combinations possible depending on dice
                {
                    destination_p1=pickedChecker;
                    destination_p2=pickedChecker;
                    combinedpoints=0; // holds the amount of points to move with current move

                    for(int u=0;u<=i;u++)
                    {
                        combinedpoints=combinedpoints+dice[u];
                    }
                    for(int m=0 ;m<combinedpoints;m++)
                    {
                        destination_p1=destination_p1-1;
                        destination_p2=destination_p2+1;
                    }
                    if(destination_p1==-1)
                    {
                        destination_p1=0;
                    }
                    if(destination_p2==24)
                    {
                        destination_p1=23;
                    }
                    if(playerMoving==1) // player 1 moves from 23 to 0 hence destination_index - combinedpoints
                    {
                        if (destination_p1 >= 0) //check for moves that would go out the playingfield
                        {
                            if (isSpaceAvailable(playerMoving, destination_p1))// checks if checker could move here
                            {
                                destinations.add(destination_index, destination_p1);
                                destination_index++;
                            }
                        }
                    }
                    if(playerMoving==2)
                    {
                        if(destination_p2<=23)
                        {
                            if(isSpaceAvailable(playerMoving,destination_p2))
                            {
                                destinations.add(destination_index, destination_p2);
                                destination_index++;
                            }
                        }

                    }

                }
            }
            if(dice[0]!=dice[1]) // Combiantions of dice dice[0], dice[1], dice[0]+dice[1]
            {
                int []combinations =new int[] {dice[0],dice[1],dice[0]+dice[1]};
                for(int i=0;i<=2;i++)
                {
                    destination_p1=pickedChecker;
                    destination_p2=pickedChecker;

                    for(int m=0 ;m<combinations[i];m++)
                    {
                        destination_p1=destination_p1-1;
                        destination_p2=destination_p2+1;
                    }
                    if(destination_p1==-1)
                    {
                        destination_p1=0;
                    }
                    if(destination_p2==24)
                    {
                        destination_p1=23;
                    }

                    if(playerMoving==1) // player 1 moves from 23 to 0 hence destination_index - combinedpoints
                    {
                        if (destination_p1 >= 0) //check for moves that would go out the playingfield
                        {
                            if (isSpaceAvailable(playerMoving, destination_p1))// checks if checker could move here
                            {
                                destinations.add(destination_index, destination_p1);
                                destination_index++;
                            }
                        }
                    }
                    if(playerMoving==2)
                    {
                        if(destination_p2<=23)
                        {
                            if(isSpaceAvailable(playerMoving,destination_p2))
                            {
                                destinations.add(destination_index, destination_p2);
                                destination_index++;
                            }
                        }

                    }

                }

            }




        }

        return destinations;
    }

    private void moveChecker(int fromIndex, int toIndex, int playerMoving){
        Checker topCheckerFrom = returnTop(fromIndex); // Checker we want to move
        Checker topCheckerTo = returnTop(toIndex); // Checker on which we will place the current one

        // Place the checker we want to move on top of the highest one at the destination
        Board_Checker2darr[toIndex][topCheckerTo.getPosition_int()+1] = Board_Checker2darr[fromIndex][topCheckerFrom.getPosition_int()];
        // Remove the checker from the current position
        Board_Checker2darr[fromIndex][topCheckerFrom.getPosition_int()] = null;
        // Reassign index and position
        Board_Checker2darr[toIndex][topCheckerTo.getPosition_int()+1].setPosition_int(topCheckerTo.getPosition_int()+1);
        Board_Checker2darr[toIndex][topCheckerTo.getPosition_int()+1].setIndex_int(toIndex);



        // Print the updated board
        this.highlightOneheckerandPrint(toIndex,playerMoving);

    }

   public int makeMove(int pickedChecker ,List<Integer> destinations, int playerMoving)
   {
       boolean succesfullPick=false;

       Scanner scanner = new Scanner(System.in);
       System.out.println("Where would you like to place your checker?: ");
       int choice_int=0;
       while(succesfullPick==false)
       {
           for(int i =0;i<destinations.size();i++)
           {
               if(playerMoving==1)
               {
                   System.out.println("Enter "+(i+1)+" to move Checker to position "+(destinations.get(i)+1));
               }
               if(playerMoving==2)
               {
                   System.out.println("Enter "+(i+1)+" to move Checker to position "+(24-(destinations.get(i))));
               }

           }
           String choice = scanner.nextLine();
           choice_int =Integer.parseInt(choice)-1; // remove offset from prompt
           if((choice_int>=0)&&(choice_int<destinations.size()))
           {
               moveChecker(pickedChecker, destinations.get(choice_int), playerMoving);
               succesfullPick=true;
           }
           else
           {
               System.out.println("Wrong input, try again!");
           }
       }
       return destinations.get(choice_int);

   }

}
