package code.maths;
import code.util.SortableCustList;
import code.util.core.IndexConstants;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class ComparatorEvents implements Comparing<SortableCustList<LgInt>> {

    @Override
    public int compare(SortableCustList<LgInt> _o1, SortableCustList<LgInt> _o2) {
        int res_ = _o1.size() - _o2.size();
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        int len_;
        len_ = _o1.size();
        for (int i = IndexConstants.FIRST_INDEX; i <len_; i++) {
            res_ = _o1.get(i).cmp(_o2.get(i));
            if (res_ != SortConstants.EQ_CMP) {
                return res_;
            }
        }
        return SortConstants.EQ_CMP;
    }

}
