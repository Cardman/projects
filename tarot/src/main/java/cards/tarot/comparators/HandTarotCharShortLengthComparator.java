package cards.tarot.comparators;
import java.util.Comparator;

import code.util.Numbers;
import cards.consts.Suit;
import cards.tarot.HandTarot;

public final class HandTarotCharShortLengthComparator implements Comparator<Suit> {

    private HandTarot hand;

    public HandTarotCharShortLengthComparator(HandTarot _hand) {
        hand = _hand;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        HandTarot main1_ = hand.charCardsBySuit(_suit1);
        HandTarot main2_ = hand.charCardsBySuit(_suit2);
        return Numbers.compare(main1_.total(), main2_.total());
    }

}
