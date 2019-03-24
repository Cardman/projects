package code.expressionlanguage.methods.util;

import code.expressionlanguage.Argument;

public abstract class AbstractCoverageResult {
    public boolean isPartialCovered() {
        return getCovered() > 0;
    }
    public boolean isFullCovered() {
        return getCovered() == getFull();
    }
    public abstract int getCovered();
    public abstract int getFull();
    public abstract void cover(Argument _arg);
}
