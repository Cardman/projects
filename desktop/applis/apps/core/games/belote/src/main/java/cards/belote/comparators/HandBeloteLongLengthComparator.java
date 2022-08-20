package cards.belote.comparators;
import cards.belote.HandBelote;
import cards.consts.Suit;
import code.util.IdMap;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class HandBeloteLongLengthComparator implements Comparing<Suit> {

    private final IdMap<Suit,HandBelote> hand;

    public HandBeloteLongLengthComparator(IdMap<Suit,HandBelote> _hand) {
        hand = _hand;
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        int total1_ = hand.getVal(_suit1).total();
        int total2_ = hand.getVal(_suit2).total();
        return NumberUtil.compareLg(total2_, total1_);
    }

}
