public class Wild extends Card{

    /***
     * Creates a new Wild Card with color "None"
     */
    public Wild() {
        super();
    }

    /***
     * Changes its color and progresses the turns.
     * @param game
     */
    @Override
    public void play(Game game) {
        return;
    }

    /***
     * Checks if card and other are the same color or at least one color is "None" or other is a member of Reverse.
     * @param other
     * @return true if this Card and other are deemed equal and false if otherwise.
     */
    @Override
    public boolean softEquals(Card other) {
        return true;
    }

}
