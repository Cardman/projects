package cards.tarot.comparators;
import code.util.ints.Comparing;

import code.util.CustList;
import cards.consts.Suit;
import cards.tarot.HandTarot;

/**
 Applied if sets are distinct*/
public final class GameSeqLengthTarotComparator implements Comparing<HandTarot> {

    @Override
    public int compare(HandTarot _hand1, HandTarot _hand2) {
        if(_hand1.total() > _hand2.total()) {
            return CustList.NO_SWAP_SORT;
        }
        if(_hand1.total() < _hand2.total()) {
            return CustList.SWAP_SORT;
        }
        if(_hand1.estVide()) {
            //_hand1.total() == _hand2.total() ==> _hand1.eq(_hand2)
            return CustList.EQ_CMP;
        }
        if(_hand1.premiereCarte().strength(Suit.TRUMP)
                > _hand2.premiereCarte().strength(Suit.TRUMP)) {
            return CustList.NO_SWAP_SORT;
        }
        return CustList.SWAP_SORT;
    }

}
