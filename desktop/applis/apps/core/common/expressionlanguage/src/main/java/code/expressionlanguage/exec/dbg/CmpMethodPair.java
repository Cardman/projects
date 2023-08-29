package code.expressionlanguage.exec.dbg;

import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class CmpMethodPair implements Comparing<MethodPointBlockPairRootBlock> {
    @Override
    public int compare(MethodPointBlockPairRootBlock _one, MethodPointBlockPairRootBlock _two) {
        int res_ = NumberUtil.compareLg(_one.getId().getPref().get(), _two.getId().getPref().get());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return StringUtil.compareStrings(_one.getId().getSgn(), _two.getId().getSgn());
    }
}
