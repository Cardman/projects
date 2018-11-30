package code.util.comparators;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Comparing;

public final class ComparatorListSizeElement implements Comparing<Numbers<Integer>> {

    @Override
    public int compare(Numbers<Integer> _arg0, Numbers<Integer> _arg1) {
        if (_arg0.size() < _arg1.size()) {
            return CustList.NO_SWAP_SORT;
        }
        if (_arg0.size() > _arg1.size()) {
            return CustList.SWAP_SORT;
        }
        int len_ = _arg0.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (_arg0.get(i) < _arg1.get(i)) {
                return CustList.NO_SWAP_SORT;
            }
            if (_arg0.get(i) > _arg1.get(i)) {
                return CustList.SWAP_SORT;
            }
        }
        return CustList.EQ_CMP;
    }
}
