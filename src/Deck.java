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
    private LinkedList<Card> deck;

    /**
     * added this to fill the deck im not sure we had a place to
     * implement this also i cannot look up javadoc on the plane so i did it
     * as a linked list for now i also know i could clean it up with strings they
     * are a bit repetitive but ill fix it later
     */
    public void deckBuilder(){
       LinkedList<Card> deck = new LinkedList<>();

       for(int i = 1; i <= 9; i++) {
           int twoOfEach = 1;
           while(twoOfEach <= 2) {
               // creates two number objects
               deck.add(new Number("Red", Integer.toString(i)));
               deck.add(new Number("Blue", Integer.toString(i)));
               deck.add(new Number("Yellow", Integer.toString(i)));
               deck.add(new Number("Green", Integer.toString(i)));

               // creates two skip objects
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

               twoOfEach++;
           }
           // creates wild cards
           int wildcounter = 1;
           while(wildcounter <= 4) {
               deck.add(new Wild("None"));
               deck.add(new Draw("None",4));
               wildcounter++;
           }
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

    }

    /***
     * The Draw method will return the top card from the shuffled deck
     * and remove it from the deck linked list and add it to the players hand
     * @return new Card element from the deck
     */
    public Card draw() {
        // should remove the card from the deck than return it
        Card draw = deck.getFirst();
        deck.removeFirst();
        return draw;
    }

    /**
     * used to find first card when checking if valid
     * @return first Card object from draw pile
     */
    public Card first(){
        return deck.getFirst();
    }
}
