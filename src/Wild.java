public class Wild extends Card{

    /***
     * Creates a new Wild Card with color "None"
     */
    public Wild() {
        super();
    }

    /***
     *
     * @return the id of the card used to determine if it is equal to another card.
     */
    @Override
    public String getValue() {
        return "Wild";
    }

    /***
     * Changes its color and progresses the turns.
     * @param game
     */
    @Override
    public void play(Game game) {
        if (getColor().equalsIgnoreCase("None")) {
            super.setColor(game.getUserColor());
        }
        game.changeCurrPlayer(game.getTurnDirection());
        // Use Game.getUserColor to change the color and move to next turn
    }

    /***
     * Creates a new wild card.
     * @return A new wild card.
     */
    public Card getCopy() {
        return new Wild();
    }
}
