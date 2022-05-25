package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetStringInfo;

public abstract class AbsTryElementBlock extends BracedBlock implements Eval {
    protected AbsTryElementBlock(int _offset) {
        super(_offset);
    }

    @Override
    public OffsetStringInfo getRealLabelInfo() {
        return ElseCondition.getRealLabelInfo(this);
    }
}
