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

    // Gets num.
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

    public Card getCopy() {
        return new Number(getColor(), num);
    }
}
