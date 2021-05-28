package aiki.facade;

import aiki.facade.enums.SelectedBoolean;
import code.util.comparators.ComparatorBoolean;


public final class BooleanFieldComparator {

    private SelectedBoolean increasing = SelectedBoolean.YES_AND_NO;

    private int priority;

    public int compare(boolean _o1, boolean _o2) {
        if (increasing == SelectedBoolean.YES) {
            return ComparatorBoolean.cmp(_o1,_o2);
        }
        if (increasing == SelectedBoolean.NO) {
            return ComparatorBoolean.cmp(_o2,_o1);
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
