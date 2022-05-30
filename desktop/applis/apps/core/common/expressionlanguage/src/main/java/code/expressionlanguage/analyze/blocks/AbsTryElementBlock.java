package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetStringInfo;

public abstract class AbsTryElementBlock extends BracedBlock implements BreakableBlock,CheckableTree {
    protected AbsTryElementBlock(int _offset) {
        super(_offset);
    }

    @Override
    public OffsetStringInfo getRealLabelInfo() {
        return ElseCondition.getRealLabelInfo(this);
    }
}
