package code.expressionlanguage.exec.variables;

import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class ViewVariableDeepNameCmp implements Comparing<ViewVariable> {
    @Override
    public int compare(ViewVariable _one, ViewVariable _two) {
        int res_ = NumberUtil.compareLg(_one.getDeep(), _two.getDeep());
        if (res_ != SortConstants.EQ_CMP) {
            return -res_;
        }
        return StringUtil.compareStrings(_one.getName(), _two.getName());
    }
}
