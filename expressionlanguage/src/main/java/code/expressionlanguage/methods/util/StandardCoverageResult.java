package code.expressionlanguage.methods.util;

import code.expressionlanguage.Argument;

public final class StandardCoverageResult extends AbstractCoverageResult {
    private boolean covered;
    @Override
    public int getCovered() {
        if (covered) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getFull() {
        return 1;
    }

    @Override
    public void cover(Argument _arg) {
        covered = true;
    }
}
