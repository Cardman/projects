package cards.gui.animations;

import cards.belote.BidBeloteSuit;
import cards.consts.DisplayingCommon;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import code.util.IdList;

public final class BeloteSortingSummedTwoHands implements IntSortingSummedTwoHands<CardBelote> {
    private final BidBeloteSuit bidBeloteSuit;

    public BeloteSortingSummedTwoHands(BidBeloteSuit _b) {
        this.bidBeloteSuit = _b;
    }

    @Override
    public IdList<CardBelote> sorted(IdList<CardBelote> _one, IdList<CardBelote> _two, DisplayingCommon _dis) {
        IdList<CardBelote> union_ = new IdList<CardBelote>();
        union_.addAllElts(_one);
        union_.addAllElts(_two);
        HandBelote hand_ = new HandBelote();
        hand_.setCards(union_);
        hand_.trier(_dis.getSuits(), _dis.isDecreasing(), bidBeloteSuit);
        return hand_.getCards();
    }
}
