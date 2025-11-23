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
        game.changeCurrPlayer(game.getTurnDirection()+1);
    }
}
