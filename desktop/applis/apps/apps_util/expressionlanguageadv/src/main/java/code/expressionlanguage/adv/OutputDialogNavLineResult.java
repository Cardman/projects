package code.expressionlanguage.adv;

import code.threads.AbstractAtomicBoolean;

public final class OutputDialogNavLineResult {
    private int row;
    private int col;
    private int index=-1;
    private final AbstractAtomicBoolean valid;

    public OutputDialogNavLineResult(AbstractAtomicBoolean _v) {
        this.valid = _v;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int _r) {
        this.row = _r;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int _c) {
        this.col = _c;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int _i) {
        this.index = _i;
    }

    public AbstractAtomicBoolean getValid() {
        return valid;
    }
}
