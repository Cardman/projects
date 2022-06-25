package cards.tarot;
import cards.consts.Suit;
import code.util.EnumList;


public final class DisplayingTarot {

    private boolean clockwise;
    private EnumList<Suit> suits;
    private boolean decreasing=true;
    public DisplayingTarot() {
        suits = Suit.couleursDefinies();
    }
    public DisplayingTarot(DisplayingTarot _displayingTarot) {
        clockwise = _displayingTarot.clockwise;
        suits = new EnumList<Suit>(_displayingTarot.suits);
        decreasing = _displayingTarot.decreasing;
    }
    public void validate() {
        EnumList<Suit> s_ = new EnumList<Suit>(Suit.couleursDefinies());
        if (!Suit.equalsSuits(suits, s_)) {
            suits.clear();
            for(Suit c:Suit.couleursDefinies()){
                suits.add(c);
            }
        }
    }
    public boolean isClockwise() {
        return clockwise;
    }
    public void setClockwise(boolean _clockwise) {
        clockwise = _clockwise;
    }
    public EnumList<Suit> getSuits() {
        return suits;
    }
    public void setSuits(EnumList<Suit> _suits) {
        suits = _suits;
    }
    public boolean isDecreasing() {
        return decreasing;
    }
    public void setDecreasing(boolean _decreasing) {
        decreasing = _decreasing;
    }
}
