package code.expressionlanguage.exec;

import code.util.Ints;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

public final class IndexesComparator implements Comparing<Ints>{

    @Override
    public int compare(Ints _one, Ints _two) {
        int res_ = NumberUtil.compareLg(_one.size(), _two.size());
        if (res_ != SortConstants.EQ_CMP) {
            return -res_;
        }
        int len_ = _one.size();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            res_ = NumberUtil.compareLg(_one.get(i), _two.get(i));
            if (res_ != SortConstants.EQ_CMP) {
                return res_;
            }
        }
        return SortConstants.EQ_CMP;
    }

}
