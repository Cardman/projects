package cards.president;
import cards.consts.Suit;
import code.util.EnumList;


public final class DisplayingPresident {

    private boolean clockwise;
    private EnumList<Suit> suits=new EnumList<Suit>();
    private boolean decreasing=true;
    private int nbDeals;
    public DisplayingPresident() {
        for(Suit c:Suit.couleursOrdinaires()){
            suits.add(c);
        }
        nbDeals = 1;
    }
    public DisplayingPresident(DisplayingPresident _displayingTarot) {
        clockwise = _displayingTarot.clockwise;
        suits = new EnumList<Suit>(_displayingTarot.suits);
        decreasing = _displayingTarot.decreasing;
        nbDeals = _displayingTarot.nbDeals;
    }
    public void validate() {
        EnumList<Suit> s_ = new EnumList<Suit>(Suit.couleursOrdinaires());
        if (!Suit.equalsSuits(suits, s_)) {
            suits.clear();
            for(Suit c:Suit.couleursOrdinaires()){
                suits.add(c);
            }
        }
        if (nbDeals <= 0) {
            nbDeals = 1;
        }
    }
    public int getNbDeals() {
        return nbDeals;
    }
    public void setNbDeals(int _nbDeals) {
        nbDeals = _nbDeals;
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
