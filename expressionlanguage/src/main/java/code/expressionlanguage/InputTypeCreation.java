package code.expressionlanguage;

import code.sml.RowCol;

public final class InputTypeCreation {

    private RowCol nextRowCol;

    private int nextIndex;

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
}
