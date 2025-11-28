import java.util.*;

/**
 * Game
 * This class allows the game to be played it creates the Hands and Deck
 * for the game and the players. It also keeps track of whose turn it is
 * and increments turns played.
 */
public class Game {

    // is an object reference to the linked list for the cards in the drawing or dealing deck
    private Deck gameDeck;
    // is an object to reference the linked list for the card objects being discarded
    private Stack<Card> discardPile;

    // All player's hands.
    private LinkedList<Card>[] hands;

    // int to keep track of which players turn it is
    private int currPlayer;

    // The direction the game moves. Only 1 or -1.
    private int turnDirection = 1;

    // Scanner for getting userinput in the game.
    Scanner userInput = new Scanner(System.in);

    public Game(int numPlayers, int numRealPlayers) {

        // Creates array of hands numPlayers long.
        hands = new LinkedList[numPlayers];

        // Instantiates the discard pile.
        discardPile = new Stack<>();

        // Instantiates each hand in hands as an empty ArrayList<Card>.
        for (int i = 0; i < hands.length; i++) {
            hands[i] = new LinkedList<>();
        }

        gameDeck = new Deck();
    }

    // Returns turnDirection
    public int getTurnDirection() {
        return turnDirection;
    }

    // Flips the direction of play.
    public void changeTurnDirection() {
        this.turnDirection *= -1;
    }

    /***
     * Sets the current player to whoever is amount away.
     * @param amount
     */
    public void changeCurrPlayer(int amount) {
        currPlayer = ((currPlayer + amount) % hands.length + hands.length) % hands.length;
    }

    public int getCurrPlayer() {
        return currPlayer;
    }

    public int getPlayerCount(){ return hands.length; }

    /**
     * Start Game creates the players and shuffles the deck. Then seven cards are
     * dealt into the players hands one after another. It also flips the top card
     * of the deck and checks to see if it is a valid start card for the game.
     */
    public void StartGame(){

        gameDeck.shuffle();

        for (LinkedList<Card> hand : hands) {
            dealCard(7, hand);
        }

        do {
            discardPile.add(gameDeck.draw());
        } while(!isValidStart(discardPile.peek()));

        currPlayer = 0;

        // User confirmation to start playing
    }

    /***
     * dealCard will take card elements and enter them into a players hand
     * depending on the required numToDraw reasoning to an action dealing
     * or drawing
     * @param numToDraw is defined by the reason for picking up cards
     *                  that will be seven at the start of the game
     *                  two or four depend on the draw card or
     *                  one card if you cannot play on a turn
     * @param hand is the arraylist of cards that a player has
     */
    public void dealCard(int numToDraw, LinkedList<Card> hand) {

        if (numToDraw > gameDeck.getDeckCount()) {

            int numRemainToDraw = numToDraw - gameDeck.getDeckCount();

            for(int i = 1; i <= gameDeck.getDeckCount();i++){
                hand.add(gameDeck.draw());
            }

            Card lastCard = discardPile.pop();
            gameDeck = new Deck(discardPile);
            gameDeck.shuffle();

            discardPile.add(lastCard);

            for (int i = 0; i < numRemainToDraw; i++) {
                hand.add(gameDeck.draw());
            }
        } else {
            for (int i = 1; i <= numToDraw; i++) {
                hand.add(gameDeck.draw());
            }
        }
    }

    public String getUserColor() {
        // Sets up options for colors.
        ArrayList<String> OPTIONS = new ArrayList<String>() {
            {
                add("red");
                add("r");
                add("blue");
                add("b");
                add("yellow");
                add("y");
                add("green");
                add("g");
            }
        };

        String color; // added this

        // Gets user input until a proper color is entered.
        do {
            System.out.println("Enter a color (Red, Blue, Yellow, Green, R, B, Y, G): ");
            color = userInput.nextLine().toLowerCase();
        }
        while (!OPTIONS.contains(color));

        // Returns fully spelt out color.
        if (color.length() > 1) {
            return color;
        }
        return OPTIONS.get(OPTIONS.indexOf(color)-1);

    }

