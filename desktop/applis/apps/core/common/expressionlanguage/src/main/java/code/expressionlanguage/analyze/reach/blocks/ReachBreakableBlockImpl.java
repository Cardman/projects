package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.analyze.blocks.BreakableBlock;

public abstract class ReachBreakableBlockImpl extends ReachBracedBlock implements ReachBreakableBlock {
    private final String label;
    protected ReachBreakableBlockImpl(AbsBk _info, BreakableBlock _br) {
        super(_info);
        label = _br.getRealLabelInfo().getInfo();
    }

    @Override
    public String getRealLabel() {
        return getLabel();
    }

    public String getLabel() {
        return label;
    }
}
