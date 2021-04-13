package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.Argument;
import code.expressionlanguage.linkage.VariablesOffsets;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.StringList;

public final class BooleanCoverageResult extends AbstractCoverageResult {
    private boolean coverBcTrue;
    private boolean coverBcFalse;

    @Override
    public StringList getCoversFoundReport(VariablesOffsets _vars) {
        StringList foundsBc_ = new StringList();
        if (!isStrictPartialCovered()) {
            return foundsBc_;
        }
        if (isCoverBcTrue()) {
            foundsBc_.add(_vars.getDisplayedStrings().getTrueString());
        }
        if (isCoverBcFalse()) {
            foundsBc_.add(_vars.getDisplayedStrings().getFalseString());
        }
        return foundsBc_;
    }

    @Override
    public int getCovered() {
        int bc_ = 0;
        if (coverBcTrue) {
            bc_++;
        }
        if (coverBcFalse) {
            bc_++;
        }
        return bc_;
    }

    @Override
    public int getFull() {
        return TWO;
    }

    @Override
    public void cover(Argument _bc) {
        if (BooleanStruct.isTrue(_bc.getStruct())) {
            coverBcTrue = true;
        }
        if (BooleanStruct.isFalse(_bc.getStruct())) {
            coverBcFalse = true;
        }
    }

    @Override
    public void fullCover() {
        coverBcTrue = true;
        coverBcFalse = true;
    }

    public boolean isCoverBcFalse() {
        return coverBcFalse;
    }

    public boolean isCoverBcTrue() {
        return coverBcTrue;
    }
}
