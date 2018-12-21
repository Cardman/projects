package code.expressionlanguage.files;

import code.expressionlanguage.methods.ImportingBlock;

public abstract class ResultCreation {

    private int nextIndex;

    private boolean ok;

    public abstract ImportingBlock getType();

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
