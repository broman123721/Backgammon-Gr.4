import java.util.Scanner;

class Game {

    private String player1;
    private String player2;

    public Game(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
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
    }

}