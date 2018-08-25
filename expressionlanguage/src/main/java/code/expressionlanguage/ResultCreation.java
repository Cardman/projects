package code.expressionlanguage;

import code.expressionlanguage.methods.ImportingBlock;
import code.sml.RowCol;

public abstract class ResultCreation {

    private RowCol nextRowCol;

    private int nextIndex;

    private boolean ok;

    public abstract ImportingBlock getType();
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
