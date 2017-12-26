package code.expressionlanguage;

import code.expressionlanguage.methods.RootBlock;
import code.sml.RowCol;

public final class ResultTypeCreation {

    private RootBlock type;

    private RowCol nextRowCol;

    private int nextIndex;

    private boolean ok;

    public RootBlock getType() {
        return type;
    }

    public void setType(RootBlock _type) {
        type = _type;
    }

    public RowCol getNextRowCol() {
        return nextRowCol;
    }

    public void setNextRowCol(RowCol _nextRowCol) {
        nextRowCol = _nextRowCol;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        nextIndex = _nextIndex;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean _ok) {
        ok = _ok;
    }
}
