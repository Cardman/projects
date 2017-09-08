package code.expressionlanguage.opers.util;

import java.util.Comparator;

import code.util.CustList;
import code.util.Numbers;

public final class IndexesComparator implements Comparator<Numbers<Integer>>{

    @Override
    public int compare(Numbers<Integer> _one, Numbers<Integer> _two) {
        int res_ = Numbers.compare(_one.size(), _two.size());
        if (res_ != CustList.EQ_CMP) {
            return -res_;
        }
        int len_ = _one.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            res_ = Numbers.compare(_one.get(i), _two.get(i));
            if (res_ != CustList.EQ_CMP) {
                return res_;
            }
        }
        return CustList.EQ_CMP;
    }

}
