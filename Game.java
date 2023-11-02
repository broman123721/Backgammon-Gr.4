import java.util.Scanner;

class Game {

    private String player1;
    private String player2;

    public Game(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
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
                System.out.println("Thanks for playing! Goodbye!");
                break;
            } else {
                System.out.println("Rolling the dice...");
                int[] diceValues = Die.rollDice();
                int total = 0;
                System.out.println("Die 1: " + diceValues[0]);
                System.out.println("Die 2: " + diceValues[1]);

                if(diceValues[0] == diceValues[1]){
                    System.out.println("You rolled a double!");
                    System.out.println("Die 3: " + diceValues[2]);
                    System.out.println("Die 4: " + diceValues[3]);
                }

                total = diceValues[0] + diceValues[1] + diceValues[2] +diceValues[3];
                System.out.println("Total: " + total);
            }
        }
    }
}



