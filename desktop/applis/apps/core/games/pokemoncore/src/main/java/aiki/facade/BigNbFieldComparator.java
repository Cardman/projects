package aiki.facade;

import aiki.facade.enums.SelectedBoolean;

public abstract class BigNbFieldComparator<T> {

    private SelectedBoolean increasing = SelectedBoolean.YES_AND_NO;

    private int priority;

    public int compare(T _o1, T _o2) {
        if (getIncreasing() == SelectedBoolean.YES) {
            return cmp(_o1,_o2);
        }
        if (getIncreasing() == SelectedBoolean.NO) {
            return cmp(_o2,_o1);
        }
        return 0;
    }
    protected abstract int cmp(T _first, T _second);
    public SelectedBoolean getIncreasing() {
        return increasing;
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
