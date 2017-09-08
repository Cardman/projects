package code.maths;
import java.util.Comparator;

import code.util.CustList;
import code.util.SortableCustList;

public final class ComparatorEvents implements Comparator<SortableCustList<LgInt>> {

    @Override
    public int compare(SortableCustList<LgInt> _o1, SortableCustList<LgInt> _o2) {
        int res_ = _o1.size() - _o2.size();
        if (res_ != CustList.EQ_CMP) {
            return res_;
        }
        int len_;
        len_ = _o1.size();
        for (int i = CustList.FIRST_INDEX; i <len_; i++) {
            res_ = _o1.get(i).cmp(_o2.get(i));
            if (res_ != CustList.EQ_CMP) {
                return res_;
            }
        }
        return CustList.EQ_CMP;
    }

}
