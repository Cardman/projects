package cards.belote;
import cards.consts.Suit;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;

public final class BeloteInfoPliEnCours {

    private Ints joueursNonJoue;
    private CustList<TrickBelote> plisFaits;
    private TrickBelote progressingTrick;
    private HandBelote cartesJouees;
    private IdMap<Suit,HandBelote> repartitionCartesJouees;
    private IdMap<Suit,CustList<HandBelote>> cartesPossibles;
    private IdMap<Suit,CustList<HandBelote>> cartesCertaines;
    private int ramasseurVirtuel;
    private IdMap<Suit,CustList<HandBelote>> suitesTouteCouleur;
    private boolean maitreAtout;
    private IdList<Suit> couleursMaitresses;
    private IdList<Suit> strictCouleursMaitresses;
    private IdMap<Suit,HandBelote> cartesMaitresses;
    private boolean maitreJeu;
    private BidBeloteSuit contrat;
    private Ints joueursConfiance;
    private Ints joueursNonConfiance;
    private int nextPlayer;
    private int nbPlayers;

    BeloteInfoPliEnCours() {
    }

    public Ints getJoueursNonJoue() {
        return joueursNonJoue;
    }

    public void setJoueursNonJoue(Ints _joueursNonJoue) {
        joueursNonJoue = _joueursNonJoue;
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

    public IdMap<Suit,HandBelote> getRepartitionCartesJouees() {
        return repartitionCartesJouees;
    }

    public void setRepartitionCartesJouees(IdMap<Suit,HandBelote> _repartitionCartesJouees) {
        repartitionCartesJouees = _repartitionCartesJouees;
    }

    public IdMap<Suit,CustList<HandBelote>> getCartesPossibles() {
        return cartesPossibles;
    }

    public void setCartesPossibles(IdMap<Suit,CustList<HandBelote>> _cartesPossibles) {
        cartesPossibles = _cartesPossibles;
    }

    public IdMap<Suit,CustList<HandBelote>> getCartesCertaines() {
        return cartesCertaines;
    }

    public void setCartesCertaines(IdMap<Suit,CustList<HandBelote>> _cartesCertaines) {
        cartesCertaines = _cartesCertaines;
    }

    public int getRamasseurVirtuel() {
        return ramasseurVirtuel;
    }

    public void setRamasseurVirtuel(int _ramasseurVirtuel) {
        ramasseurVirtuel = _ramasseurVirtuel;
    }

    public IdMap<Suit,CustList<HandBelote>> getSuitesTouteCouleur() {
        return suitesTouteCouleur;
    }

    public void setSuitesTouteCouleur(IdMap<Suit,CustList<HandBelote>> _suitesTouteCouleur) {
        suitesTouteCouleur = _suitesTouteCouleur;
    }

    public boolean isMaitreAtout() {
        return maitreAtout;
    }

    public void setMaitreAtout(boolean _maitreAtout) {
        maitreAtout = _maitreAtout;
    }

    public IdList<Suit> getCouleursMaitresses() {
        return couleursMaitresses;
    }

    public void setCouleursMaitresses(IdList<Suit> _couleursMaitresses) {
        couleursMaitresses = _couleursMaitresses;
    }

    public IdList<Suit> getStrictCouleursMaitresses() {
        return strictCouleursMaitresses;
    }

    public void setStrictCouleursMaitresses(IdList<Suit> _strictCouleursMaitresses) {
        strictCouleursMaitresses = _strictCouleursMaitresses;
    }

    public IdMap<Suit,HandBelote> getCartesMaitresses() {
        return cartesMaitresses;
    }

    public void setCartesMaitresses(IdMap<Suit,HandBelote> _cartesMaitresses) {
        cartesMaitresses = _cartesMaitresses;
    }

    public boolean isMaitreJeu() {
        return maitreJeu;
    }

    public void setMaitreJeu(boolean _maitreJeu) {
        maitreJeu = _maitreJeu;
    }

    public BidBeloteSuit getContrat() {
        return contrat;
    }

    public Suit getCouleurAtout() {
        return contrat.getSuit();
    }

    public void setContrat(BidBeloteSuit _contrat) {
        contrat = _contrat;
    }

    public Ints getJoueursConfiance() {
        return joueursConfiance;
    }

    public void setJoueursConfiance(Ints _joueursConfiance) {
        joueursConfiance = _joueursConfiance;
    }

    public Ints getJoueursNonConfiance() {
        return joueursNonConfiance;
    }

    public void setJoueursNonConfiance(Ints _joueursNonConfiance) {
        joueursNonConfiance = _joueursNonConfiance;
    }

    public int getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(int _nextPlayer) {
        nextPlayer = _nextPlayer;
    }

    public int getNbPlayers() {
        return nbPlayers;
    }

    public void setNbPlayers(int _nbPlayers) {
        nbPlayers = _nbPlayers;
    }
}
