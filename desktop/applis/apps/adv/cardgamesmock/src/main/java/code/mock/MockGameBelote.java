package code.mock;

import cards.belote.*;
import cards.belote.enumerations.*;
import code.util.*;

public final class MockGameBelote implements IntGameBelote {
    private final CustList<BidBeloteSuit> bids = new CustList<BidBeloteSuit>();
    private final IdList<CardBelote> cards = new IdList<CardBelote>();
    private int indexBid;
    private int indexCard;
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

    public CustList<BidBeloteSuit> getBids() {
        return bids;
    }

    public IdList<CardBelote> getCards() {
        return cards;
    }
}
