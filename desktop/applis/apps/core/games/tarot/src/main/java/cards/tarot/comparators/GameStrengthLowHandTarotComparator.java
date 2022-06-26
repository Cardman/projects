package cards.tarot.comparators;
import cards.consts.Suit;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.core.IndexConstants;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class GameStrengthLowHandTarotComparator implements Comparing<Suit> {

    private HandTarot hand;

    public GameStrengthLowHandTarotComparator(HandTarot _hand) {
        hand = _hand;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        HandTarot main1_ = hand.couleur(_suit1);
        HandTarot main2_ = hand.couleur(_suit2);
        boolean aussiHaut_ = true;
        boolean permuter_ = false;
        int min_ = Math.min(main1_.total(), main2_.total());
        for (int k = IndexConstants.FIRST_INDEX; k < min_; k++) {
            CardTarot carte1_ = main1_.carte(k);
            CardTarot carte2_ = main2_.carte(k);
            if (carte1_.strength(carte1_.getId().getCouleur()) > carte2_
                    .strength(carte2_.getId().getCouleur())) {
                permuter_ = true;
                aussiHaut_ = false;
                break;
            }
            if (carte1_.strength(carte1_.getId().getCouleur()) < carte2_
                    .strength(carte2_.getId().getCouleur())) {
                aussiHaut_ = false;
            }
        }
        if (aussiHaut_) {
            if (main1_.total() > main2_.total()) {
                permuter_ = true;
                aussiHaut_ = false;
            } else if (main1_.total() < main2_.total()) {
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
