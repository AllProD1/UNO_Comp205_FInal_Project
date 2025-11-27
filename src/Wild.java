public class Wild extends Card{

    /***
     * Creates a new Wild Card with color "None"
     */
    public Wild() {
        super("None");
    }

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
        super.setColor(game.getUserColor());
        game.changeCurrPlayer(game.getTurnDirection());
        // Use Game.getUserColor to change the color and move to next turn
    }

    public Card getCopy() {
        return new Wild();
    }
}
