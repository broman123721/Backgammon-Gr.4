import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class testGame {

    private Player player1;
    private Player player2;

    private int matchLength;

    public testGame(Player player1, Player player2, int matchLength) {
        this.player1 = player1; //White
        this.player2 = player2; //Red
        this.matchLength = matchLength; //Length of match
    }



    public int promptUserPickChecker(List<Integer> movableCheckers, int playerMoving) {
        Scanner scanner = new Scanner(System.in);
        boolean successfulPick = false;
        System.out.println();
        int choice_int = 0;

        while (!successfulPick) {
            for (int i = 0; i < movableCheckers.size(); i++) {
                if (playerMoving == 1) {
                    System.out.println("Enter " + (i + 1) + " to move Checker at position " + (movableCheckers.get(i) + 1));
                }
                if (playerMoving == 2) {
                    System.out.println("Enter " + (i + 1) + " to move Checker at position " + (24 - (movableCheckers.get(i))));
                }
            }

            System.out.println();
            try {
                choice_int = scanner.nextInt() - 1; // remove offset from prompt

                if (choice_int >= 0 && choice_int < movableCheckers.size()) {
                    successfulPick = true;
                } else {
                    throw new InvalidEntryException("Invalid entry. Please enter a valid number from the list.");
                }
            } catch (java.util.InputMismatchException e) {
                // Handle the case where the user enters a non-integer
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            } catch (InvalidEntryException e) {
                System.out.println(e.getMessage());
            }
        }

        return movableCheckers.get(choice_int);
    }

}
