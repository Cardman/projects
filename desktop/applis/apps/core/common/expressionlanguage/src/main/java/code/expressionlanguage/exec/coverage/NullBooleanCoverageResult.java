package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.linkage.VariablesOffsets;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public final class NullBooleanCoverageResult extends AbstractCoverageResult {
    private boolean coverNbcTrue;
    private boolean coverNbcFalse;
    private boolean coverNbcNull;

    @Override
    public StringList getCoversFoundReport(VariablesOffsets _vars) {
        StringList foundsNbc_ = new StringList();
        if (!isStrictPartialCovered()) {
            return foundsNbc_;
        }
        if (isCoverNbcNull()) {
            foundsNbc_.add(_vars.getDisplayedStrings().getNullCoverString());
        }
        if (isCoverNbcTrue()) {
            foundsNbc_.add(_vars.getDisplayedStrings().getTrueString());
        }
        if (isCoverNbcFalse()) {
            foundsNbc_.add(_vars.getDisplayedStrings().getFalseString());
        }
        return foundsNbc_;
    }

    @Override
    public int getCovered() {
        int nbc_ = 0;
        if (coverNbcTrue) {
            nbc_++;
        }
        if (coverNbcFalse) {
            nbc_++;
        }
        if (coverNbcNull) {
            nbc_++;
        }
        return nbc_;
    }

    @Override
    public int getFull() {
        return THREE;
    }

    @Override
    public void cover(Struct _nbc) {
        if (_nbc == NullStruct.NULL_VALUE) {
            coverNbcNull = true;
        }
        if (BooleanStruct.isTrue(_nbc)) {
            coverNbcTrue = true;
        }
        if (BooleanStruct.isFalse(_nbc)) {
            coverNbcFalse = true;
        }

    }

    @Override
    public void fullCover() {
        coverNbcNull = true;
        coverNbcTrue = true;
        coverNbcFalse = true;
    }

    public boolean isCoverNbcFalse() {
        return coverNbcFalse;
    }

    public boolean isCoverNbcTrue() {
        return coverNbcTrue;
    }

    public boolean isCoverNbcNull() {
        return coverNbcNull;
    }
}
