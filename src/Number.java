/***
 * Class for number all basic number cards.
 */
public class Number extends Card{

    // The number value of the card.
    private String num;

    /***
     * Creates a new Number Card with the given num and color.
     * @param color
     * @param num
     */
    public Number(String color, String num) {
        super(color);
        this.num = num;
    }

    /***
     *
     * @return the id of the card used to determine if it is equal to another card.
     */
    @Override
    public String getValue() {
        return num;
    }

    /***
     * Moves to next players turn.
     * @param game
     */
    public void play(Game game) {
        game.changeCurrPlayer(game.getTurnDirection());
    }

    /***
     * Creates a deep copy of the current number card.
     * @return A new deep copy of the number card.
     */
    public Card getCopy() {
        return new Number(getColor(), num);
    }
}
