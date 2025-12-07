/**
 * Class for all Skip Card Colors
 */

public class Skip extends Card {
    /**
     * Constructor for Skip card
     * @param color
     */
    public Skip(String color){
        super(color);
    }

    /***
     *
     * @return the id of the card used to determine if it is equal to another card.
     */
    @Override
    public String getValue() {
        return "Skip";
    }

    /**
     * play uses two to increment the turn skipping the following player
     * advancing to the new players turn
     * @return
     */
    public void play(Game game){
        // Change player by 2.
        game.changeCurrPlayer(2 * game.getTurnDirection());
    }

    /***
     * Creates a deep copy of the current skip card.
     * @return A new deep copy of the skip card.
     */
    public Card getCopy() {
        return new Skip(getColor());
    }
}
