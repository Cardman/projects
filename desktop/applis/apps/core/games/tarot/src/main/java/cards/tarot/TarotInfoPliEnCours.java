package cards.tarot;
import cards.consts.Suit;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;

public final class TarotInfoPliEnCours {

    private HandTarot cartesJouables;
    private Bytes joueursNonJoue;
    private CustList<TrickTarot> plisFaits;
    private TrickTarot progressingTrick;
    private HandTarot cartesJouees;
    private IdMap<Suit,HandTarot> repartitionCartesJouees;
    private boolean carteAppeleeJouee;
    private boolean contientExcuse;
    private IdMap<Suit,CustList<HandTarot>> cartesPossibles;
    private IdMap<Suit,CustList<HandTarot>> cartesCertaines;
    private byte ramasseurVirtuel;
    private IdMap<Suit,CustList<HandTarot>> suitesTouteCouleur;
    private boolean maitreAtout;
    private IdList<Suit> couleursMaitresses;
    private IdMap<Suit,HandTarot> cartesMaitresses;
    private boolean maitreJeu;
    private IdList<Suit> coupesFranches;
    private IdList<Suit> calledSuits;
    private byte nbPlayers;
    private byte currentPlayer;
    private byte taker;
    private Bytes joueursConfiance;
    private Bytes joueursNonConfiance;

    TarotInfoPliEnCours() {
    }

    public byte getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(byte _currentPlayer) {
        currentPlayer = _currentPlayer;
    }

    public HandTarot getCartesJouables() {
        return cartesJouables;
    }

    public void setCartesJouables(HandTarot _cartesJouables) {
        cartesJouables = _cartesJouables;
    }

    public Bytes getJoueursNonJoue() {
        return joueursNonJoue;
    }

    public void setJoueursNonJoue(Bytes _joueursNonJoue) {
        joueursNonJoue = _joueursNonJoue;
    }

    public CustList<TrickTarot> getPlisFaits() {
        return plisFaits;
    }

    public void setPlisFaits(CustList<TrickTarot> _plisFaits) {
        plisFaits = _plisFaits;
    }

    public TrickTarot getProgressingTrick() {
        return progressingTrick;
    }

    public void setProgressingTrick(TrickTarot _progressingTrick) {
        progressingTrick = _progressingTrick;
    }

    public HandTarot getCartesJouees() {
        return cartesJouees;
    }

    public void setCartesJouees(HandTarot _cartesJouees) {
        cartesJouees = _cartesJouees;
    }

    public IdMap<Suit,HandTarot> getRepartitionCartesJouees() {
        return repartitionCartesJouees;
    }

    public void setRepartitionCartesJouees(IdMap<Suit,HandTarot> _repartitionCartesJouees) {
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

    public IdMap<Suit,CustList<HandTarot>> getCartesPossibles() {
        return cartesPossibles;
    }

    public void setCartesPossibles(IdMap<Suit,CustList<HandTarot>> _cartesPossibles) {
        cartesPossibles = _cartesPossibles;
    }

    public IdMap<Suit,CustList<HandTarot>> getCartesCertaines() {
        return cartesCertaines;
    }

    public void setCartesCertaines(IdMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        cartesCertaines = _cartesCertaines;
    }

    public byte getRamasseurVirtuel() {
        return ramasseurVirtuel;
    }

    public void setRamasseurVirtuel(byte _ramasseurVirtuel) {
        ramasseurVirtuel = _ramasseurVirtuel;
    }

    public IdMap<Suit,CustList<HandTarot>> getSuitesTouteCouleur() {
        return suitesTouteCouleur;
    }

    public void setSuitesTouteCouleur(IdMap<Suit,CustList<HandTarot>> _suitesTouteCouleur) {
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

    public IdMap<Suit,HandTarot> getCartesMaitresses() {
        return cartesMaitresses;
    }

    public void setCartesMaitresses(IdMap<Suit,HandTarot> _cartesMaitresses) {
        cartesMaitresses = _cartesMaitresses;
    }

    public boolean isMaitreJeu() {
        return maitreJeu;
    }

    public void setMaitreJeu(boolean _maitreJeu) {
        maitreJeu = _maitreJeu;
    }

    public IdList<Suit> getCoupesFranches() {
        return coupesFranches;
    }

    public void setCoupesFranches(IdList<Suit> _coupesFranches) {
        coupesFranches = _coupesFranches;
    }

    public IdList<Suit> getCalledSuits() {
        return calledSuits;
    }

    public void setCalledSuits(IdList<Suit> _calledSuits) {
        calledSuits = _calledSuits;
    }

    public byte getNbPlayers() {
        return nbPlayers;
    }

    public void setNbPlayers(byte _nbPlayers) {
        nbPlayers = _nbPlayers;
    }

    public byte getTaker() {
        return taker;
    }

    public void setTaker(byte _taker) {
        taker = _taker;
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
}
