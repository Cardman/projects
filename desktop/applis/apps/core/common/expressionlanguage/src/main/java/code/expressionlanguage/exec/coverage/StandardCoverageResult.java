package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.linkage.VariablesOffsets;
import code.expressionlanguage.structs.Struct;
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
    public void cover(Struct _arg) {
        covered = true;
    }

    @Override
    public void fullCover() {
        covered = true;
    }
}
