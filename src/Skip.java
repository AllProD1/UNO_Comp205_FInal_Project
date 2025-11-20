/**
 * Class for all Skip Card Colors
 */

public class Skip extends Card {
    /**
     * Constructor for Skip card
     * @param color
     */
    public Skip(String color){
        super();
    }

    /**
     * play uses two to increment the turn skipping the following player
     * advancing to the new players turn
     * @return
     */
    public int play(){
        return 2;
    }

    /**
     * Checks if card and other are the same color or at least one color is "None" or the numbers are the same.
     * @param other
     * @return true if this Card and other are deemed equal and false if otherwise.
     */
    @Override
    public boolean softEquals(Card other) {
        return true;
    }
}
