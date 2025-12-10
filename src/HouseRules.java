import java.util.LinkedList;

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

    // needs to override stack draw two

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
                userPlay(getCurrHand().size()-1);
            } else {
                changeCurrPlayer(getTurnDirection());
            }

        } else {

            int cardIndex = checkHandForCard(turnInput);
            if(turnInput.equalsIgnoreCase("Red 0")|| turnInput.equalsIgnoreCase("Blue 0")||
                    turnInput.equalsIgnoreCase("Green 0")|| turnInput.equalsIgnoreCase("Yellow 0")){

                    System.out.println("Enter the player you want to trade hands with?");
                    int newHand = userInput.nextInt();
                    while(newHand < 0 && newHand > getPlayerCount()){
                        System.out.println("Invalid Player Number Try Again");
                        newHand = userInput.nextInt();
                        if(newHand > 0 && newHand < getPlayerCount()){
                            break;
                        }
                    }
                    // currentHand hand becomes newHand and newHand becomes currentHand


                    userPlay(cardIndex);

            }else {
                userPlay(cardIndex);
            }
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // Hides player hand
    }
}
