package cards.belote.comparators;
import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.EnumMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

/**Only "no trump" suits can be sorted with this comparator*/
public final class GameStrengthGreatHandBeloteComparator implements Comparing<Suit> {

    private final EnumMap<Suit,HandBelote> hand;
    private final BidBeloteSuit bid;

    public GameStrengthGreatHandBeloteComparator(EnumMap<Suit,HandBelote> _hand,
            BidBeloteSuit _bid) {
        hand = _hand;
        bid = _bid;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        HandBelote main1_ = hand.getVal(_suit1);
        HandBelote main2_ = hand.getVal(_suit2);
        int res_ = SortConstants.EQ_CMP;
        int min_ = Math.min(main1_.total(), main2_.total());
        for (int k = IndexConstants.FIRST_INDEX; k < min_; k++) {
            CardBelote carte1_ = main1_.carte(k);
            CardBelote carte2_ = main2_.carte(k);
            if (carte1_.strength(carte1_.getId().getCouleur(),bid) < carte2_
                    .strength(carte2_.getId().getCouleur(),bid)) {
                res_ = SortConstants.SWAP_SORT;
                break;
            }
            if (carte1_.strength(carte1_.getId().getCouleur(),bid) > carte2_
                    .strength(carte2_.getId().getCouleur(),bid)) {
                res_ = SortConstants.NO_SWAP_SORT;
            }
        }
        if (res_ == SortConstants.EQ_CMP) {
            res_ = -NumberUtil.compareLg(main1_.total(),main2_.total());
        }
        return res_;
    }

}
