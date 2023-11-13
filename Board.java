import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board
{
    private Checker[][] Board_Checker2darr = new Checker[24][5];
    private Checker[] beamWhite_Checkerarr=new Checker[5];
    private Checker[] beamRed_Checkerarr=new Checker[5];
    private int barPointerWhite_int =0;
    private int barPointerRed_int =0;

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

    public void setBeamWhite_Checkerarr(Checker[] beamWhite_Checkerarr) {
        this.beamWhite_Checkerarr = beamWhite_Checkerarr;
    }

    public void setBeamRed_Checkerarr(Checker[] beamRed_Checkerarr) {
        this.beamRed_Checkerarr = beamRed_Checkerarr;
    }

    public void setBarPointerRed_int(int barPointerRed_int) {
        this.barPointerRed_int = barPointerRed_int;
    }

    public void setBarPointerWhite_int(int barPointerWhite_int) {
        this.barPointerWhite_int = barPointerWhite_int;
    }

    public void fillPoints(int index, int numCheckers, String Color) // index = pointNumber | numCheckers = number of Checkers
    {
        for(int i=0;i <numCheckers;i++)
        {
            if(Color.equals(ANSI_WHITE)) //Player 1
            {
                // Create Checker and set its color, index (1-24) and position up the stack
                this.Board_Checker2darr[index-1][i]=new Checker(Color,index-1,i,index);
            }
            if(Color.equals(ANSI_RED))
            {
                // Create Checker and set its color, index (24-1) and position up the stack
                this.Board_Checker2darr[index-1][i]=new Checker(Color,index-1,i,25-index);
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
    public int getPipCount(int playerMoving)
    {
        Checker help= new Checker("default",-1,-1,-1);
        int pipCount=0;

        for(int i=0; i<=23;i++) // Count pips from checkers on board
        {
            help=returnTop(i);
            if(playerMoving==1)
            {
               for(int u=0;u<=4;u++)
               {
                   help=this.Board_Checker2darr[i][u];
                   if ((help!=null)&&(help.getColor_str()==ANSI_WHITE))
                   {
                       pipCount=pipCount+help.getPip_int();
                   }
               }
            }
            if(playerMoving==2)
            {
                for(int u=0;u<=4;u++)
                {
                    help=this.Board_Checker2darr[i][u];
                    if ((help!=null)&&(help.getColor_str()==ANSI_RED))
                    {
                        pipCount=pipCount+help.getPip_int();
                    }
                }
            }
        }
        for(int i=0;i<=4;i++)  // Count pips from checkers on beam
        {
            if ((playerMoving==1)&&(beamWhite_Checkerarr[i]!=null))
            {
               pipCount=pipCount+ beamWhite_Checkerarr[i].getPip_int();
            }
            if ((playerMoving==2)&&(beamRed_Checkerarr[i]!=null))
            {
                pipCount=pipCount+ beamRed_Checkerarr[i].getPip_int();
            }
        }
        return pipCount;

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
        System.out.printf("%-24s","on Beam White:");

        //Print
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
        System.out.println();
        System.out.printf("%-24s","on Beam Red:");
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
        Checker ret=new Checker("default",-100,-100,-100);

        for(int i=0;i<=4;i++)
        {
            if(Board_Checker2darr[index][0]==null)
            {
                ret = new Checker("Blank",index,-1,-100); // if empty return checker with pos =-1
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

        Checker  help=new Checker("default",-100,-100,-100);
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
        Checker help=new Checker("default",-100,-100,-100);
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
    public int[] calculateMovesFromBeam(int playerMoving, int[] dice)
    {


        boolean nopickpossible=false;
        boolean succesfullPick=false;
        int[]destinations={-1,-1};
        Scanner scanner = new Scanner(System.in);
        int choice_int=0;

        if(playerMoving ==1)
        {
            for(int i=0;i<=1;i++)
            {
                if(dice[i]!=0)
                {
                    if(isSpaceAvailable(playerMoving,24-dice[i]))
                    {
                        destinations[i]=24-dice[i];
                    }
                }

            }
        }
        if(playerMoving ==2)
        {
            for(int i=0;i<=1;i++)
            {
                if(dice[i]!=0)
                {
                    if(isSpaceAvailable(playerMoving,dice[i]-1))
                    {
                        destinations[i]=dice[i]-1;
                    }
                }

            }
        }
        while(succesfullPick==false)
        {
            for(int i=0;i<2;i++)
            {
                if((playerMoving ==1)&&(destinations[i]!=-1))
                {
                    System.out.println("Enter "+(i+1)+" to move Checker to position "+(1+destinations[i]));
                }
                if((playerMoving ==2)&&(destinations[i]!=-1))
                {
                    System.out.println("Enter "+(i+1)+" to move Checker to position "+(24-destinations[i]));
                }
                else if((destinations[0]==-1)&&(destinations[1]==-1))
                {
                    if(i==1) // will go through loop twice so only print it once
                    {
                        System.out.println("No move Possible");
                    }

                    dice[0]=0;
                    dice[1]=0;
                    succesfullPick=true;
                    nopickpossible=true;
                }
            }
            if(nopickpossible==false)
            {
                choice_int = scanner.nextInt()-1; // get user input
                if((choice_int>=0)&&(choice_int<2)&&(destinations[choice_int]>=0)) // if move is legal make it and calculate new dice
                {
                    moveCheckerFromBeam(destinations[choice_int], playerMoving);
                    highlightOneheckerandPrint(destinations[choice_int],playerMoving);
                    dice[choice_int]=0; // reset dice
                    succesfullPick=true;
                }
                else
                {
                    System.out.println("Wrong input, try again!");
                }
            }


        }
        return dice;
    }
    private boolean checkForHit(int toIndex, int playerMoving)
    {

        Checker help =new Checker("default",-1,-1,-1);
        help=returnTop(toIndex);
        boolean ret=false;
        if(playerMoving==1)
        {
            if((help.getPosition_int()==0)&&(help.getColor_str().equals(ANSI_RED))) // checks if destination holds exactly 1 enemy Checker
                ret= true;
        }
        if(playerMoving==2)
        {
            if((help.getPosition_int()==0)&&(help.getColor_str().equals(ANSI_WHITE)))
                ret= true;
        }

        return ret;

    }
    private void moveChecker(int fromIndex, int toIndex, int playerMoving) // executes actual moving of checker
    {
        if(checkForHit(toIndex,playerMoving)==false) // not a hit -> normal move
        {
            Checker topCheckerFrom = returnTop(fromIndex); // Checker we want to move
            Checker topCheckerTo = returnTop(toIndex); // Checker on which we will place the current one

            if(playerMoving==1) //Update Pip
            {
                Board_Checker2darr[fromIndex][topCheckerFrom.getPosition_int()].setPip_int(toIndex+1);
            }
            if(playerMoving==2)
            {
                Board_Checker2darr[fromIndex][topCheckerFrom.getPosition_int()].setPip_int(24-toIndex);
            }

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
        if(checkForHit(toIndex,playerMoving)==true) // hit
        {
            Checker topCheckerFrom = returnTop(fromIndex); // Checker we want to move
            Checker topCheckerTo = returnTop(toIndex); // Checker to be hit

            if(playerMoving==1)
            {
                topCheckerTo.setPip_int(25); // update pip for hit red checker
                beamRed_Checkerarr[barPointerRed_int]=topCheckerTo; // place checker on beam
                beamRed_Checkerarr[barPointerRed_int].setIndex_int(barPointerRed_int);
                barPointerRed_int = barPointerRed_int +1; // increase Stackpointer

                Board_Checker2darr[toIndex][0] = Board_Checker2darr[fromIndex][topCheckerFrom.getPosition_int()]; //move hitting checker
                Board_Checker2darr[fromIndex][topCheckerFrom.getPosition_int()] = null; // remove hitting checker from old position

                Board_Checker2darr[toIndex][0].setIndex_int(toIndex);
                Board_Checker2darr[toIndex][0].setPosition_int(0); // will be new checker at pos 0
                Board_Checker2darr[toIndex][0].setPip_int(toIndex+1);
            }
            if(playerMoving==2)
            {
                topCheckerTo.setPip_int(25); // update pip for hit white checker
                beamWhite_Checkerarr[barPointerWhite_int]=topCheckerTo; // place checker on beam
                beamWhite_Checkerarr[barPointerWhite_int].setIndex_int(barPointerWhite_int);
                barPointerWhite_int = barPointerWhite_int +1; // increase Stackpointer

                Board_Checker2darr[toIndex][0] = Board_Checker2darr[fromIndex][topCheckerFrom.getPosition_int()]; //move hitting checker
                Board_Checker2darr[fromIndex][topCheckerFrom.getPosition_int()] = null; // remove hitting checker from old position

                Board_Checker2darr[toIndex][0].setIndex_int(toIndex);
                Board_Checker2darr[toIndex][0].setPosition_int(0); // will be new checker at pos 0
                Board_Checker2darr[toIndex][0].setPip_int(24-toIndex);
            }

        }



    }

    public void moveCheckerFromBeam(int toIndex, int playerMoving)
    {
        Checker help=returnTop(toIndex); // get checker on which we will move our checker
        int height=help.getPosition_int(); // get the position where we will place our new checker in

        if(checkForHit(toIndex,playerMoving)==false) // no hit
        {
            if (playerMoving==1)
            {
                Board_Checker2darr[toIndex][height+1]=this.beamWhite_Checkerarr[this.barPointerWhite_int -1]; //place Checker in Board
                Board_Checker2darr[toIndex][height+1].setIndex_int(toIndex); // adjust member variables
                Board_Checker2darr[toIndex][height+1].setPosition_int(height+1);
                Board_Checker2darr[toIndex][height+1].setPip_int(toIndex+1);

                for(int i=0;i<4;i++) // move up
                {
                    beamWhite_Checkerarr[i]=beamWhite_Checkerarr[i+1];
                }
                barPointerWhite_int = barPointerWhite_int -1; // decrease beam stack counter
            }
            if (playerMoving==2)
            {
                Board_Checker2darr[toIndex][height+1]=this.beamRed_Checkerarr[this.barPointerRed_int -1]; //place Checker in Board
                Board_Checker2darr[toIndex][height+1].setIndex_int(toIndex); // adjust member variables
                Board_Checker2darr[toIndex][height+1].setPosition_int(height+1);
                Board_Checker2darr[toIndex][height+1].setPip_int(24-toIndex);

                for(int i=0;i<4;i++) // move up
                {
                    beamRed_Checkerarr[i]=beamRed_Checkerarr[i+1];
                }
                barPointerRed_int = barPointerRed_int -1; // decrease beam stack counter
            }
        }
        if(checkForHit(toIndex,playerMoving)==true)
        {
            //Checker topCheckerFrom = returnTop(fromIndex); // Checker we want to move
            Checker topCheckerTo = returnTop(toIndex); // Checker to be hit

            if(playerMoving==1)
            {
                topCheckerTo.setPip_int(25); // update pip for hit red checker
                beamRed_Checkerarr[barPointerRed_int]=topCheckerTo; // place checker on beam
                beamRed_Checkerarr[barPointerRed_int].setIndex_int(barPointerRed_int);
                barPointerRed_int = barPointerRed_int +1; // increase Stackpointer

                Board_Checker2darr[toIndex][0]=this.beamWhite_Checkerarr[this.barPointerWhite_int -1]; //place Checker in Board
                Board_Checker2darr[toIndex][0].setIndex_int(toIndex); // adjust member variables
                Board_Checker2darr[toIndex][0].setPosition_int(0);
                Board_Checker2darr[toIndex][0].setPip_int(toIndex+1);

                for(int i=0;i<4;i++) // move up
                {
                    beamWhite_Checkerarr[i]=beamWhite_Checkerarr[i+1];
                }
                barPointerWhite_int = barPointerWhite_int -1; // decrease beam stack counter

            }
            if(playerMoving==2)
            {
                topCheckerTo.setPip_int(25); // update pip for hit white checker
                beamWhite_Checkerarr[barPointerWhite_int]=topCheckerTo; // place checker on beam
                beamWhite_Checkerarr[barPointerWhite_int].setIndex_int(barPointerWhite_int);
                barPointerWhite_int = barPointerWhite_int +1; // increase Stackpointer

                Board_Checker2darr[toIndex][0]=this.beamRed_Checkerarr[this.barPointerRed_int -1]; //place red checker in Board
                Board_Checker2darr[toIndex][0].setIndex_int(toIndex); // adjust member variables
                Board_Checker2darr[toIndex][0].setPosition_int(0);
                Board_Checker2darr[toIndex][0].setPip_int(24-toIndex);

                for(int i=0;i<4;i++) // move up
                {
                    beamRed_Checkerarr[i]=beamRed_Checkerarr[i+1];
                }
                barPointerRed_int = barPointerRed_int -1; // decrease beam stack counter
            }
        }


    }
    public boolean hasCheckerOnBeam(int playerMoving)
    {
        boolean ret=false;

        if((playerMoving==1)&&(barPointerWhite_int >=1))
        {
            ret=true;
        }
        if((playerMoving==2)&&(barPointerRed_int >=1))
        {
            ret=true;
        }
        return ret;
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
           choice_int = scanner.nextInt()-1; // get user input

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


}

