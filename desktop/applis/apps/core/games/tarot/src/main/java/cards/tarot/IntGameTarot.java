package cards.tarot;

import cards.consts.Hypothesis;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.maths.montecarlo.AbstractGenerator;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;

public interface IntGameTarot {
    BidTarot strategieContrat(GameTarot _g);
    BidTarot strategieContratUser(BidTarot _g);
    BidTarot currentBid();
    HandTarot strategieAppel(GameTarot _g);
    HandTarot strategieAppelUser(HandTarot _g);
    HandTarot currentCall();
    HandTarot strategieEcart(GameTarot _g);
    CallDiscard strategieAppelApresEcart(GameTarot _g,boolean _removeDog);
    CardTarot discard(CardTarot _c);
    CardTarot restore(CardTarot _c);
    CardTarot currentDiscard();
    boolean annoncerUnChelem(GameTarot _g);
    CardTarot changerConfianceJeuCarteUnique(GameTarot _g);
    CardTarot changerConfianceJeuCarteUniqueQuick(GameTarot _g);
    CardTarot changerConfianceJeuCarteUniqueUser(CardTarot _g);
    CardTarot currentCard();
    IdList<Handfuls> handful(IdList<Handfuls> _h);
    IdList<Handfuls> currentHandful();
    HandTarot handfulCard(HandTarot _h);
    HandTarot currentHandfulCard();
    IdList<Miseres> misere(IdList<Miseres> _h);
    IdList<Miseres> currentMiseres();
    GameTarotTrickInfo gameTarotTrickInfo(GameTarot _g);
    IdMap<Suit, CustList<HandTarot>> cartesPossibles(GameTarotTrickInfo _g,HandTarot _cartesJoueur);
    IdMap<Hypothesis,IdMap<Suit,CustList<HandTarot>>> cartesCertaines(GameTarotTrickInfo _g,
            IdMap<Suit, CustList<HandTarot>> _cartesPossibles);
    DealTarot empiler(long _nb, GameTarot _game, AbstractGenerator _gene);
}
