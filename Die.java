import java.util.Random;

class Die {
    private int sides_int;
    public Die(int sides){
        this.sides_int = sides;
    }

    public int roll(){
        Random random = new Random();
        return random.nextInt(sides_int) + 1;
    }

    public static int[] rollDice(){
        Die die1 = new Die(6); // Six-sided die
        Die die2 = new Die(6); // Another six-sided die

        int result1 = die1.roll();
        int result2 = die2.roll();

        return new int[]{result1, result2};

     }

     //Dice command causes the subsequent dice roll
     public static int[] diceEntry(int die1, int die2){
        System.out.println("*MANUAL DICE ENTRY*");

        return new int[]{die1, die2};
     }

}
