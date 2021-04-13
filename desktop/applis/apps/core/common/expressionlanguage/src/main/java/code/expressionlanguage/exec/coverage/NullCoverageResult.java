package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.Argument;
import code.expressionlanguage.linkage.VariablesOffsets;
import code.util.StringList;

public final class NullCoverageResult extends AbstractCoverageResult {
    private boolean coverNcNotNull;
    private boolean coverNcNull;

    @Override
    public StringList getCoversFoundReport(VariablesOffsets _vars) {
        StringList foundsNc_ = new StringList();
        if (!isStrictPartialCovered()) {
            return foundsNc_;
        }
        if (isCoverNcNull()) {
            foundsNc_.add(_vars.getDisplayedStrings().getNullCoverString());
        }
        if (isCoverNcNotNull()) {
            foundsNc_.add(_vars.getDisplayedStrings().getNotNullCoverString());
        }
        return foundsNc_;
    }

    @Override
    public int getCovered() {
        int nc_ = 0;
        if (coverNcNotNull) {
            nc_++;
        }
        if (coverNcNull) {
            nc_++;
        }
        return nc_;
    }

    @Override
    public int getFull() {
        return TWO;
    }

    @Override
    public void cover(Argument _nc) {
        if (_nc.isNull()) {
            coverNcNull = true;
        } else {
            coverNcNotNull = true;
        }
    }

    @Override
    public void fullCover() {
        coverNcNull = true;
        coverNcNotNull = true;
    }

    public boolean isCoverNcNotNull() {
        return coverNcNotNull;
    }

    public boolean isCoverNcNull() {
        return coverNcNull;
    }
}
