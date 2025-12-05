import java.util.LinkedList;

public class HouseRules extends Game {

    public HouseRules(int realPlayer, int botPlayer){
        super(realPlayer,botPlayer);
    }

    // needs to override pick until you play for a turn
    @Override
    public void playTurn() {

        if (getCurrPlayer() >= getBotStart()) {
            botMove();
        } else {

            System.out.printf("\nTop Card: %s\n", getDiscardPile());

            if (getCurrPlayer() == -1) {
                System.out.println("");
            }

            LinkedList<Card> currHand = getHands()[getCurrPlayer()];

            System.out.printf("\nPlayer %d's Hand:\n%s\n\n", getCurrPlayer() + 1, printHand(currHand));

            System.out.print("Enter the card you want to play by typing color and number or type draw: ");
            String requestedCard = userInput.nextLine().strip();

            int requestedCardIndex = checkHandForCard(requestedCard, currHand);

            while (!requestedCard.equalsIgnoreCase("draw") && (requestedCardIndex == -1 || !canBePlayed(currHand.get(requestedCardIndex), currHand))) {

                System.out.print("Not a valid card. Try again: ");
                requestedCard = userInput.nextLine();

                requestedCardIndex = checkHandForCard(requestedCard, currHand);
            }

            // made changes here in looping the draw phase if the card cannot play
            if (requestedCard.equalsIgnoreCase("draw")) {
                dealCard(1);
                if(canBePlayed(currHand.getLast(),currHand)){
                    discard(currHand.size()-1).play(this);
                }else {
                    while (!canBePlayed(currHand.getLast(), currHand)) { // added this while loop for drawing until playable needs testing
                        dealCard(1);
                        if (canBePlayed(currHand.getLast(), currHand)) {
                            discard(currHand.size() - 1).play(this);
                            break;
                        }
                        changeCurrPlayer(getTurnDirection());
                    }
                }
            } else {
                discard(requestedCardIndex).play(this);
            }
        }

    }





    // needs to override stack draw two
}
