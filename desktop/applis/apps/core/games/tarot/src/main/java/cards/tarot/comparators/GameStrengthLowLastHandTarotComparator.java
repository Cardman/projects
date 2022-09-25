package cards.tarot.comparators;

import cards.consts.Suit;
import cards.tarot.HandTarot;
import cards.tarot.enumerations.CardTarot;
import code.util.comparators.ComparatorBoolean;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class GameStrengthLowLastHandTarotComparator implements Comparing<Suit> {

    private final HandTarot hand;

    public GameStrengthLowLastHandTarotComparator(HandTarot _hand) {
        hand = _hand;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        HandTarot main1_ = hand.couleur(_suit1);
        HandTarot main2_ = hand.couleur(_suit2);
        int res_;
        int min_ = NumberUtil.min(main1_.total(), main2_.total());
        if(min_ == 0) {
            res_ = ComparatorBoolean.cmp(main1_.estVide(), main2_.estVide());
        }else{
            CardTarot carte1_ = main1_.derniereCarte();
            CardTarot carte2_ = main2_.derniereCarte();
            res_ = NumberUtil.compareLg(carte1_.strength(_suit1), carte2_
                    .strength(_suit2));
        }
        return res_;
    }

}
