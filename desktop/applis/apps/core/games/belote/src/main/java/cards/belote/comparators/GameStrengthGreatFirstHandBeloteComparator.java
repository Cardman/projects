package cards.belote.comparators;
import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.EnumMap;
import code.util.core.IndexConstants;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class GameStrengthGreatFirstHandBeloteComparator implements
        Comparing<Suit> {

    private EnumMap<Suit,HandBelote> hand;
    private BidBeloteSuit bid;

    public GameStrengthGreatFirstHandBeloteComparator(
            EnumMap<Suit,HandBelote> _hand, BidBeloteSuit _bid) {
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
        if(min_ == IndexConstants.SIZE_EMPTY) {
            if(main1_.estVide() && !main2_.estVide()) {
                aussiHaut_ = false;
                permuter_ = true;
            }
            if(!main1_.estVide()) {
                aussiHaut_ = false;
            }
        }else{
            CardBelote carte1_ = main1_.premiereCarte();
            CardBelote carte2_ = main2_.premiereCarte();
            if (carte1_.strength(_suit1,bid) < carte2_
                    .strength(_suit2,bid)) {
                permuter_ = true;
                aussiHaut_ = false;
            }
            if (carte1_.strength(_suit1,bid) > carte2_
                    .strength(_suit2,bid)) {
                aussiHaut_ = false;
            }
        }
        if (permuter_) {
            return SortConstants.SWAP_SORT;
        }
        if (aussiHaut_) {
            return SortConstants.EQ_CMP;
        }
        return SortConstants.NO_SWAP_SORT;
    }

}
