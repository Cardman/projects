package cards.belote.comparators;
import java.util.Comparator;

import code.util.CustList;
import cards.belote.BidBeloteSuit;

public final class BidBeloteSuitComparator implements Comparator<BidBeloteSuit> {

    @Override
    public int compare(BidBeloteSuit _bid1, BidBeloteSuit _bid2) {
        if(_bid1.strongerThan(_bid2)){
            return CustList.SWAP_SORT;
        }
        if(_bid2.strongerThan(_bid1)){
            return CustList.NO_SWAP_SORT;
        }
        return CustList.EQ_CMP;
    }

}
