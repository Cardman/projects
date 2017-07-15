package cards.belote.comparators;
import java.util.Comparator;

import code.util.EnumMap;
import code.util.Numbers;
import cards.belote.HandBelote;
import cards.consts.Suit;

public final class HandBeloteLongLengthComparator implements Comparator<Suit> {

    private EnumMap<Suit,HandBelote> hand;

    public HandBeloteLongLengthComparator(EnumMap<Suit,HandBelote> _hand) {
        hand = _hand;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        int total1_ = hand.getVal(_suit1).total();
        int total2_ = hand.getVal(_suit2).total();
        return Numbers.compare(total2_, total1_);
    }

}
