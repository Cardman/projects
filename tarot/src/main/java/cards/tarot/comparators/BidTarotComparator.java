package cards.tarot.comparators;
import java.util.Comparator;

import code.util.CustList;
import cards.tarot.enumerations.BidTarot;

public final class BidTarotComparator implements Comparator<BidTarot> {

    @Override
    public int compare(BidTarot _bid1, BidTarot _bid2) {
        if(_bid1.strongerThan(_bid2)){
            return CustList.SWAP_SORT;
        }
        if(_bid2.strongerThan(_bid1)){
            return CustList.NO_SWAP_SORT;
        }
        return CustList.EQ_CMP;
    }

}
