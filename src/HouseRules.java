import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class HouseRules extends Game {

    public HouseRules(int realPlayer, int botPlayer){
        super(realPlayer,botPlayer);
    }

    @Override
    public void userDraw() {
        // Draws 1 card to start in case the player's last card was playable, but they still wanted to draw.
        dealCard(1);

        while (!canBePlayed(getCurrHand().getLast())) { // added this while loop for drawing until playable needs testing
            dealCard(1);
        }
    }

    public void swapHands() {

        int newHand = -1;

        // Gets a valid hand
        do {
            System.out.printf("Enter the player you want to trade hands with? 1-%d\n", getPlayerCount());

            try  {
                newHand = Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.printf("Please enter valid integer from 1-%d.\n", getPlayerCount());
            }
        } while (newHand <= 0 || newHand > getPlayerCount() || newHand == getCurrPlayer()-1);

        newHand--;

        // currentHand hand becomes newHand and newHand becomes currentHand
        LinkedList<Card>[] allHands = getHands();
        LinkedList<Card> temp = allHands[getCurrPlayer()];
        allHands[getCurrPlayer()] = allHands[newHand];
        allHands[newHand] = temp;
    }

// this is for trading hands
    @Override
    public void playerTurn() {

        // Prints the top card of the discard pile.
        System.out.printf("\nTop Card: %s\n", getDiscardPile().peek());

        // Print Players hand.
        System.out.printf("\nPlayer %d's Hand:\n%s\n\n", getCurrPlayer() + 1, printHand());

        // Get User Input
        String turnInput = getUserTurnInput();

        // Handle User Input
        if (turnInput.equalsIgnoreCase("draw")) {

            userDraw();

            // If drawn card can be played, play it.
            if(canBePlayed(getCurrHand().getLast())){
                System.out.printf("Drew playable card. Now playing %s.\n", getCurrHand().getLast());

                if (getCurrHand().getLast().getValue().equalsIgnoreCase("0")) {
                    swapHands();
                }

                userPlay(getCurrHand().size()-1);

                // Have player confirm they played the drawn card.
                System.out.println("Enter 'next' to confirm.");
                String input = userInput.nextLine();
                while(!input.equalsIgnoreCase("next")) {
                    System.out.print("Try Again: ");
                    input= userInput.nextLine();
                }
            } else {
                changeCurrPlayer(getTurnDirection());
            }

        } else {

            int cardIndex = checkHandForCard(turnInput);
            if (getCurrHand().get(cardIndex).getValue().equalsIgnoreCase("0")) {
                swapHands();
            }
            userPlay(cardIndex);
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // Hides player hand
    }

    @Override
    public void botHandleWild(Card c) {
        if (c.getValue().equalsIgnoreCase("+4") || c.getValue().equalsIgnoreCase("Wild")) {
            c.setColor(getMostColor());
        }else if (c.getValue().equalsIgnoreCase("0")){
        System.out.println("0 Was played");

            Random randomHand = new Random(getPlayerCount());
            int botRandom = randomHand.nextInt();

            while(botRandom < 0 || botRandom == getCurrPlayer() ) {
                botRandom = randomHand.nextInt();
            }

            LinkedList<Card>[] allHands = getHands();
            System.out.println(allHands);
            LinkedList<Card> temp = allHands[getCurrPlayer()];
            System.out.println(temp);
            allHands[getCurrPlayer()] = allHands[botRandom];
            System.out.println(allHands[getCurrPlayer()]);
            allHands[botRandom] = temp;
            System.out.println(allHands[botRandom]);

        }
    }
}
