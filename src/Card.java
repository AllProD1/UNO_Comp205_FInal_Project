public class Card {

    //
    private String color;

    /***
     *
     * @return
     */
    public String getColor() {
        return color;
    }

    /***
     *
     * @param other
     * @return
     */
    public boolean softEquals(Card other) {
        return true;
    }
}
