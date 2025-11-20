/***
 * Class for utility cards that don't cause draws such as skip, reverse, and wilds.
 */
public class Utility extends Card{

    // The name of the utility card. (i.e. Reverse, Skip, Wild).
    private String name;

    /***
     * Creates a new Utility Card with color "None" and the given name.
     * @param name
     */
    public Utility(String name) {
        super();
    }

    /***
     * Creates a new Utility Card with the given color and name.
     * @param color
     * @param name
     */
    public Utility(String color, String name) {
        super();
    }

    // Returns name.
    public String getName() {
        return "";
    }

    /***
     * Checks if card and other are the same color or at least one color is "None" or the name is the same.
     * @param other
     * @return true if this Card and other are deemed equal and false if otherwise.
     */
    @Override
    public boolean softEquals(Card other) {
        return true;
    }
}