    /***
     * dealCard will take card elements and enter them into a players hand
     * depending on the required numToDraw reasoning to an action dealing
     * or drawing
     *
     * Deals to currPlayer
     *
     * @param numToDraw is defined by the reason for picking up cards
     *                  that will be seven at the start of the game
     *                  two or four depend on the draw card or
     *                  one card if you cannot play on a turn
     */
    public void dealCard(int numToDraw) {
        dealCard(numToDraw, hands[currPlayer]);
    }// we may not need this method

    /***
     * discard is the act of playing by a player removing the card from
     * their hand and adding it to the discard pile
     */
    public Card discard(int index) {

       Card cardToDiscard = hands[currPlayer].remove(index);
       discardPile.add(cardToDiscard);
       return cardToDiscard;
    }

    /***
     *The method will allow player x to either play a card out of their
     * hand or draw a card due to not being able to play a valid card
     * if the card draw is valid it plays adn advances to next players turn
     */
    public void playTurn() {
        System.out.printf("\nTop Card: %s\n", discardPile.peek());

        if (currPlayer == -1) {
            System.out.println("");
        }

        LinkedList<Card> currHand = hands[currPlayer];

        System.out.printf("\nPlayer %d's Hand:\n%s\n\n", currPlayer + 1, printHand(currHand));

        System.out.print("Enter the card you want to play or type draw: ");
        String requestedCard = userInput.nextLine().strip();

        int requestedCardIndex = checkHandForCard(requestedCard, currHand);

        while (!requestedCard.equalsIgnoreCase("draw") && (requestedCardIndex == -1 || !canBePlayed(currHand.get(requestedCardIndex), currHand))) {

            System.out.print("Not a valid card. Try again: ");
            requestedCard = userInput.nextLine();

            requestedCardIndex = checkHandForCard(requestedCard, currHand);
        }

        if (requestedCard.equalsIgnoreCase("draw")) {
            dealCard(1);
            changeCurrPlayer(getTurnDirection());
        } else {
            discard(requestedCardIndex).play(this);
        }

    }

    public String printHand(LinkedList<Card> hand) {
        StringBuilder sb = new StringBuilder();

        for (Card c : hand) {
            sb.append(c.toString().replace("None ", ""));
            sb.append("\n");
        }

        return sb.toString();
    }

    /***
     * Checks if the current card is a possible starting card. I.E. a number card.
     * @param card is the object in the players hand that is trying to be played
     * @return true or false if the card can be the starting card.
     */
    public boolean isValidStart(Card card) {
        return card instanceof Number;
    }

    /***
     * Checks the current card on the discard pile and returns true or false if they are compatible.
     * @param card
     * @return if the given card can be played on the discard pile.
     */
    public boolean canBePlayed(Card card, LinkedList<Card> hand) { // Can you play +4 with a wild in your hand?

        // Checks if +4 card is the only viable option.
        if (card.getValue().contains("+4")) {
            if (canDrawFour(hand)) {
                return true;
            }
            return false;
        }

        // Checks for Wild cards
        if (card.getValue().contains("Wild")) {
            return true;
        }

        // Handles all other cards.
        if (card.softEquals(discardPile.peek())) {
            return true;
        }

        return false;
    }

    /***
     * canDrawFour checks to make sure you have no other cards in your hand
     * that are valid plays if so a player can play the draw four
     * @param hand is the Arraylist of the players cards
     * @return true when you have no other valid plays and false when a card plays
     */
    public boolean canDrawFour(LinkedList<Card> hand) {
        for (Card c : hand) {
            // Checks if all non +4 cards match color to the last card discarded.
            if (!c.getValue().equals("+4") && c.getColor().equalsIgnoreCase(discardPile.peek().getColor())) {
                return false;
            }
        }
        return true;
    }

    /***
     * Finds if any the first matching Card in Hand with card.
     * @param card
     * @param hand
     * @return Returns the index of the Card in hand with to same toString as card. Return -1 otherwise.
     */
    public int checkHandForCard(String card, LinkedList<Card> hand) {
        for (int i = 0; i < hand.size(); i++) {

            String cardString = hand.get(i).toString();

            // Remove None Color.
            cardString = cardString.replace("None", "");

            // Remove Excess Spacing.
            cardString = cardString.strip();

            if (card.equalsIgnoreCase(cardString)) {
                return i;
            }
        }
        return -1;
    }

    public int hasWon() {
        for (int i = 0; i < hands.length; i++) {
            if (hands[i].size() == 0) {
                return i;
            }
        }

        return -1;
    }
}
