package aiki.facade;

import aiki.facade.enums.SelectedBoolean;
import code.maths.Rate;


public final class RateFieldComparator {

    private SelectedBoolean increasing = SelectedBoolean.YES_AND_NO;

    private int priority;

    public int compare(Rate _o1, Rate _o2) {
        if (increasing == SelectedBoolean.YES) {
            return _o1.cmp(_o2);
        }
        if (increasing == SelectedBoolean.NO) {
            return _o2.cmp(_o1);
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
