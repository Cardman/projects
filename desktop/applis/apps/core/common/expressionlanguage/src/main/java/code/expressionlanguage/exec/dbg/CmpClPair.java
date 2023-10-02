package code.expressionlanguage.exec.dbg;

import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class CmpClPair implements Comparing<CoreCheckedExecOperationNodeInfosPreference> {
    @Override
    public int compare(CoreCheckedExecOperationNodeInfosPreference _one, CoreCheckedExecOperationNodeInfosPreference _two) {
        int res_ = NumberUtil.compareLg(_one.getPreference(), _two.getPreference());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return StringUtil.compareStrings(_one.getSgn(), _two.getSgn());
    }
}
