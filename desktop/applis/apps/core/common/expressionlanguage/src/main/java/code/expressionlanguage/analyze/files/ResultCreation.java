package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.blocks.AbsBk;

public final class ResultCreation {

    private int nextIndex;

    private AbsBk block;

    private boolean okType;

    public AbsBk getBlock() {
        return block;
    }

    public void setBlock(AbsBk _block) {
        block = _block;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        nextIndex = _nextIndex;
    }

    public boolean isOkType() {
        return okType;
    }

    public void setOkType(boolean _okType) {
        this.okType = _okType;
    }
}
