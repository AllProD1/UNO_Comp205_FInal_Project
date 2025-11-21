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

    // Gets drawAmount.
    @Override
    public String getValue() {
        return String.valueOf(drawAmount);
    }

    /***
     * Makes the next player draw drawAmount cards and skips their turn.
     * @param game
     */
    public void play(Game game) {

        // If Draw 4 Card is played set it to a color.
        if (drawAmount == 4) {
            super.setColor(game.getUserColor());
        }

        // Switch to next player.
        game.changeCurrPlayer(1 * game.getTurnDirection());
        // Deal next player drawAmount cards.
        game.dealCard(drawAmount);
        // Skip next players turn.
        game.changeCurrPlayer(1 * game.getTurnDirection());

    }
}
