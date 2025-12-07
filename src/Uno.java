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

        if (10-numRealPlayers > 0) {

            do {
                System.out.printf("How many BOTS do you want to play? (%d-%d): ", Math.max(0, 2 - numRealPlayers), 10 - numRealPlayers);
                try {
                    numBots = Integer.parseInt(userInput.nextLine());

                    if (numBots < 0) {
                        System.out.println("Number too small.");
                    } else if (numBots > 10 - numRealPlayers) {
                        System.out.println("Number to big.");
                    } else if (numRealPlayers == 1 && numBots == 0) {
                        System.out.println("Need more players.");
                    }

                } catch (NumberFormatException nfe) {
                    System.out.println("Not a valid number.");
                }
            } while (numBots < 0 || numBots > 10 - numRealPlayers || (numBots == 0 && numRealPlayers == 1));
        }



            System.out.println("Enter Uno for regular rules and House for our house rules ");
            String gameMode = userInput.nextLine();

            // while loop to only get Uno or House as valid inputs from user
            while(!gameMode.equalsIgnoreCase("Uno")&&!gameMode.equalsIgnoreCase("House")){
                System.out.println("Try again ");
                gameMode = userInput.nextLine();
            }

            Game uno;

            // if else for creating uno game or house rules game
            if(gameMode.equalsIgnoreCase("Uno")) {
                uno = new Game(numRealPlayers + numBots, numRealPlayers);
            } else {
                uno = new HouseRules(numRealPlayers + numBots, numRealPlayers);
            }

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // Hides game setup

            // Start game
            uno.StartGame();

            // Play Game
            while (uno.hasWon() == -1) {

                if (!uno.isBotTurn()) {
                    System.out.printf("Player %d's turn: Enter 'next' to view your hand. ", uno.getCurrPlayer() + 1);
                    String input = userInput.nextLine();
                    while(!input.equalsIgnoreCase("next")) {
                        System.out.print("Try Again: ");
                        input= userInput.nextLine();
                    }
                }

                uno.playTurn();

                // Fix spacing so bot moves appear above next real player confirmation.
                // Finish commenting Game
            }

            System.out.printf("\n\nPlayer %d won!", uno.hasWon() + 1);


    }
}
