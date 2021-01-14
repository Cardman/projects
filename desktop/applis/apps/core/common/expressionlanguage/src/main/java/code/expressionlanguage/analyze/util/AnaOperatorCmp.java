package code.expressionlanguage.analyze.util;

import code.expressionlanguage.analyze.blocks.OperatorBlock;
import code.util.StringList;
import code.util.comparators.ComparatorBoolean;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class AnaOperatorCmp implements Comparing<OperatorBlock> {
    @Override
    public int compare(OperatorBlock _one, OperatorBlock _two) {
        int res_ = ComparatorBoolean.cmp(_one.isRetRef(),_two.isRetRef());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        res_ = ComparatorBoolean.cmp(_one.isVarargs(),_two.isVarargs());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        res_ = StringUtil.compareStrings(_one.getName(),_two.getName());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        StringList pOne_ = _one.getImportedParametersTypes();
        StringList pTwo_ = _two.getImportedParametersTypes();
        res_ = NumberUtil.compareLg(pOne_.size(), pTwo_.size());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        int i_ = IndexConstants.FIRST_INDEX;
        int len_ = Math.min(pOne_.size(), pTwo_.size());
        while (i_ < len_) {
            res_ = ComparatorBoolean.cmp(_one.getParametersRef().get(i_),_two.getParametersRef().get(i_));
            if (res_ != SortConstants.EQ_CMP) {
                return res_;
            }
            res_ = StringUtil.compareStrings(pOne_.get(i_),pTwo_.get(i_));
            if (res_ != SortConstants.EQ_CMP) {
                return res_;
            }
            i_++;
        }
        return SortConstants.EQ_CMP;
    }
}
