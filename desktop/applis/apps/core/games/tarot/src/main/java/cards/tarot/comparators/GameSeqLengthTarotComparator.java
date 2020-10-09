package cards.tarot.comparators;
import cards.consts.Suit;
import cards.tarot.HandTarot;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

/**
 Applied if sets are distinct*/
public final class GameSeqLengthTarotComparator implements Comparing<HandTarot> {

    @Override
    public int compare(HandTarot _hand1, HandTarot _hand2) {
        if(_hand1.total() > _hand2.total()) {
            return SortConstants.NO_SWAP_SORT;
        }
        if(_hand1.total() < _hand2.total()) {
            return SortConstants.SWAP_SORT;
        }
        if(_hand1.estVide()) {
            //_hand1.total() == _hand2.total() ==> _hand1.eq(_hand2)
            return SortConstants.EQ_CMP;
        }
        if(_hand1.premiereCarte().strength(Suit.TRUMP)
                > _hand2.premiereCarte().strength(Suit.TRUMP)) {
            return SortConstants.NO_SWAP_SORT;
        }
        return SortConstants.SWAP_SORT;
    }

}
