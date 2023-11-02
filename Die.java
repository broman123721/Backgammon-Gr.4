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
        Die die3 = new Die(6);
        Die die4 = new Die(6);

        int result3 = 0;
        int result4 = 0;

        int result1 = die1.roll();
        int result2 = die2.roll();

        if(result1 == result2){
            result3 = result1;
            result4 = result1;
        }

        return new int[]{result1, result2, result3, result4};

     }
}
