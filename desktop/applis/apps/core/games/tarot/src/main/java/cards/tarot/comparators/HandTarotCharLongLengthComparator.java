package cards.tarot.comparators;
import cards.consts.Suit;
import cards.tarot.HandTarot;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class HandTarotCharLongLengthComparator implements Comparing<Suit> {

    private HandTarot hand;

    public HandTarotCharLongLengthComparator(HandTarot _hand) {
        hand = _hand;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        HandTarot main1_ = hand.charCardsBySuit(_suit1);
        HandTarot main2_ = hand.charCardsBySuit(_suit2);
        return NumberUtil.compareLg(main2_.total(), main1_.total());
    }

}
