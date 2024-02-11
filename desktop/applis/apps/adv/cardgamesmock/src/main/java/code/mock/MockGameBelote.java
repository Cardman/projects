package code.mock;

import cards.belote.*;
import cards.belote.enumerations.*;
import cards.consts.Hypothesis;
import cards.consts.Suit;
import code.maths.montecarlo.AbstractGenerator;
import code.util.*;

public final class MockGameBelote implements IntGameBelote {
    private final CustList<BidBeloteSuit> bids = new CustList<BidBeloteSuit>();
    private final IdList<CardBelote> cards = new IdList<CardBelote>();
    private final IdMap<Suit, CustList<HandBelote>> possible = new IdMap<Suit, CustList<HandBelote>>();
    private final IdMap<Hypothesis, IdMap<Suit, CustList<HandBelote>>> sure = new IdMap<Hypothesis, IdMap<Suit, CustList<HandBelote>>>();
    private final CustList<DealBelote> stacks = new CustList<DealBelote>();
    private int indexBid;
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
    public DealBelote empiler(long _nb, DisplayingBelote _dis, GameBelote _game, AbstractGenerator _gene) {
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

    public IdList<CardBelote> getCards() {
        return cards;
    }

    public CustList<DealBelote> getStacks() {
        return stacks;
    }
}
