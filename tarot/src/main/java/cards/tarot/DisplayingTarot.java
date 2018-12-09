package cards.tarot;
import cards.consts.Suit;
import code.util.EnumList;


public final class DisplayingTarot {

    private boolean clockwise;
    private EnumList<Suit> suits=new EnumList<Suit>();
    private boolean decreasing=true;
    public DisplayingTarot() {
        for(Suit c:Suit.values()){
            if(c == Suit.UNDEFINED){
                continue;
            }
            suits.add(c);
        }
    }
    public DisplayingTarot(DisplayingTarot _displayingTarot) {
        clockwise = _displayingTarot.clockwise;
        suits = new EnumList<Suit>(_displayingTarot.suits);
        decreasing = _displayingTarot.decreasing;
    }
    public void validate() {
        EnumList<Suit> s_ = new EnumList<Suit>(Suit.values());
        s_.removeObj(Suit.UNDEFINED);
        if (!Suit.equalsSuits(suits, s_)) {
            suits.clear();
            for(Suit c:Suit.values()){
                if(c == Suit.UNDEFINED){
                    continue;
                }
                suits.add(c);
            }
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
