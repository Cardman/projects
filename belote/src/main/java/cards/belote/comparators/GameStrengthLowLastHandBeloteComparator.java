package cards.belote.comparators;
import java.util.Comparator;

import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EnumMap;

public final class GameStrengthLowLastHandBeloteComparator implements
        Comparator<Suit> {

    private EnumMap<Suit,HandBelote> hand;
    private BidBeloteSuit bid;

    public GameStrengthLowLastHandBeloteComparator(EnumMap<Suit,HandBelote> _hand,
            BidBeloteSuit _bid) {
        hand = _hand;
        bid = _bid;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        HandBelote main1_ = hand.getVal(_suit1);
        HandBelote main2_ = hand.getVal(_suit2);
        boolean aussiHaut_ = true;
        boolean permuter_ = false;
        int min_ = Math.min(main1_.total(), main2_.total());
        if(min_ == CustList.SIZE_EMPTY) {
            if(main1_.estVide() && !main2_.estVide()) {
                aussiHaut_ = false;
                permuter_ = true;
            }
            if(!main1_.estVide() && main2_.estVide()) {
                aussiHaut_ = false;
            }
        }else{
            CardBelote carte1_ = main1_.derniereCarte();
            CardBelote carte2_ = main2_.derniereCarte();
            if (carte1_.strength(_suit1,bid) > carte2_
                    .strength(_suit2,bid)) {
                permuter_ = true;
                aussiHaut_ = false;
            }
            if (carte1_.strength(_suit1,bid) < carte2_
                    .strength(_suit2,bid)) {
                aussiHaut_ = false;
            }
        }
        if (permuter_) {
            return CustList.SWAP_SORT;
        }
        if (aussiHaut_) {
            return CustList.EQ_CMP;
        }
        return CustList.NO_SWAP_SORT;
    }

}
