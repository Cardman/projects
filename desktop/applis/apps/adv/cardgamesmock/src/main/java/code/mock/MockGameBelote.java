package code.mock;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.Hypothesis;
import cards.consts.Suit;
import code.maths.montecarlo.AbstractGenerator;
import code.util.*;
import code.util.core.BoolVal;

public final class MockGameBelote implements IntGameBelote {
    private final CustList<BidBeloteSuit> bids = new CustList<BidBeloteSuit>();
    private final CustList<HandBelote> discardIa = new CustList<HandBelote>();
    private final IdList<CardBelote> discard = new IdList<CardBelote>();
    private final IdList<BoolVal> slams = new IdList<BoolVal>();
    private final IdList<CardBelote> cards = new IdList<CardBelote>();
    private final IdMap<Suit, CustList<HandBelote>> possible = new IdMap<Suit, CustList<HandBelote>>();
    private final IdMap<Hypothesis, IdMap<Suit, CustList<HandBelote>>> sure = new IdMap<Hypothesis, IdMap<Suit, CustList<HandBelote>>>();
    private final CustList<DealBelote> stacks = new CustList<DealBelote>();
    private int indexBid;
    private int indexDiscardIa;
    private int indexDiscard;
    private int indexSlam;
    private int indexCard;
    private int indexStack;
    @Override
    public BidBeloteSuit strategieContrat(GameBelote _game) {
        return incrBid();
    }

    @Override
    public BidBeloteSuit strategieContratUser(BidBeloteSuit _game) {
        return incrBid();
    }

    @Override
    public BidBeloteSuit currentBid() {
        return bids.get(indexBid);
    }

    private BidBeloteSuit incrBid() {
        BidBeloteSuit v_ = bids.get(indexBid);
        indexBid++;
        return v_;
    }

    @Override
    public HandBelote strategieEcart(GameBelote _g) {
        HandBelote v_ = discardIa.get(indexDiscardIa);
        indexDiscardIa++;
        return v_;
    }

    @Override
    public CardBelote discard(CardBelote _c) {
        CardBelote v_ = discard.get(indexDiscard);
        indexDiscard++;
        return v_;
    }

    @Override
    public CardBelote restore(CardBelote _c) {
        indexDiscard--;
        return discard.get(indexDiscard);
    }

    public CardBelote currentDiscard() {
        return discard.get(indexDiscard);
    }

    @Override
    public boolean annoncerUnChelem(GameBelote _g) {
        BoolVal v_ = slams.get(indexSlam);
        indexSlam++;
        return v_ == BoolVal.TRUE;
    }

    @Override
    public CardBelote strategieJeuCarteUnique(GameBelote _game) {
        return incrCard();
    }

    @Override
    public CardBelote strategieJeuCarteUniqueUser(CardBelote _game) {
        return incrCard();
    }

    @Override
    public CardBelote currentCard() {
        return cards.get(indexCard);
    }

    private CardBelote incrCard() {
        CardBelote v_ = cards.get(indexCard);
        indexCard++;
        return v_;
    }

    @Override
    public IdMap<Suit, CustList<HandBelote>> cartesPossibles(GameBeloteTrickInfo _info, HandBelote _curHand) {
        return getPossible();
    }

    @Override
    public IdMap<Hypothesis, IdMap<Suit, CustList<HandBelote>>> cartesCertaines(GameBeloteTrickInfo _info, IdMap<Suit, CustList<HandBelote>> _possible) {
        return getSure();
    }

    @Override
    public DealBelote empiler(long _nb, GameBelote _game, AbstractGenerator _gene) {
        return incrStack();
    }

    private DealBelote incrStack() {
        DealBelote v_ = stacks.get(indexStack);
        indexStack++;
        return v_;
    }
    public IdMap<Suit, CustList<HandBelote>> getPossible() {
        return possible;
    }

    public IdMap<Hypothesis, IdMap<Suit, CustList<HandBelote>>> getSure() {
        return sure;
    }

    public CustList<BidBeloteSuit> getBids() {
        return bids;
    }
    public CustList<HandBelote> getDiscardIa() {
        return discardIa;
    }

    public IdList<BoolVal> getSlams() {
        return slams;
    }

    public IdList<CardBelote> getDiscard() {
        return discard;
    }

    public IdList<CardBelote> getCards() {
        return cards;
    }

    public CustList<DealBelote> getStacks() {
        return stacks;
    }
}
