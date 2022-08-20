package cards.belote.comparators;

import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.consts.Suit;
import code.util.EnumMap;
import code.util.ints.Comparing;

public final class GameStrengthGreatFirstHandBeloteComparator implements
        Comparing<Suit> {

    private final EnumMap<Suit,HandBelote> hand;
    private final BidBeloteSuit bid;
    private final CardStrengthComparatorFirst cmp;

    public GameStrengthGreatFirstHandBeloteComparator(
            EnumMap<Suit,HandBelote> _hand, BidBeloteSuit _bid) {
        hand = _hand;
        bid = _bid;
        cmp = new CardStrengthComparatorFirst();
    }

    @Override
    public int compare(Suit _suit1, Suit _suit2) {
        return cmp.compare(hand,bid,_suit1,_suit2);
    }

}
