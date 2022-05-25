package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetStringInfo;

public abstract class AbstractForLoop extends LabelledOtherBlock implements Loop,BuildableElMethod{
    private int conditionNb;

    protected AbstractForLoop(int _offset, OffsetStringInfo _lab) {
        super(_offset,_lab);
    }


    public int getConditionNb() {
        return conditionNb;
    }

    public void setConditionNb(int _conditionNb) {
        conditionNb = _conditionNb;
    }
}
