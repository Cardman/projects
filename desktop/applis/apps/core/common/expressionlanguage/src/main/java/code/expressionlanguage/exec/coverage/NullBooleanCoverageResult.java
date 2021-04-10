package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.Argument;
import code.expressionlanguage.linkage.VariablesOffsets;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.StringList;

public final class NullBooleanCoverageResult extends AbstractCoverageResult {
    private boolean coverTrue;
    private boolean coverFalse;
    private boolean coverNull;

    @Override
    public StringList getCoversFoundReport(VariablesOffsets _vars) {
        StringList founds_ = new StringList();
        if (!isStrictPartialCovered()) {
            return founds_;
        }
        if (isCoverNull()) {
            founds_.add(_vars.getDisplayedStrings().getNullCoverString());
        }
        if (isCoverTrue()) {
            founds_.add(_vars.getDisplayedStrings().getTrueString());
        }
        if (isCoverFalse()) {
            founds_.add(_vars.getDisplayedStrings().getFalseString());
        }
        return founds_;
    }

    @Override
    public int getCovered() {
        int c_ = 0;
        if (coverTrue) {
            c_++;
        }
        if (coverFalse) {
            c_++;
        }
        if (coverNull) {
            c_++;
        }
        return c_;
    }

    @Override
    public int getFull() {
        return 3;
    }

    @Override
    public void cover(Argument _arg) {
        if (_arg.isNull()) {
            coverNull = true;
        }
        if (BooleanStruct.isTrue(_arg.getStruct())) {
            coverTrue = true;
        }
        if (BooleanStruct.isFalse(_arg.getStruct())) {
            coverFalse = true;
        }

    }

    @Override
    public void fullCover() {
        coverNull = true;
        coverTrue = true;
        coverFalse = true;
    }

    public boolean isCoverFalse() {
        return coverFalse;
    }

    public boolean isCoverTrue() {
        return coverTrue;
    }

    public boolean isCoverNull() {
        return coverNull;
    }
}
