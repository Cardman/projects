package cards.belote.comparators;

import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.IdMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

abstract class GameStrengthHandBeloteComparator implements Comparing<Suit> {

    private final IdMap<Suit,HandBelote> hand;
    private final BidBeloteSuit bid;
    private final int rt;

    GameStrengthHandBeloteComparator(IdMap<Suit, HandBelote> _hand,
                                     BidBeloteSuit _bid, int _r) {
        hand = _hand;
        bid = _bid;
        rt = _r;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        HandBelote main1_ = hand.getVal(_suit1);
        HandBelote main2_ = hand.getVal(_suit2);
        int res_ = SortConstants.EQ_CMP;
        int min_ = NumberUtil.min(main1_.total(), main2_.total());
        for (int k = IndexConstants.FIRST_INDEX; k < min_; k++) {
            CardBelote carte1_ = main1_.carte(k);
            CardBelote carte2_ = main2_.carte(k);
            if (-rt*carte1_.strength(carte1_.getId().getCouleur(),bid) < -rt*carte2_
                    .strength(carte2_.getId().getCouleur(),bid)) {
                res_ = SortConstants.SWAP_SORT;
                break;
            }
            if (-rt*carte1_.strength(carte1_.getId().getCouleur(),bid) > -rt*carte2_
                    .strength(carte2_.getId().getCouleur(),bid)) {
                res_ = SortConstants.NO_SWAP_SORT;
            }
        }
        if (res_ == SortConstants.EQ_CMP) {
            res_ = rt*NumberUtil.compareLg(main1_.total(),main2_.total());
        }
        return res_;
    }
}
