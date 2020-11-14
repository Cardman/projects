package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetsBlock;

public abstract class AbstractForLoop extends BracedBlock {
    private int conditionNb;
    protected AbstractForLoop(OffsetsBlock _offset) {
        super(_offset);
    }

    public int getConditionNb() {
        return conditionNb;
    }

    public void setConditionNb(int _conditionNb) {
        conditionNb = _conditionNb;
    }
}
