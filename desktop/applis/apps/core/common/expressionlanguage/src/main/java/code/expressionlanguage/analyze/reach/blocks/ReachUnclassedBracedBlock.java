package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.Block;

public final class ReachUnclassedBracedBlock extends ReachBracedBlock {
    protected ReachUnclassedBracedBlock(Block _info) {
        super(_info);
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.canCompleteNormally(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }
}
