package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.linkage.VariablesOffsets;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public abstract class AbstractCoverageResult {
    protected static final int TWO = 2;
    protected static final int THREE = 3;
    private boolean init;

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean _initTypes) {
        if (_initTypes) {
            init = true;
        }
    }

    public abstract StringList getCoversFoundReport(VariablesOffsets _vars);
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
    public abstract void cover(Struct _arg);
    public abstract void fullCover();
}
