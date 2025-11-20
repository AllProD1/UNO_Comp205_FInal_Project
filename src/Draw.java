public class Draw extends Card{

    // The number of cards other players will draw when this card is played.
    private int drawAmount;

    /***
     * Creates a new Draw Card with color "None" and given drawAmount.
     * @param drawAmount
     */
    public Draw(int drawAmount) {
        super();
    }

    /***
     * Creates a new Draw Card with the given color and drawAmount.
     * @param color
     * @param drawAmount
     */
    public Draw(String color, int drawAmount) {
        super();
    }

    // Gets drawAmount.
    public int getDrawAmount() {
        return 0;
    }

    /***
     * Checks if card and other are the same color or at least one color is "None" or the drawAmount is the same.
     * @param other
     * @return true if this Card and other are deemed equal and false if otherwise.
     */
    @Override
    public boolean softEquals(Card other) {
        return true;
    }
}
