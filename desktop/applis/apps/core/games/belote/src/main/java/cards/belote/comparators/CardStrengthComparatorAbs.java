package cards.belote.comparators;

import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.IdMap;
import code.util.comparators.ComparatorBoolean;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

abstract class CardStrengthComparatorAbs implements CardStrengthComparator {
    private final int rt;

    CardStrengthComparatorAbs(int _r) {
        this.rt = _r;
    }
    public int compare(IdMap<Suit,HandBelote> _hand, BidBeloteSuit _bid, Suit _suit1, Suit _suit2) {
        HandBelote main1_ = _hand.getVal(_suit1);
        HandBelote main2_ = _hand.getVal(_suit2);
        int res_;
        int min_ = Math.min(main1_.total(), main2_.total());
        if(min_ == IndexConstants.SIZE_EMPTY) {
            res_ = ComparatorBoolean.cmp(main1_.estVide(), main2_.estVide());
        }else{
            CardBelote carte1_ = card(main1_);
            CardBelote carte2_ = card(main2_);
            res_ = rate()*NumberUtil.compareLg(carte1_.strength(_suit1,_bid) , carte2_
                    .strength(_suit2,_bid));
        }
        return res_;
    }
    @Override
    public int rate() {
        return rt;
    }
}
