package cards.tarot.comparators;
import cards.consts.Suit;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class GameStrengthGreatHandTarotComparator implements Comparing<Suit> {

    private final HandTarot hand;

    public GameStrengthGreatHandTarotComparator(HandTarot _hand) {
        hand = _hand;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        HandTarot main1_ = hand.couleur(_suit1);
        HandTarot main2_ = hand.couleur(_suit2);
        int res_ = SortConstants.EQ_CMP;
        int min_ = Math.min(main1_.total(), main2_.total());
        for (int k = IndexConstants.FIRST_INDEX; k < min_; k++) {
            CardTarot carte1_ = main1_.carte(k);
            CardTarot carte2_ = main2_.carte(k);
            if (carte1_.strength(carte1_.getId().getCouleur()) < carte2_
                    .strength(carte2_.getId().getCouleur())) {
                res_ = SortConstants.SWAP_SORT;
                break;
            }
            if (carte1_.strength(carte1_.getId().getCouleur()) > carte2_
                    .strength(carte2_.getId().getCouleur())) {
                res_ = SortConstants.NO_SWAP_SORT;
            }
        }
        if (res_ == SortConstants.EQ_CMP) {
            res_ = -NumberUtil.compareLg(main1_.total(), main2_.total());
        }
        return res_;
    }

}
