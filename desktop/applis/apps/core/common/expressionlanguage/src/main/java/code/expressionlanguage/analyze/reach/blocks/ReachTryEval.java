package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.TryEval;

public final class ReachTryEval extends ReachBracedBlock implements ReachBreakableBlock {
    private final String label;
    public ReachTryEval(TryEval _info) {
        super(_info);
        label = _info.getLabel();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

}
