/***
 * Abstract Parent class for all cards that holds color and basic comparing methods.
 */
public abstract class Card {

    // The color of the card. "None" if the card is colorless.
    private String color;

    /***
     * Creates a new Card with this.color set to "None".
     */
    public Card() {
    }

    /***
     * Creates a new Card with this.color set to color.
     * @param color
     */
    public Card(String color) {
    }

    // Gets Color.
    public String getColor() {
        return "";
    }

    // Sets this.Color to newColor.
    public void setColor(String newColor) {

    }

    /***
     * Checks if card and other are the same color or at least one color is "None".
     * @param other
     * @return true if this Card and other are deemed equal and false if otherwise.
     */
    public boolean softEquals(Card other) {
        return true;
    }
}
