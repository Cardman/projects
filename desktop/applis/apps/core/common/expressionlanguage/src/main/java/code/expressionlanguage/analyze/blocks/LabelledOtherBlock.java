package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetStringInfo;

public abstract class LabelledOtherBlock extends BracedBlock implements BreakableBlock{

    protected LabelledOtherBlock(int _offset, OffsetStringInfo _lab) {
        super(_offset, _lab);
    }

    @Override
    public OffsetStringInfo getRealLabelInfo() {
        return getLabelInfo();
    }
}
