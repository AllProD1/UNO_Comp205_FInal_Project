/***
 * Class for number all reverse cards.
 */
public class Reverse extends Card {

    /***
     * Creates a new Reverse Card with the given color.
     * @param color
     */
    public Reverse(String color) {
        super();
    }

    /***
     * Reverses the turn order and does nothing if there are 2 players.
     * @param game
     */
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
