import java.sql.SQLOutput;
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

    // used for reference in House Rules
    int numPlayers,numRealPlayers;

    /***
     * Creates a new game with numPlayers and initializes hand, discard pile, and deck.
     * @param numPlayers The total number of players both real and bot
     * @param numRealPlayers The total number of real players.
     */
    public Game(int numPlayers, int numRealPlayers) {
        this.numPlayers = numPlayers;
        this.numRealPlayers = numRealPlayers;

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

    // for house rules access
    public LinkedList<Card>[] getHands() {
        return hands;
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

    // Gets the current player
    public int getCurrPlayer() {
        return currPlayer;
    }

    // Gets the number of players both bots and real.
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

        // Makes sure the deck has enough cards to draw.
        if (numToDraw > gameDeck.getDeckCount()) {

            int numRemainToDraw = numToDraw - gameDeck.getDeckCount();

            // If deck does not contain enough cards to draw:
            // Draw all remaining cards
            for(int i = 1; i <= gameDeck.getDeckCount();i++){
                hand.add(gameDeck.draw());
            }

            // Remove the top of the discard pile
            Card lastCard = discardPile.pop();

            // Create a new deck with the remaining discard pile
            // If for some reason there are no cards left to be shuffled print error and make a new deck.
            // The above should only happen if every player just keeps drawing instead of playing any cards.
            if (!discardPile.isEmpty()) {
                gameDeck = new Deck(discardPile);
            } else {
                System.out.println("No cards were left to be shuffled: Creating new deck.");
                gameDeck = new Deck();
            }
            gameDeck.shuffle();

            // Add the removed card from the discard pile back onto the discard pile.
            discardPile.add(lastCard);

            // Finish drawing the necessary cards.
            for (int i = 0; i < numRemainToDraw; i++) {
                hand.add(gameDeck.draw());
            }

        } else {
            // If the deck contains enough cards to draw, draw that many cards.
            for (int i = 1; i <= numToDraw; i++) {
                hand.add(gameDeck.draw());
            }
        }
    }

    /***
     * Prompts the user to pick a color for wild and wild +4 cards.
     * Ensures the player enters a valid color and translates that color to the proper format.
     * @return A formatted string color to be added to a card.
     */
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

        // Gets fully spelled out color.
        if (color.length() == 1) {
            color = OPTIONS.get(OPTIONS.indexOf(color)-1);
        }

        // Capitalizes first letter
        color = color.substring(0,1).toUpperCase() + color.substring(1);
        return color;

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
    }

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

        if (isBotTurn()) {
            botMove();
        } else {
            playerTurn();
        }

    }

