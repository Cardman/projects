package cards.belote;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.*;

final class BeloteInfoPliEnCours {

    private HandBelote playableCards;
    private HandBelote cardsToBePlayed;
    private Bytes joueursNonJoue;
    private Bytes joueursJoue;
    private CustList<TrickBelote> plisFaits;
    private TrickBelote progressingTrick;
    private HandBelote cartesJouees;
    private EnumMap<Suit,HandBelote> repartitionCartesJouees;
    private EnumMap<Suit,CustList<HandBelote>> cartesPossibles;
    private EnumMap<Suit,CustList<HandBelote>> cartesCertaines;
    private byte ramasseurVirtuel;
    private EnumMap<Suit,CustList<HandBelote>> suitesTouteCouleur;
    private boolean maitreAtout;
    private EnumList<Suit> couleursMaitresses;
    private EnumList<Suit> strictCouleursMaitresses;
    private EnumMap<Suit,HandBelote> cartesMaitresses;
    private boolean maitreJeu;
    private EnumList<Suit> coupesFranches;
    private BidBeloteSuit contrat;
    private Bytes joueursConfiance;
    private Bytes joueursNonConfiance;
    private byte nextPlayer;
    private byte nbPlayers;

    BeloteInfoPliEnCours() {
    }

    public HandBelote getCartesJouables() {
        return playableCards;
    }

    public void setCartesJouables(HandBelote _cartesJouables) {
        playableCards = _cartesJouables;
    }

    public HandBelote getCartesAJouer() {
        return cardsToBePlayed;
    }

    public void setCartesAJouer(HandBelote _cartesAJouer) {
        cardsToBePlayed = _cartesAJouer;
    }

    public Bytes getJoueursNonJoue() {
        return joueursNonJoue;
    }

    public void setJoueursNonJoue(Bytes _joueursNonJoue) {
        joueursNonJoue = _joueursNonJoue;
    }

    public Bytes getJoueursJoue() {
        return joueursJoue;
    }

    public void setJoueursJoue(Bytes _joueursJoue) {
        joueursJoue = _joueursJoue;
    }

    public CustList<TrickBelote> getPlisFaits() {
        return plisFaits;
    }

    public void setPlisFaits(CustList<TrickBelote> _plisFaits) {
        plisFaits = _plisFaits;
    }

    public TrickBelote getProgressingTrick() {
        return progressingTrick;
    }

    public void setProgressingTrick(TrickBelote _progressingTrick) {
        progressingTrick = _progressingTrick;
    }

    public HandBelote getCartesJouees() {
        return cartesJouees;
    }

    public void setCartesJouees(HandBelote _cartesJouees) {
        cartesJouees = _cartesJouees;
    }

    public EnumMap<Suit,HandBelote> getRepartitionCartesJouees() {
        return repartitionCartesJouees;
    }

    public void setRepartitionCartesJouees(EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        repartitionCartesJouees = _repartitionCartesJouees;
    }

    public EnumMap<Suit,CustList<HandBelote>> getCartesPossibles() {
        return cartesPossibles;
    }

    public void setCartesPossibles(EnumMap<Suit,CustList<HandBelote>> _cartesPossibles) {
        cartesPossibles = _cartesPossibles;
    }

    public EnumMap<Suit,CustList<HandBelote>> getCartesCertaines() {
        return cartesCertaines;
    }

    public void setCartesCertaines(EnumMap<Suit,CustList<HandBelote>> _cartesCertaines) {
        cartesCertaines = _cartesCertaines;
    }

    public byte getRamasseurVirtuel() {
        return ramasseurVirtuel;
    }

    public void setRamasseurVirtuel(byte _ramasseurVirtuel) {
        ramasseurVirtuel = _ramasseurVirtuel;
    }

    public EnumMap<Suit,CustList<HandBelote>> getSuitesTouteCouleur() {
        return suitesTouteCouleur;
    }

    public void setSuitesTouteCouleur(EnumMap<Suit,CustList<HandBelote>> _suitesTouteCouleur) {
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

    public EnumList<Suit> getStrictCouleursMaitresses() {
        return strictCouleursMaitresses;
    }

    public void setStrictCouleursMaitresses(EnumList<Suit> _strictCouleursMaitresses) {
        strictCouleursMaitresses = _strictCouleursMaitresses;
    }

    public EnumMap<Suit,HandBelote> getCartesMaitresses() {
        return cartesMaitresses;
    }

    public void setCartesMaitresses(EnumMap<Suit,HandBelote> _cartesMaitresses) {
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

    public BidBeloteSuit getContrat() {
        return contrat;
    }

    public Suit getCouleurAtout() {
        return contrat.getCouleur();
    }

    public void setContrat(BidBeloteSuit _contrat) {
        contrat = _contrat;
    }

    public Bytes getJoueursConfiance() {
        return joueursConfiance;
    }

    public void setJoueursConfiance(Bytes _joueursConfiance) {
        joueursConfiance = _joueursConfiance;
    }

    public Bytes getJoueursNonConfiance() {
        return joueursNonConfiance;
    }

    public void setJoueursNonConfiance(Bytes _joueursNonConfiance) {
        joueursNonConfiance = _joueursNonConfiance;
    }

    public byte getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(byte _nextPlayer) {
        nextPlayer = _nextPlayer;
    }

    public byte getNbPlayers() {
        return nbPlayers;
    }

    public void setNbPlayers(byte _nbPlayers) {
        nbPlayers = _nbPlayers;
    }
}
