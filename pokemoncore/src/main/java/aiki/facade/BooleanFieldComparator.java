package aiki.facade;

import code.util.pagination.SelectedBoolean;


public final class BooleanFieldComparator {

    private SelectedBoolean increasing = SelectedBoolean.YES_AND_NO;

    private int priority;

    public int compare(Boolean _o1, Boolean _o2) {
        if (increasing == SelectedBoolean.YES) {
            return _o1.compareTo(_o2);
        }
        if (increasing == SelectedBoolean.NO) {
            return _o2.compareTo(_o1);
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
