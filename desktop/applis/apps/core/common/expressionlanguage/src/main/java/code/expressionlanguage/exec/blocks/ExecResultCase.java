package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.ConditionReturn;

public final class ExecResultCase {
    private final ConditionReturn condition;
    private final ExecBracedBlock block;
    private final int index;

    public ExecResultCase(ConditionReturn _c,ExecBracedBlock _block, int _index) {
        this.condition = _c;
        this.block = _block;
        this.index = _index;
    }

    public ConditionReturn getCondition() {
        return condition;
    }

    public ExecBracedBlock getBlock() {
        return block;
    }

    public int getIndex() {
        return index;
    }
}
