import java.util.LinkedList;

public class HouseRules extends Game {

    public HouseRules(int realPlayer, int botPlayer){
        super(realPlayer,botPlayer);
    }

    @Override
    public void userDraw() {
        // Draws 1 card to start in case the player's last card was playable, but they still wanted to draw.
        dealCard(1);

        while (!canBePlayed(getCurrHand().getLast())) { // added this while loop for drawing until playable needs testing
            dealCard(1);
        }
    }

    // needs to override stack draw two
}
