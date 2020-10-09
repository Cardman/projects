package cards.belote.comparators;
import cards.belote.BidBeloteSuit;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class BidBeloteSuitComparator implements Comparing<BidBeloteSuit> {

    @Override
    public int compare(BidBeloteSuit _bid1, BidBeloteSuit _bid2) {
        return NumberUtil.compareLg(_bid1.getBid().getForce(), _bid2.getBid().getForce());
    }

}
