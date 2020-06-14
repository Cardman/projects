package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.Argument;

public abstract class AbstractCoverageResult {
    private boolean init;

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean _initTypes) {
        if (_initTypes) {
            init = true;
        }
    }

    public boolean isStrictPartialCovered() {
        return !isFullCovered() && isPartialCovered();
    }
    public boolean isPartialCovered() {
        return getCovered() > 0;
    }
    public boolean isFullCovered() {
        return getCovered() == getFull();
    }
    public abstract int getCovered();
    public abstract int getFull();
    public abstract void cover(Argument _arg);
    public abstract void fullCover();
}
