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
        if(game.getPlayerCount() <= 2){
            game.changeCurrPlayer(1 + game.getTurnDirection());
        }else {
            game.changeTurnDirection();
        }
    }
}
