package cards.tarot;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.Numbers;
import cards.consts.Suit;

final class TarotInfoPliEnCours {

    private HandTarot cartesJouables;
    private HandTarot cartesAJouer;
    private Numbers<Byte> joueursNonJoue;
    private Numbers<Byte> joueursJoue;
    private CustList<TrickTarot> plisFaits;
    private HandTarot cartesJouees;
    private EnumMap<Suit,HandTarot> repartitionCartesJouees;
    private boolean carteAppeleeJouee;
    private boolean contientExcuse;
    private EnumMap<Suit,EqList<HandTarot>> cartesPossibles;
    private EnumMap<Suit,EqList<HandTarot>> cartesCertaines;
    private byte ramasseurVirtuel;
    private EnumMap<Suit,EqList<HandTarot>> suitesTouteCouleur;
    private boolean maitreAtout;
    private EnumList<Suit> couleursMaitresses;
    private EnumMap<Suit,HandTarot> cartesMaitresses;
    private boolean maitreJeu;
    private EnumList<Suit> coupesFranches;

    TarotInfoPliEnCours() {
    }

    public HandTarot getCartesJouables() {
        return cartesJouables;
    }

    public void setCartesJouables(HandTarot _cartesJouables) {
        cartesJouables = _cartesJouables;
    }

    public HandTarot getCartesAJouer() {
        return cartesAJouer;
    }

    public void setCartesAJouer(HandTarot _cartesAJouer) {
        cartesAJouer = _cartesAJouer;
    }

    public Numbers<Byte> getJoueursNonJoue() {
        return joueursNonJoue;
    }

    public void setJoueursNonJoue(Numbers<Byte> _joueursNonJoue) {
        joueursNonJoue = _joueursNonJoue;
    }

    public Numbers<Byte> getJoueursJoue() {
        return joueursJoue;
    }

    public void setJoueursJoue(Numbers<Byte> _joueursJoue) {
        joueursJoue = _joueursJoue;
    }

    public CustList<TrickTarot> getPlisFaits() {
        return plisFaits;
    }

    public void setPlisFaits(CustList<TrickTarot> _plisFaits) {
        plisFaits = _plisFaits;
    }

    public HandTarot getCartesJouees() {
        return cartesJouees;
    }

    public void setCartesJouees(HandTarot _cartesJouees) {
        cartesJouees = _cartesJouees;
    }

    public EnumMap<Suit,HandTarot> getRepartitionCartesJouees() {
        return repartitionCartesJouees;
    }

    public void setRepartitionCartesJouees(EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        repartitionCartesJouees = _repartitionCartesJouees;
    }

    public boolean isCarteAppeleeJouee() {
        return carteAppeleeJouee;
    }

    public void setCarteAppeleeJouee(boolean _carteAppeleeJouee) {
        carteAppeleeJouee = _carteAppeleeJouee;
    }

    public boolean isContientExcuse() {
        return contientExcuse;
    }

    public void setContientExcuse(boolean _contientExcuse) {
        contientExcuse = _contientExcuse;
    }

    public EnumMap<Suit,EqList<HandTarot>> getCartesPossibles() {
        return cartesPossibles;
    }

    public void setCartesPossibles(EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        cartesPossibles = _cartesPossibles;
    }

    public EnumMap<Suit,EqList<HandTarot>> getCartesCertaines() {
        return cartesCertaines;
    }

    public void setCartesCertaines(EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        cartesCertaines = _cartesCertaines;
    }

    public byte getRamasseurVirtuel() {
        return ramasseurVirtuel;
    }

    public void setRamasseurVirtuel(byte _ramasseurVirtuel) {
        ramasseurVirtuel = _ramasseurVirtuel;
    }

    public EnumMap<Suit,EqList<HandTarot>> getSuitesTouteCouleur() {
        return suitesTouteCouleur;
    }

    public void setSuitesTouteCouleur(EnumMap<Suit,EqList<HandTarot>> _suitesTouteCouleur) {
        suitesTouteCouleur = _suitesTouteCouleur;
    }

    public boolean isMaitreAtout() {
        return maitreAtout;
    }

    public void setMaitreAtout(boolean _maitreAtout) {
        maitreAtout = _maitreAtout;
    }

    public EnumList<Suit> getCouleursMaitresses() {
        return couleursMaitresses;
    }

    public void setCouleursMaitresses(EnumList<Suit> _couleursMaitresses) {
        couleursMaitresses = _couleursMaitresses;
    }

    public EnumMap<Suit,HandTarot> getCartesMaitresses() {
        return cartesMaitresses;
    }

    public void setCartesMaitresses(EnumMap<Suit,HandTarot> _cartesMaitresses) {
        cartesMaitresses = _cartesMaitresses;
    }

    public boolean isMaitreJeu() {
        return maitreJeu;
    }

    public void setMaitreJeu(boolean _maitreJeu) {
        maitreJeu = _maitreJeu;
    }

    public EnumList<Suit> getCoupesFranches() {
        return coupesFranches;
    }

    public void setCoupesFranches(EnumList<Suit> _coupesFranches) {
        coupesFranches = _coupesFranches;
    }

}
