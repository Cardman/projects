package aiki.facade;

import code.util.Numbers;
import aiki.facade.enums.SelectedBoolean;


public final class LongFieldComparator {

    private SelectedBoolean increasing = SelectedBoolean.YES_AND_NO;

    private int priority;

    public int compare(long _o1, long _o2) {
        if (increasing == SelectedBoolean.YES) {
            return Numbers.compareLg(_o1, _o2);
        }
        if (increasing == SelectedBoolean.NO) {
            return Numbers.compareLg(_o2, _o1);
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
