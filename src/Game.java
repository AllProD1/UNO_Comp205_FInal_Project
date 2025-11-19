import java.util.ArrayList;

public class Game {

    //
    private Deck gameDeck;
    //
    private Deck discardPile;

    //
    private int currPlayer;
    //
    private int turnIncrement;

    /***
     *
     * @param numToDraw
     * @param hand
     */
    public void dealCard(int numToDraw, ArrayList<Card> hand) {
        return;
    }

    /***
     *
     */
    public void discard() {
        return;
    }

    /***
     *
     */
    public void playTurn() {
        return;
    }

    /***
     *
     * @param card
     * @return
     */
    public boolean isValid(Card card) {
        return false;
    }

    /***
     *
     * @param hand
     * @return
     */
    public boolean canDrawFour(ArrayList<Card> hand) {
        return false;
    }

    public static void main(String[] args) {
        System.out.println("UNO");
    }
}
