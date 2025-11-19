public class Number extends Card{

    //
    private int number;

    /***
     *
     * @param other
     * @return
     */
    @Override
    public boolean softEquals(Card other) {
        return true;
    }
}
