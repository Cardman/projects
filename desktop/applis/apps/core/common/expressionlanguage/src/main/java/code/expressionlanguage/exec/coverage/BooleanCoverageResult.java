package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.BooleanStruct;

public final class BooleanCoverageResult extends AbstractCoverageResult {
    private boolean coverTrue;
    private boolean coverFalse;
    @Override
    public int getCovered() {
        int c_ = 0;
        if (coverTrue) {
            c_++;
        }
        if (coverFalse) {
            c_++;
        }
        return c_;
    }

    @Override
    public int getFull() {
        return 2;
    }

    @Override
    public void cover(Argument _arg) {
        if (BooleanStruct.isTrue(_arg.getStruct())) {
            coverTrue = true;
        }
        if (BooleanStruct.isFalse(_arg.getStruct())) {
            coverFalse = true;
        }
    }

    @Override
    public void fullCover() {
        coverTrue = true;
        coverFalse = true;
    }

    public boolean isCoverFalse() {
        return coverFalse;
    }

    public boolean isCoverTrue() {
        return coverTrue;
    }
}
