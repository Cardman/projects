package cards.president;
import cards.consts.Suit;
import code.util.EnumList;
import code.util.annot.RwXml;

@RwXml
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
    public boolean getHoraire() {
        return clockwise;
    }
    public void setHoraire(boolean _horaire) {
        clockwise = _horaire;
    }
    public EnumList<Suit> getCouleurs() {
        return suits;
    }
    public void setCouleurs(EnumList<Suit> _couleurs) {
        suits = _couleurs;
    }
    public boolean getDecroissant() {
        return decreasing;
    }
    public void setDecroissant(boolean _decroissant) {
        decreasing = _decroissant;
    }
    public int getNbDeals() {
        return nbDeals;
    }
    public void setNbDeals(int _nbDeals) {
        nbDeals = _nbDeals;
    }
}
