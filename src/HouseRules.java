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

    public void playerSwap() {
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

        newHand --;

        swapHands(newHand);
    }

    public void swapHands(int toSwap) {

        int lastPlayer = ((getCurrPlayer() - getTurnDirection()) % getPlayerCount() + getPlayerCount()) % getPlayerCount();

        // currentHand hand becomes newHand and newHand becomes currentHand
        LinkedList<Card>[] allHands = getHands();
        LinkedList<Card> temp = allHands[lastPlayer];

        allHands[lastPlayer] = allHands[toSwap];
        allHands[toSwap] = temp;
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
                    userPlay(getCurrHand().size()-1);
                    playerSwap();
                } else {
                    userPlay(getCurrHand().size()-1);
                }

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
                userPlay(cardIndex);
                playerSwap();
            } else {
                userPlay(cardIndex);
            }
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // Hides player hand
    }


    /***
     * Controls the bots turn movement.
     */
    @Override
    public void botMove() {

        ListIterator<Card> it = getCurrHand().listIterator();

        // Iterates through bots hand
        while (it.hasNext()) {

            Card c = it.next();

            // If the bot can play the card play it.
            if (canBePlayed(c)) {

                botHandleWild(c);

                System.out.printf("Bot %d Played: %s\n", getCurrPlayer()+1, c);

                if (c.getValue().equalsIgnoreCase("0")) {

                    Random randomHand = new Random();
                    int botRandom = randomHand.nextInt(getPlayerCount());

                    while(botRandom < 0 || botRandom == getCurrPlayer() ) {
                        botRandom = randomHand.nextInt(getPlayerCount());
                    }

                    System.out.println("Swapped hands with player " + botRandom);

                    discard(it.previousIndex()).play(this);
                    swapHands(botRandom);
                } else {
                    discard(it.previousIndex()).play(this);
                }

                // Ends Turn
                System.out.println("");
                return;
            }
        }

        System.out.printf("Bot %d Draws a card.\n", getCurrPlayer()+1);
        userDraw();

        Card lastCard = getCurrHand().getLast();

        // If bot can play picked up card, it plays it.
        if (canBePlayed(lastCard)) {

            botHandleWild(lastCard);

            System.out.printf("Bot %d Plays: %s\n", getCurrPlayer()+1, lastCard);
            if (lastCard.getValue().equalsIgnoreCase("0")) {

                Random randomHand = new Random();
                int botRandom = randomHand.nextInt(getPlayerCount());

                while(botRandom < 0 || botRandom == getCurrPlayer() ) {
                    botRandom = randomHand.nextInt(getPlayerCount());
                }

                System.out.println("Swapped hands with player " + botRandom);

                discard(getCurrHand().size()-1).play(this);
                swapHands(botRandom);
            } else {
                discard(getCurrHand().size()-1).play(this);
            }
        } else {
            changeCurrPlayer(getTurnDirection());
        }

        System.out.println("");
    }
}


