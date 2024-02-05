package cards.tarot;

import cards.consts.Hypothesis;
import cards.consts.Suit;
import cards.tarot.enumerations.*;
import code.util.*;

public final class DefGameTarot implements IntGameTarot {
    @Override
    public BidTarot strategieContrat(GameTarot _g) {
        return _g.strategieContrat();
    }

    @Override
    public BidTarot strategieContratUser(BidTarot _g) {
        return _g;
    }

    @Override
    public BidTarot currentBid() {
        return BidTarot.SLAM;
    }

    @Override
    public HandTarot strategieAppel(GameTarot _g) {
        return _g.strategieAppel();
    }

    @Override
    public HandTarot strategieAppelUser(HandTarot _g) {
        return _g;
    }

    @Override
    public HandTarot currentCall() {
        return new HandTarot();
    }

    @Override
    public HandTarot strategieEcart(GameTarot _g) {
        return _g.strategieEcart();
    }

    @Override
    public CallDiscard strategieAppelApresEcart(GameTarot _g, boolean _removeDog) {
        return _g.strategieAppelApresEcart(_removeDog);
    }

    @Override
    public CardTarot discard(CardTarot _c) {
        return _c;
    }

    @Override
    public CardTarot restore(CardTarot _c) {
        return _c;
    }

    @Override
    public CardTarot currentDiscard() {
        return CardTarot.WHITE;
    }

    @Override
    public boolean annoncerUnChelem(GameTarot _g, byte _numeroJoueur) {
        return _g.annoncerUnChelem(_numeroJoueur);
    }

    @Override
    public CardTarot changerConfianceJeuCarteUnique(GameTarot _g) {
        return _g.changerConfianceJeuCarteUnique();
    }

    @Override
    public CardTarot changerConfianceJeuCarteUniqueQuick(GameTarot _g) {
        return _g.changerConfianceJeuCarteUniqueQuick();
    }

    @Override
    public CardTarot changerConfianceJeuCarteUniqueUser(CardTarot _g) {
        return _g;
    }

    @Override
    public CardTarot currentCard() {
        return CardTarot.WHITE;
    }

    @Override
    public IdList<Handfuls> handful(IdList<Handfuls> _h) {
        return _h;
    }

    @Override
    public IdList<Handfuls> currentHandful() {
        return new IdList<Handfuls>();
    }

    @Override
    public HandTarot handfulCard(HandTarot _h) {
        return _h;
    }

    @Override
    public HandTarot currentHandfulCard() {
        return new HandTarot();
    }

    @Override
    public IdList<Miseres> misere(IdList<Miseres> _h) {
        return _h;
    }

    @Override
    public IdList<Miseres> currentMiseres() {
        return new IdList<Miseres>();
    }

    @Override
    public IdMap<Suit, CustList<HandTarot>> cartesPossibles(GameTarotTrickInfo _g, HandTarot _cartesJoueur) {
        return _g.cartesPossibles(_cartesJoueur);
    }

    @Override
    public IdMap<Hypothesis, IdMap<Suit, CustList<HandTarot>>> cartesCertaines(GameTarotTrickInfo _g, IdMap<Suit, CustList<HandTarot>> _cartesPossibles) {
        return _g.cartesCertaines(_cartesPossibles);
    }
}
