package cards.tarot.comparators;
import cards.consts.Suit;
import cards.tarot.HandTarot;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class HandTarotLongLengthComparator implements Comparing<Suit> {

    private HandTarot hand;

    public HandTarotLongLengthComparator(HandTarot _hand) {
        hand = _hand;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        HandTarot main1_ = hand.couleur(_suit1);
        HandTarot main2_ = hand.couleur(_suit2);
        return Numbers.compareLg(main2_.total(), main1_.total());
    }

}
