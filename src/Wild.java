public class Wild extends Card{

    /***
     * Creates a new Wild Card with color "None"
     */
    public Wild() {
        super();
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
        return;
    }
}
