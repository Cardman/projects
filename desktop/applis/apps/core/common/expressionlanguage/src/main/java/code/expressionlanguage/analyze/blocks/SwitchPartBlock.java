package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetsBlock;

public abstract class SwitchPartBlock extends BracedBlock implements
        BuildableElMethod {

    private SwitchBlock switchParent;

    private int conditionNb;

    protected SwitchPartBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    public SwitchBlock getSwitchParent() {
        return switchParent;
    }

    public void setSwitchParent(SwitchBlock _switchParent) {
        switchParent = _switchParent;
    }

    public int getConditionNb() {
        return conditionNb;
    }

    public void setConditionNb(int _conditionNb) {
        conditionNb = _conditionNb;
    }
}
