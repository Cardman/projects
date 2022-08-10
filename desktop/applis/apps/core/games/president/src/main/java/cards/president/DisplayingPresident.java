package cards.president;
import cards.consts.DisplayingCommon;
import cards.consts.Suit;


public final class DisplayingPresident {

    private final DisplayingCommon displaying;
    private int nbDeals;
    public DisplayingPresident() {
        displaying = new DisplayingCommon();
        displaying.setSuits(Suit.couleursOrdinaires());
        nbDeals = 1;
    }
    public DisplayingPresident(DisplayingPresident _displayingTarot) {
        displaying = new DisplayingCommon(_displayingTarot.displaying);
        nbDeals = _displayingTarot.nbDeals;
    }
    public void validate() {
        displaying.validate(Suit.couleursOrdinaires());
        if (nbDeals <= 0) {
            nbDeals = 1;
        }
    }

    public DisplayingCommon getDisplaying() {
        return displaying;
    }

    public int getNbDeals() {
        return nbDeals;
    }
    public void setNbDeals(int _nbDeals) {
        nbDeals = _nbDeals;
    }

}
