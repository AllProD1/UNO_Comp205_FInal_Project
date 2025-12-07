/***
 * Class for number all reverse cards.
 */
public class Reverse extends Card {

    /***
     * Creates a new Reverse Card with the given color.
     * @param color
     */
    public Reverse(String color) {
        super(color);
    }

    /***
     *
     * @return the id of the card used to determine if it is equal to another card.
     */
    @Override
    public String getValue() {
        return "Reverse";
    }

    /***
     * Reverses the turn order and does nothing if there are 2 players.
     * @param game
     */
    public void play(Game game) {
        // Change turnDirection and change player.
        // If there are only 2 players do nothing.

        game.changeTurnDirection();

        if(game.getPlayerCount() > 2){
            game.changeCurrPlayer(game.getTurnDirection());
        }
    }

    /***
     * Creates a deep copy of the current reverse card.
     * @return A new deep copy of the reverse card.
     */
    public Card getCopy() {
        return new Reverse(getColor());
    }
}
