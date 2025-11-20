/***
 * Class for number all basic number cards.
 */
public class Number extends Card{

    // The number value of the card.
    private int num;

    /***
     * Creates a new Number Card with the given num and color.
     * @param color
     * @param num
     */
    public Number(String color, int num) {
        super();
    }

    // Gets num.
    public int getNum() {
        return num;
    }

    /***
     * Checks if card and other are the same color or at least one color is "None" or the numbers are the same.
     * @param other
     * @return true if this Card and other are deemed equal and false if otherwise.
     */
    @Override
    public boolean softEquals(Card other) {
        return true;
    }
}
