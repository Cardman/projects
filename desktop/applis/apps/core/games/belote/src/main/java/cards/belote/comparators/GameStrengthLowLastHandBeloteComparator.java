package cards.belote.comparators;

import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.EnumMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class GameStrengthLowLastHandBeloteComparator implements
        Comparing<Suit> {

    private final EnumMap<Suit,HandBelote> hand;
    private final BidBeloteSuit bid;

    public GameStrengthLowLastHandBeloteComparator(EnumMap<Suit,HandBelote> _hand,
            BidBeloteSuit _bid) {
        hand = _hand;
        bid = _bid;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        HandBelote main1_ = hand.getVal(_suit1);
        HandBelote main2_ = hand.getVal(_suit2);
        int res_;
        int min_ = Math.min(main1_.total(), main2_.total());
        if(min_ == IndexConstants.SIZE_EMPTY) {
            res_ = ComparatorBoolean.cmp(main1_.estVide(), main2_.estVide());
        }else{
            CardBelote carte1_ = main1_.derniereCarte();
            CardBelote carte2_ = main2_.derniereCarte();
            res_ = NumberUtil.compareLg(carte1_.strength(_suit1,bid), carte2_
                    .strength(_suit2,bid));
        }
        return res_;
    }

}
