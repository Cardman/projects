package cards.belote.comparators;
import cards.belote.BidBeloteSuit;
import cards.belote.GameBeloteBeginTrick;
import cards.belote.HandBelote;
import cards.consts.Suit;
import code.util.IdMap;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class HandBeloteGreatNbPtsCards implements Comparing<Suit> {

    private final IdMap<Suit,HandBelote> hand;
    private final BidBeloteSuit bid;

    public HandBeloteGreatNbPtsCards(IdMap<Suit,HandBelote> _hand, BidBeloteSuit _bid) {
        hand = _hand;
        bid = _bid;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        int total1_ = GameBeloteBeginTrick.nombreCartesPoints(hand, bid, _suit1);
        int total2_ = GameBeloteBeginTrick.nombreCartesPoints(hand, bid, _suit2);
        return NumberUtil.compareLg(total2_, total1_);
    }

}
