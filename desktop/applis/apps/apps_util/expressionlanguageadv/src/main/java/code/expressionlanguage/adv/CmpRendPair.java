package code.expressionlanguage.adv;

import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public final class CmpRendPair implements Comparing<RenderPointInfosPreference> {
    @Override
    public int compare(RenderPointInfosPreference _one, RenderPointInfosPreference _two) {
        int res_ = NumberUtil.compareLg(_one.getPreference(), _two.getPreference());
        if (res_ != SortConstants.EQ_CMP) {
            return res_;
        }
        return StringUtil.compareStrings(_one.getSgn(), _two.getSgn());
    }
}
