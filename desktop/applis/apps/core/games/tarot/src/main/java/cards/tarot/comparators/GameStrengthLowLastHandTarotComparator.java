package cards.tarot.comparators;
import cards.consts.Suit;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class GameStrengthLowLastHandTarotComparator implements Comparing<Suit> {

    private HandTarot hand;

    public GameStrengthLowLastHandTarotComparator(HandTarot _hand) {
        hand = _hand;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        HandTarot main1_ = hand.couleur(_suit1);
        HandTarot main2_ = hand.couleur(_suit2);
        boolean aussiHaut_ = true;
        boolean permuter_ = false;
        int min_ = Math.min(main1_.total(), main2_.total());
        if(min_ == 0) {
            if(main1_.estVide() && !main2_.estVide()) {
                aussiHaut_ = false;
                permuter_ = true;
            }
            if(main2_.estVide() && !main1_.estVide()) {
                aussiHaut_ = false;
            }
        }else{
            CardTarot carte1_ = main1_.derniereCarte();
            CardTarot carte2_ = main2_.derniereCarte();
            if (carte1_.strength(_suit1) > carte2_
                    .strength(_suit2)) {
                permuter_ = true;
                aussiHaut_ = false;
            }
            if (carte1_.strength(_suit1) < carte2_
                    .strength(_suit2)) {
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
