package code.expressionlanguage.exec;

import code.expressionlanguage.structs.MethodMetaInfo;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public class OperatorCmp implements Comparing<MethodMetaInfo> {

    @Override
    public int compare(MethodMetaInfo _one, MethodMetaInfo _two) {
        int res_ = StringUtil.compareStrings(_one.getName(),_two.getName());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        StringList pOne_ = _one.getParameterNames();
        StringList pTwo_ = _two.getParameterNames();
        res_ = NumberUtil.compareLg(pOne_.size(), pTwo_.size());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        int i_ = IndexConstants.FIRST_INDEX;
        while (true) {
            res_ = StringUtil.compareStrings(pOne_.get(i_),pTwo_.get(i_));
            if (res_ != SortConstants.EQ_CMP) {
                return res_;
            }
            i_++;
        }
    }
}
