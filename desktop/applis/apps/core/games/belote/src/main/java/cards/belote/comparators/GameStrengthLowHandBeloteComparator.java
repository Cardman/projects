package cards.belote.comparators;
import cards.belote.BidBeloteSuit;
import cards.belote.HandBelote;
import cards.consts.Suit;
import code.util.IdMap;

/**Only "no trump" suits can be sorted with this comparator*/
public final class GameStrengthLowHandBeloteComparator extends GameStrengthHandBeloteComparator {

    public GameStrengthLowHandBeloteComparator(IdMap<Suit,HandBelote> _hand,
            BidBeloteSuit _bid) {
        super(_hand, _bid, 1);
    }


}
