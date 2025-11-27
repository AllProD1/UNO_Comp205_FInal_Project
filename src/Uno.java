import java.util.Scanner;

public class Uno {
    public static void main(String[] args) {
        System.out.println("Lets play Uno");

        Scanner userInput = new Scanner(System.in);

        int numRealPlayers = -1;
        int numBots = -1;

        do {
            System.out.print("How many REAL players do you want to play? (1-10): ");
            try {
                numRealPlayers = Integer.parseInt(userInput.nextLine());

                if (numRealPlayers < 1) {
                    System.out.println("Number too small.");
                } else if (numRealPlayers > 10) {
                    System.out.println("Number to big.");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Not a valid number.");
            }
        } while (numRealPlayers < 1 || numRealPlayers > 10);

        do {
            System.out.printf("How many BOTS do you want to play? (0-%d): ", 10-numRealPlayers);
            try {
                numBots = Integer.parseInt(userInput.nextLine());

                if (numBots < 0) {
                    System.out.println("Number too small.");
                } else if (numBots > 10-numRealPlayers) {
                    System.out.println("Number to big.");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Not a valid number.");
            }
        } while (numBots < 0 || numBots > 10-numRealPlayers);

        Game uno = new Game(numRealPlayers + numBots, numRealPlayers);
        uno.StartGame();

        while(uno.hasWon() == -1) {
            uno.playTurn();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // Hides last players hand.
            System.out.printf("Player %d's turn: Enter any key to view your hand.", uno.getCurrPlayer() + 1);
            userInput.nextLine();
        }

        System.out.printf("\n\nPlayer %d won!", uno.hasWon()+1);
    }
}
