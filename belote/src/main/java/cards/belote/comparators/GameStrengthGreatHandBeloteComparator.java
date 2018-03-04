package cards.belote.comparators;
import java.util.Comparator;

import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EnumMap;

/**Only "no trump" suits can be sorted with this comparator*/
public final class GameStrengthGreatHandBeloteComparator implements Comparator<Suit> {

    private EnumMap<Suit,HandBelote> hand;
    private BidBeloteSuit bid;

    public GameStrengthGreatHandBeloteComparator(EnumMap<Suit,HandBelote> _hand,
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
        for (int k = CustList.FIRST_INDEX; k < min_; k++) {
            CardBelote carte1_ = main1_.carte(k);
            CardBelote carte2_ = main2_.carte(k);
            if (carte1_.strength(carte1_.couleur(),bid) < carte2_
                    .strength(carte2_.couleur(),bid)) {
                permuter_ = true;
                aussiHaut_ = false;
                break;
            }
            if (carte1_.strength(carte1_.couleur(),bid) > carte2_
                    .strength(carte2_.couleur(),bid)) {
                aussiHaut_ = false;
            }
        }
        if (aussiHaut_) {
            if (main1_.total() < main2_.total()) {
                permuter_ = true;
                aussiHaut_ = false;
            } else if (main1_.total() > main2_.total()) {
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
