package aiki.facade;

import aiki.facade.enums.SelectedBoolean;
import code.util.core.NumberUtil;


public final class LongFieldComparator {

    private SelectedBoolean increasing = SelectedBoolean.YES_AND_NO;

    private int priority;

    public int compare(long _o1, long _o2) {
        if (increasing == SelectedBoolean.YES) {
            return NumberUtil.compareLg(_o1, _o2);
        }
        if (increasing == SelectedBoolean.NO) {
            return NumberUtil.compareLg(_o2, _o1);
        }
        return 0;
    }

    public void setIncreasing(SelectedBoolean _increasing) {
        increasing = _increasing;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int _priority) {
        priority = _priority;
    }
}
