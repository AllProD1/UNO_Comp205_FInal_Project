/***
 * Class for all draw cards such draw 2 and draw 4.
 */
public class Draw extends Card{

    // The number of cards other players will draw when this card is played.
    private int drawAmount;

    /***
     * Creates a new Draw Card with color "None" and given drawAmount.
     * @param drawAmount
     */
    public Draw(int drawAmount) {
        super();
        this.drawAmount = drawAmount;
    }

    /***
     * Creates a new Draw Card with the given color and drawAmount.
     * @param color
     * @param drawAmount
     */
    public Draw(String color, int drawAmount) {
        super(color);
        this.drawAmount = drawAmount;
    }

    /***
     *
     * @return the id of the card used to determine if it is equal to another card.
     */
    @Override
    public String getValue() {
        // Returns '+drawAmount' i.e. '+2', '+4'
        return String.format("+%d",drawAmount);
    }

    /***
     * Makes the next player draw drawAmount cards and skips their turn.
     * @param game
     */
    public void play(Game game) {

        // If Draw 4 Card is played set it to a color.
        if (drawAmount == 4 && getColor().equalsIgnoreCase("None")) {
            super.setColor(game.getUserColor());
        }

        // Switch to next player.
        game.changeCurrPlayer(game.getTurnDirection());
        // Deal next player drawAmount cards.
        game.dealCard(drawAmount);
        // Skip next players turn.
        game.changeCurrPlayer(game.getTurnDirection());

    }

    // Makes +4 Cards Appear as "Wild +4".
    @Override public String toString() {
        if (drawAmount == 4 && getColor().equalsIgnoreCase("None")) {
            return "Wild " + super.toString();
        }
        return super.toString();

    }

    /***
     * Returns a deep copy of the draw card and removes the color if it is a +4
     * @return A new deep copy of the draw card.
     */
    public Card getCopy() {
        if (drawAmount == 4) { // Removes Color of draw four cards copied.
            return new  Draw(drawAmount);
        }

        return new Draw(getColor(), drawAmount);
    }
}
