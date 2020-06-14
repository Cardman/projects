package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.Argument;

public final class NullCoverageResult extends AbstractCoverageResult {
    private boolean coverNotNull;
    private boolean coverNull;
    @Override
    public int getCovered() {
        int c_ = 0;
        if (coverNotNull) {
            c_++;
        }
        if (coverNull) {
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
        if (_arg.isNull()) {
            coverNull = true;
        } else {
            coverNotNull = true;
        }
    }

    @Override
    public void fullCover() {
        coverNull = true;
        coverNotNull = true;
    }

    public boolean isCoverNotNull() {
        return coverNotNull;
    }

    public boolean isCoverNull() {
        return coverNull;
    }
}
