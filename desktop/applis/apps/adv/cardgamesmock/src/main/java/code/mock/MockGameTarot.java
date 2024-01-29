package code.mock;

import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.util.*;
import code.util.core.BoolVal;

public final class MockGameTarot implements IntGameTarot {
    private final IdList<BidTarot> bids = new IdList<BidTarot>();
    private final CustList<HandTarot> calls = new CustList<HandTarot>();
    private final CustList<HandTarot> discardIa = new CustList<HandTarot>();
    private final CustList<CallDiscard> discardVarIa = new CustList<CallDiscard>();
    private final IdList<CardTarot> discard = new IdList<CardTarot>();
    private final IdList<BoolVal> slams = new IdList<BoolVal>();
    private final IdList<CardTarot> cards = new IdList<CardTarot>();
    private final CustList<IdList<Handfuls>> handfuls = new CustList<IdList<Handfuls>>();
    private final CustList<HandTarot> handfulsCard = new CustList<HandTarot>();
    private final CustList<IdList<Miseres>> miseres = new CustList<IdList<Miseres>>();
    private int indexBid;
    private int indexCall;
    private int indexDiscardIa;
    private int indexDiscardVarIa;
    private int indexDiscard;
    private int indexSlam;
    private int indexCard;
    private int indexHandful;
    private int indexHandfulCard;
    private int indexMiseres;
    @Override
    public BidTarot strategieContrat(GameTarot _g) {
        return incrBid();
    }

    @Override
    public BidTarot strategieContratUser(BidTarot _g) {
        return incrBid();
    }

    private BidTarot incrBid() {
        BidTarot v_ = bids.get(indexBid);
        indexBid++;
        return v_;
    }
    @Override
    public BidTarot currentBid() {
        return bids.get(indexBid);
    }

    @Override
    public HandTarot strategieAppel(GameTarot _g) {
        return incrCall();
    }

    @Override
    public HandTarot strategieAppelUser(HandTarot _g) {
        return incrCall();
    }

    private HandTarot incrCall() {
        HandTarot v_ = calls.get(indexCall);
        indexCall++;
        return v_;
    }
    @Override
    public HandTarot currentCall() {
        return calls.get(indexCall);
    }

    @Override
    public HandTarot strategieEcart(GameTarot _g) {
        HandTarot v_ = discardIa.get(indexDiscardIa);
        indexDiscardIa++;
        return v_;
    }

    @Override
    public CallDiscard strategieAppelApresEcart(GameTarot _g, boolean _removeDog) {
        CallDiscard v_ = discardVarIa.get(indexDiscardVarIa);
        indexDiscardVarIa++;
        return v_;
    }

    @Override
    public CardTarot discard(CardTarot _c) {
        CardTarot v_ = discard.get(indexDiscard);
        indexDiscard++;
        return v_;
    }

    @Override
    public CardTarot restore(CardTarot _c) {
        indexDiscard--;
        return discard.get(indexDiscard);
    }

    @Override
    public CardTarot currentDiscard() {
        return discard.get(indexDiscard);
    }

    @Override
    public boolean annoncerUnChelem(GameTarot _g, byte _numeroJoueur) {
        BoolVal v_ = slams.get(indexSlam);
        indexSlam++;
        return v_ == BoolVal.TRUE;
    }

    @Override
    public CardTarot changerConfianceJeuCarteUnique(GameTarot _g) {
        return incrCard();
    }

    @Override
    public CardTarot changerConfianceJeuCarteUniqueUser(CardTarot _g) {
        return incrCard();
    }

    private CardTarot incrCard() {
        CardTarot v_ = cards.get(indexCard);
        indexCard++;
        return v_;
    }
    @Override
    public CardTarot currentCard() {
        return cards.get(indexCard);
    }

    @Override
    public IdList<Handfuls> handful(IdList<Handfuls> _h) {
        IdList<Handfuls> v_ = handfuls.get(indexHandful);
        indexHandful++;
        return v_;
    }

    @Override
    public IdList<Handfuls> currentHandful() {
        return handfuls.get(indexHandful);
    }

    @Override
    public HandTarot handfulCard(HandTarot _h) {
        HandTarot v_ = handfulsCard.get(indexHandfulCard);
        indexHandfulCard++;
        return v_;
    }

    @Override
    public HandTarot currentHandfulCard() {
        return handfulsCard.get(indexHandfulCard);
    }

    @Override
    public IdList<Miseres> misere(IdList<Miseres> _h) {
        IdList<Miseres> v_ = miseres.get(indexMiseres);
        indexMiseres++;
        return v_;
    }

    @Override
    public IdList<Miseres> currentMiseres() {
        return miseres.get(indexMiseres);
    }

    public IdList<BidTarot> getBids() {
        return bids;
    }

    public CustList<CallDiscard> getDiscardVarIa() {
        return discardVarIa;
    }

    public CustList<HandTarot> getCalls() {
        return calls;
    }

    public CustList<HandTarot> getDiscardIa() {
        return discardIa;
    }

    public CustList<HandTarot> getHandfulsCard() {
        return handfulsCard;
    }

    public IdList<BoolVal> getSlams() {
        return slams;
    }

    public IdList<CardTarot> getCards() {
        return cards;
    }

    public IdList<CardTarot> getDiscard() {
        return discard;
    }

    public CustList<IdList<Handfuls>> getHandfuls() {
        return handfuls;
    }

    public CustList<IdList<Miseres>> getMiseres() {
        return miseres;
    }
}
