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
    public void createBoard() //Inserts Checkers intheir initial positions
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
    {// takes a destination index and player moving and checks if the current player can move a checker there

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
    public List<Integer> findFreeCheckers(int playerMoving,int[] dice) // returns a list of integer indices of movable checkers for current player moving
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
    void highlightOneheckerandPrint(int index,int playerMoving) // Highlights the top Checker of  a chosen index green for current player
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
    void highlightCheckersandPrint(List<Integer> movablecheckers,int playerMoving) // highlights all movable checkers green for current player
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
    List<Integer> calculateMoves(int pickedChecker, int[] dice,int playerMoving) // calculates all the places that checker can move to and returns destination indices list
    {

        int[] dest_int=new int[3]; // all possible destinations [0] destination of die 0, [1] destination of die 1, [2] destination of die 1+2
        int destination_index =0;

        List<Integer> destinations = new ArrayList<>(4);
        //TODO BEARING OFF if all in last quarter, move could be to destination -1 and in promptUserPickDestination() have a if(destination==-1) --> bear off
        boolean bearingoffcondition=false;

        if(bearingoffcondition)
        {

        }
        else //normal moving
        {
            if(playerMoving==1) //moves down the board and array -> destination is pickedchecker - movAmount
            {
                dest_int[0]=pickedChecker-dice[0];
                dest_int[1]=pickedChecker-dice[1];
                dest_int[2]=pickedChecker-(dice[0]+dice[1]);

                for(int i=0;i<=2;i++) // run trough all combinatio [dice1], [dice2], [dice1+dice2]
                {
                    if (dest_int[i] >= 0) // valid destination
                    {
                        //  (combination of both) && (is free)&& (both dest 1 and dest 2 are free) && (destinaiton != pickedchecker)
                        if((i==2)&&(isSpaceAvailable(playerMoving, dest_int[i]))&&(destinations.size()==2)&&(dest_int[i]!=pickedChecker)) // for combinationn of both dice
                        {
                            destinations.add(destination_index, dest_int[i]); // save valid destination to return list
                            destination_index++;
                        }
                        else if((i<2)&&(isSpaceAvailable(playerMoving, dest_int[i]))&&(dest_int[i]!=pickedChecker)) // for individual dice
                        {
                            destinations.add(destination_index, dest_int[i]);
                            destination_index++;
                        }
                    }

                }
            }
            if(playerMoving==2) //moves up the board and array -> destination is pickedchecker + movAmount
            {
                dest_int[0]=pickedChecker+dice[0];
                dest_int[1]=pickedChecker+dice[1];
                dest_int[2]=pickedChecker+dice[0]+dice[1];

                for(int i=0;i<=2;i++)
                {
                    if (dest_int[i] <= 23) // valid destination
                    {
                        if((i==2)&&(isSpaceAvailable(playerMoving, dest_int[i]))&&(destinations.size()==2)&&(dest_int[i]!=pickedChecker)) //  (combination of both) && (is free)&& (both dest 1 and dest 2 are free)
                        {
                            destinations.add(destination_index, dest_int[i]);
                            destination_index++;
                        }
                        else if((i<2)&&(isSpaceAvailable(playerMoving, dest_int[i]))&&(dest_int[i]!=pickedChecker))
                        {
                            destinations.add(destination_index, dest_int[i]); // save valid destination to return list
                            destination_index++;
                        }
                    }

                }
            }

        }
        return destinations;
    }

    private void moveChecker(int fromIndex, int toIndex, int playerMoving) // executes actual moving of checker
    {
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

   public int[] promptUserPickDestination(int pickedChecker , List<Integer> destinations, int playerMoving, int[] dice) // ask user to pick the destination of Checker
   {
       int[] ret =new int[3];
       ret=dice;
       boolean succesfullPick=false;
       boolean resetflag =false;
       Scanner scanner = new Scanner(System.in);
       System.out.println("Where would you like to place your checker?: ");
       int choice_int=0;
       while(succesfullPick==false)
       {
           for(int i =0;i<destinations.size();i++) //print all possibilities
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
           String choice = scanner.nextLine(); // get user input
           choice_int =Integer.parseInt(choice)-1; // remove offset from prompt to fit array index

           if((choice_int>=0)&&(choice_int<destinations.size())) // if move is legal make it and highlight the moved checker
           {
               moveChecker(pickedChecker, destinations.get(choice_int), playerMoving);
               highlightOneheckerandPrint(destinations.get(choice_int),playerMoving);
               succesfullPick=true;
           }
           else
           {
               System.out.println("Wrong input, try again!");
           }
       }

       //Find the move that was made and set the according dice to 0, This is necessary as next loop iteration of playGame() will then calculate possible moves
       //with updated dice
       if(playerMoving==1)
       {
           int movedistance = pickedChecker - destinations.get(choice_int);
           if((movedistance==dice[0])&&(resetflag==false)) // resetflag prevents both dice getting wiped when they are the same on double round
           {
               dice[0]=0;
               resetflag=true;
           }
           if((movedistance==dice[1])&&(resetflag==false))
           {
               dice[1]=0;
               resetflag=true;
           }
           if((movedistance == (dice[0] + dice[1])) && (resetflag == false))
           {
               dice[0]=0;
               dice[1]=0;
           }
       }
       if(playerMoving==2)
       {

           int movedistance =destinations.get(choice_int) - pickedChecker;
           if((movedistance==dice[0])&&(resetflag==false))
           {
               dice[0]=0;
               resetflag=true;
           }
           if((movedistance==dice[1])&&(resetflag==false))
           {
               dice[1]=0;
               resetflag=true;
           }
           if((movedistance == (dice[0] + dice[1])) && (resetflag == false))
           {
               dice[0]=0;
               dice[1]=0;
               resetflag=true;
           }
       }


       return ret;

   }
    public int promptUserPickChecker(List<Integer> movableCheckers, int playerMoving) // asks user to pick a checker
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

