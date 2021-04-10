package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.Argument;
import code.expressionlanguage.linkage.VariablesOffsets;
import code.util.StringList;

public final class StandardCoverageResult extends AbstractCoverageResult {
    private boolean covered;

    @Override
    public StringList getCoversFoundReport(VariablesOffsets _vars) {
        return new StringList();
    }

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

    @Override
    public void fullCover() {
        covered = true;
    }
}
