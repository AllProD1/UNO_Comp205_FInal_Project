import java.util.ArrayList;
import java.util.Collection;
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
    private Queue<Card> deck = new LinkedList<>();

    /**
     * added this to fill the deck im not sure we had a place to
     * implement this also i cannot look up javadoc on the plane so i did it
     * as a linked list for now i also know i could clean it up with strings they
     * are a bit repetitive but ill fix it later
     */
    public Deck(){

        for (int i = 0; i < 2; i++) {

            // Loops through 0-9 the first time.
            // On the second loop loops 0-8 but adds 1 to each number making it 1-9.
            for (int j = 0; j < 10 - i; j++) {
                deck.add(new Number("Red", Integer.toString(j + i)));
                deck.add(new Number("Blue", Integer.toString(j + i)));
                deck.add(new Number("Yellow", Integer.toString(j + i)));
                deck.add(new Number("Green", Integer.toString(j + i)));
            }

            // creates two skip cards
            deck.add(new Skip("Red"));
            deck.add(new Skip("Blue"));
            deck.add(new Skip("Yellow"));
            deck.add(new Skip("Green"));

            // creates two reverse cards
            deck.add(new Reverse("Red"));
            deck.add(new Reverse("Blue"));
            deck.add(new Reverse("Yellow"));
            deck.add(new Reverse("Green"));

            // creates two draw two cards
            deck.add(new Draw("Red",2));
            deck.add(new Draw("Blue",2));
            deck.add(new Draw("Yellow",2));
            deck.add(new Draw("Green",2));

            for (int j = 0; j < 2; j++) {
                deck.add(new Wild());
                deck.add(new Draw(4));
            }
        }
    }

    /***
     * Creates a deck with the given cards and resets and user altered properties
     * @param cardsToAdd A collection of cards to be included in the deck.
     */
    public Deck(Collection<Card> cardsToAdd) {
        for (Card c : cardsToAdd) {
            deck.add(c.getCopy());
        }
    }

    /***
     *Shuffle method will take the card elements in the linked list
     * and randomize there current order to deal the cards to the
     * players at the beginning of the game
     * it also will take the discard pile and re shuffle it to make
     * the new deck
     */
    public void shuffle() {
        ArrayList<Card> listDeck = new ArrayList<>(deck);
        for (int i = 0; i < listDeck.size(); i++) {
            Card temp = listDeck.get(i);
            int random = (int) (Math.random() * listDeck.size());
            listDeck.set(i, listDeck.get(random));
            listDeck.set(random, temp);
        }

        deck = new LinkedList<>(listDeck);
    }

    /***
     * The Draw method will return the top card from the shuffled deck
     * and remove it from the deck linked list and add it to the players hand
     * @return new Card element from the deck
     */
    public Card draw() {
        // should remove the card from the deck than return it
        Card draw = deck.peek();
        deck.poll();
        return draw;
    }

    // Returns the number of cards in the deck.
    public int getDeckCount() { return deck.size(); }

    /**
     * used to find first card when checking if valid
     * @return first Card object from draw pile
     */
    public Card first(){
        return  deck.peek();
    }

    /***
     * Used for debugging
     * @return Each card of the deck printed on their own line.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Card card : deck) {
            sb.append(card.toString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
