package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.AbsBk;

public final class ReachUnclassedBracedBlock extends ReachBracedBlock {
    protected ReachUnclassedBracedBlock(AbsBk _info) {
        super(_info);
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.canCompleteNormally(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }
}