// added for House rules play turn override
    public Stack<Card> getDiscardPile() {
        return discardPile;
    }

    /***
     * Controls the flow of a real players turn.
     */
    public void playerTurn() {

        // Prints the top card of the discard pile.
        System.out.printf("\nTop Card: %s\n", discardPile.peek());

        // Print Players hand.
        System.out.printf("\nPlayer %d's Hand:\n%s\n\n", currPlayer + 1, printHand());

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

            userPlay(cardIndex);

        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // Hides player hand
    }

    // Deals a card to the current player.
    public void userDraw() {
        dealCard(1);
    }

    // Returns whether it is a bots turn.
    public boolean isBotTurn() {
        return currPlayer >= numRealPlayers;
    };

    // plays and discards the card at the index in current players hand.
    public void userPlay(int cardIndex) {
        discard(cardIndex).play(this);
    }

    /***
     * Prompts the user for play input until valid input is received.
     * @return the valid play instruction.
     */
    public String getUserTurnInput() {

        System.out.print("Enter the card you want to play by typing color and number or type draw: ");
        String requestedMove = userInput.nextLine().strip();

        // Get Valid User Input
        while (validPlayerInput(requestedMove)) {
            requestedMove = userInput.nextLine().strip();
        }

        return requestedMove;
    }

    /***
     * Checks if a player input is a valid option by either being a playable card from their hand or to draw.
     * Prints out corresponding messages for incorrect input.
     * @param userInput
     * @return true if userInput will result in a valid action.
     */
    public boolean validPlayerInput(String userInput) {

        // If the player doesn't draw check if the card is valid.
        if (!userInput.equalsIgnoreCase("draw")) {

            // Get Card index in hand or -1
            int requestedCardIndex = checkHandForCard(userInput);

            if (requestedCardIndex == -1) {
                System.out.println("Card not in hand. Try again: ");
                return true;
            } else {

                // Card must exist in the hand
                Card requestedCard = getCurrHand().get(requestedCardIndex);

                if (!canBePlayed(requestedCard)) {

                    System.out.println("Card cannot be played. Try again: ");
                    return true;
                }
            }
        }

        return false;
    }

    /***
     * Controls the bots turn movement.
     */
    public void botMove() {

        ListIterator<Card> it = getCurrHand().listIterator();

        // Iterates through bots hand
        while (it.hasNext()) {

            Card c = it.next();

            // If the bot can play the card play it.
            if (canBePlayed(c)) {

                botHandleWild(c);

                System.out.printf("Bot %d Played: %s\n", currPlayer+1, c);
                discard(it.previousIndex()).play(this);

                // Ends Turn
                System.out.println("");
                return;
            }
        }

        System.out.printf("Bot %d Draws a card.\n", currPlayer+1);
        dealCard(1);

        Card lastCard = getCurrHand().getLast();

        // If bot can play picked up card, it plays it.
        if (canBePlayed(lastCard)) {

            botHandleWild(lastCard);

            System.out.printf("Bot %d Plays: %s\n", currPlayer+1, lastCard);
            discard(getCurrHand().size()-1).play(this);
        } else {
            changeCurrPlayer(getTurnDirection());
        }

        System.out.println("");
    }

    // Returns a reference to the LinkedList<Card> of the current player.
    public LinkedList<Card> getCurrHand() {
        return hands[currPlayer];
    }

    /***
     * Checks if the card being played by the bot is a wild or +4 and deals with the color change.
     * @param c The card being played
     */
    public void botHandleWild(Card c) {
        if (c.getValue().equalsIgnoreCase("+4") || c.getValue().equalsIgnoreCase("Wild")) {
            c.setColor(getMostColor());
        }
    }

    /***
     * Used to make bots slightly more intelligent by changing getting color most common in their hand.
     * @return the color the bot contains the most of.
     */
    public String getMostColor() {
        HashMap<String, Integer> colors = new HashMap<>();

        // Counts number of cards of each color in hand.
        for (Card c : getCurrHand()) {

            String cardColor = c.getColor();

            // Ensures card has a usable color.
            if (!cardColor.equalsIgnoreCase("None")) {
                int count = colors.getOrDefault(colors.get(cardColor), 0);
                colors.put(cardColor, count + 1);
            }
        }

        // If the bot contains no colored cards add 1 red card.
        if (colors.isEmpty()) {
            colors.put("Red", 1);
        }

        TreeMap<Integer, String> orderedColors = new TreeMap<>();

        // Sorts cards by number rather can color
        for (String color: colors.keySet()) {
            int count = colors.get(color);
            orderedColors.put(count, color);
        }

        // Gets the number of the most used color
        int firstOrderedKey = orderedColors.descendingKeySet().first();

        // Returns most used color.
        return orderedColors.get(firstOrderedKey);
    }

    /***
     * Creates a string that contains a formatted list of cards to display to the player.
     * @return a string with each card in the hand on its own line.
     */
    public String printHand() {
        StringBuilder sb = new StringBuilder();

        for (Card c : getCurrHand()) {
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
    public boolean canBePlayed(Card card) {

        // Checks if +4 card is the only viable option.
        if (card.getValue().contains("+4")) {
            if (canDrawFour(getCurrHand())) {
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
     * @return Returns the index of the Card in hand with to same toString as card. Return -1 otherwise.
     */
    public int checkHandForCard(String card) {
        for (int i = 0; i < getCurrHand().size(); i++) {

            String cardString = getCurrHand().get(i).toString();

            // Remove None Color.
            cardString = cardString.replace("None ", "");

            // Remove Excess Spacing.
            cardString = cardString.strip();

            if (card.equalsIgnoreCase(cardString)) {
                return i;
            }
        }
        return -1;
    }

    /***
     * Checks if any player has no cards remaining in their hand.
     * @return the player num of the winner or -1 if no winner exists yet.
     */
    public int hasWon() {
        for (int i = 0; i < hands.length; i++) {
            if (hands[i].isEmpty()) {
                return i;
            }
        }

        return -1;
    }
}
