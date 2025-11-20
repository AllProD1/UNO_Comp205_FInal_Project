import java.util.ArrayList;

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
    private Deck discardPile;

    // int to keep track of which players turn it is
    private int currPlayer;
    // int to count the turns of the round and used to increment direction
    // for reverse card and skip cards
    private int turnIncrement;


    /**
     * Start Game creates the players and shuffles the deck. Then seven cards are
     * dealt into the players hands one after another. It also flips the top card
     * of the deck and checks to see if it is a valid start card for the game.
     */
    public void StartGame(){}

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
    public void dealCard(int numToDraw, ArrayList<Card> hand) {
        return;
    }

    /***
     * discard is the act of playing by a player removing the card from
     * their hand and adding it to the discard pile
     */
    public void discard() {
        return;
    }

    /***
     *The method will allow player x to either play a card out of their
     * hand or draw a card due to not being able to play a valid card
     * if the card draw is valid it plays adn advances to next players turn
     */
    public void playTurn() {
        return;
    }

    /***
     * isValid checks the current card on the discard pile and returns true or false
     * if the card played is valid it plays on the discard pile and advances to the
     * next players turn if the card is not a valid play the player is prompted to
     * try to play another card from their hand or draw a card from the deck or draw pile
     * @param card is the object in the players hand that is trying to be played
     * @return true or false if the card can be played on the discard pile
     */
    public boolean isValid(Card card) {
        return false;
    }

    /***
     * canDrawFour checks to make sure you have no other cards in your hand
     * that are valid plays if so a player can play the draw four
     * @param hand is the Arraylist of the players cards
     * @return true when you have no other valid plays and false when a card plays
     */
    public boolean canDrawFour(ArrayList<Card> hand) {
        return false;
    }

    public static void main(String[] args) {
        System.out.println("UNO");
    }
}
