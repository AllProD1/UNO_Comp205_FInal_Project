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
            if(turnInput.equalsIgnoreCase("Red 0")|| turnInput.equalsIgnoreCase("Blue 0")||
                    turnInput.equalsIgnoreCase("Green 0")|| turnInput.equalsIgnoreCase("Yellow 0")){

                    System.out.println("Enter the player you want to trade hands with by typing number i.e. '1' ?");
                    int newHand = userInput.nextInt()-1;
                    while(newHand < 0 || newHand > getPlayerCount() || newHand == getCurrPlayer() ) {
                        System.out.println("Invalid Player Number Try Again");
                        newHand = userInput.nextInt()-1;
                    }
                // currentHand hand becomes newHand and newHand becomes currentHand
                        LinkedList<Card>[] allHands = getHands();
                        LinkedList<Card> temp = allHands[getCurrPlayer()];
                        allHands[getCurrPlayer()] = allHands[newHand];
                        allHands[newHand] = temp;

                    userPlay(cardIndex);

            }else {
                userPlay(cardIndex);
            }
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
