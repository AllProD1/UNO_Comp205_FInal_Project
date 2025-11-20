import java.util.LinkedList;
import java.util.Queue;

/***
 *Deck
 * This takes the cards and shuffles them randomly into a LinkedList.
 * This list then is used when players draw from it thus removing card element.
 * This also stores and holds the deck here as a linked list.
 */
public class Deck {

    /***
     * The linked list will hold the card objects of the deck
     * this functions as the deck of cards for the game it will be shuffled
     * and it can be drawn from by players
     * LinkedList was chosen for its speed when shuffling
     */
    // Linked List
    private Queue<Card> deck;

    /***
     *Shuffle method will take the card elements in the linked list
     * and randomize there current order to deal the cards to the
     * players at the beginning of the game
     * it also will take the discard pile and re shuffle it to make
     * the new deck
     */
    public void shuffle() {}

    /***
     * The Draw method will return the top card from the shuffled deck
     * and remove it from the deck linked list and add it to the players hand
     * @return new Card element from the deck
     */
    public Card draw() {
        return new Number("",0);
    }

}
