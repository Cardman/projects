package cards.tarot.comparators;
import cards.consts.Suit;
import cards.tarot.HandTarot;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class HandTarotShortLengthComparator implements Comparing<Suit> {

    private HandTarot hand;

    public HandTarotShortLengthComparator(HandTarot _hand) {
        hand = _hand;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        HandTarot main1_ = hand.couleur(_suit1);
        HandTarot main2_ = hand.couleur(_suit2);
        return Numbers.compare(main1_.total(), main2_.total());
    }

}
