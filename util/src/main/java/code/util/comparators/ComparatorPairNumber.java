package code.util.comparators;
import code.util.CustList;
import code.util.Numbers;
import code.util.PairNumber;
import code.util.ints.Comparing;

public final class ComparatorPairNumber<U extends Number, V extends Number> implements Comparing<PairNumber<U, V>> {

    @Override
    public int compare(PairNumber<U, V> _o1, PairNumber<U, V> _o2) {
        int res_ = Numbers.compare(_o1.getFirst(), _o2.getFirst());
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        return Numbers.compare(_o1.getSecond(), _o2.getSecond());
    }

}
