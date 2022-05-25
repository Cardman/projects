package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetStringInfo;

public abstract class LabelledConditionBlock extends ConditionBlock implements BreakableBlock{

    protected LabelledConditionBlock(OffsetStringInfo _condition, int _offset, OffsetStringInfo _lab) {
        super(_condition, _offset, _lab);
    }

    @Override
    public OffsetStringInfo getRealLabelInfo() {
        return getLabelInfo();
    }
}
