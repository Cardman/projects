package code.expressionlanguage.methods.util;
import code.util.CustList;

public final class SearchingReturnThrow {

    private boolean alwaysSkipped;

    private int order = CustList.INDEX_NOT_FOUND_ELT;

    private boolean exitable;

    private boolean stoppable;

    public boolean isAlwaysSkipped() {
        return alwaysSkipped;
    }

    public void setAlwaysSkipped(boolean _alwaysSkipped) {
        alwaysSkipped = _alwaysSkipped;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int _order) {
        order = _order;
    }

    public boolean isExitable() {
        return exitable;
    }

    public void setExitable(boolean _exitable) {
        exitable = _exitable;
    }

    public boolean isStoppable() {
        return stoppable;
    }

    public void setStoppable(boolean _stoppable) {
        stoppable = _stoppable;
    }

}
