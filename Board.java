import java.util.ArrayList;
import java.util.List;

public class Board
{
    private Checker[][] Board_Checker2darr = new Checker[24][5];
    private Checker[] beamWhite_Checkerarr=new Checker[5];
    private Checker[] beamRed_Checkerarr=new Checker[5];
    private int stake_int;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_GREEN = "\u001B[34m";
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
                this.Board_Checker2darr[index-1][i]=new Checker(Color,24-index,i);
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
    public Checker returnTop(int index) // Returns Top value of selected index
    {
        Checker ret=new Checker("default",-100,-100);

        for(int i=0;i<=4;i++)
        {
            if(Board_Checker2darr[index][0]==null)
            {
                ret = new Checker("Blank",index,-1); // if empty retunr checker with pos =-1
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


    }
    public List<Integer> findFreeCheckers(int playerMoving)
    {
        List<Integer> ret = new ArrayList<>();

        Checker  help=new Checker("default",-100,-100);
        int u=0;
        for(int i=0;i<=23;i++)
        {
            help=returnTop(i);
            if(help.getPosition_int()>=0)
            {
                switch(playerMoving)
                {
                    case(1): // White moving (0-23 indexing)
                        if(help.getColor_str().equals(ANSI_WHITE))
                        {
                            ret.add(u,i);
                            u++;
                            break;
                        }
                        else break;
                    case(2):  // Red moving (23-0 indexing)
                        if(help.getColor_str().equals(ANSI_RED))
                        {
                            ret.add(u,23-i);
                            u++;
                            break;
                        }
                        else break;
                    default:
                }

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

        for(int i=0;i<movablecheckers.size();i++)
        {
            help=returnTop(movablecheckers.get(i));
            color=help.getColor_str();
            position=help.getPosition_int();
            index=help.getIndex_int();
            this.Board_Checker2darr[index][position].setColor_str(ANSI_GREEN);
            if(i==movablecheckers.size()-1)
            {
                if (color == ANSI_WHITE) // player 1
                {
                    this.printBoard(1);
                }
                if (color == ANSI_RED) // player 1
                {
                    this.printBoard(2);
                }

            }
        }
        for(int i=0;i<movablecheckers.size();i++)
        {
            help=returnTop(movablecheckers.get(i));
            color=help.getColor_str();
            position=help.getPosition_int();
            index=help.getIndex_int();
            this.Board_Checker2darr[index][position].setColor_str(color);
        }
    }

}
